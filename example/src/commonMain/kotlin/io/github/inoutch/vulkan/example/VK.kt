package io.github.inoutch.vulkan.example

import io.github.inoutch.kotlin.vulkan.api.VkCommandBuffer
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferBeginInfo
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferUsageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkCommandPool
import io.github.inoutch.kotlin.vulkan.api.VkDevice
import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.VkFence
import io.github.inoutch.kotlin.vulkan.api.VkFenceCreateFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkFenceCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkFramebuffer
import io.github.inoutch.kotlin.vulkan.api.VkImage
import io.github.inoutch.kotlin.vulkan.api.VkImageView
import io.github.inoutch.kotlin.vulkan.api.VkInstance
import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDevice
import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDeviceMemoryProperties
import io.github.inoutch.kotlin.vulkan.api.VkPipelineStageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkPresentInfoKHR
import io.github.inoutch.kotlin.vulkan.api.VkQueue
import io.github.inoutch.kotlin.vulkan.api.VkRenderPass
import io.github.inoutch.kotlin.vulkan.api.VkResult
import io.github.inoutch.kotlin.vulkan.api.VkResult.VK_ERROR_OUT_OF_DATE_KHR
import io.github.inoutch.kotlin.vulkan.api.VkResult.VK_SUBOPTIMAL_KHR
import io.github.inoutch.kotlin.vulkan.api.VkSemaphore
import io.github.inoutch.kotlin.vulkan.api.VkSemaphoreCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkStructureType
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_PRESENT_INFO_KHR
import io.github.inoutch.kotlin.vulkan.api.VkSubmitInfo
import io.github.inoutch.kotlin.vulkan.api.VkSurface
import io.github.inoutch.kotlin.vulkan.api.VkSurfaceFormatKHR
import io.github.inoutch.kotlin.vulkan.api.VkSwapchainKHR
import io.github.inoutch.kotlin.vulkan.api.vk
import io.github.inoutch.kotlin.vulkan.extension.forEachIndexes
import io.github.inoutch.kotlin.vulkan.utility.DeviceQueueFamilyIndices
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty
import io.github.inoutch.kotlin.vulkan.utility.SwapchainRecreator
import io.github.inoutch.kotlin.vulkan.utility.SwapchainRecreatorDepthResource
import io.github.inoutch.kotlin.vulkan.utility.SwapchainSupportDetails

class VK(
    applicationName: String,
    engineName: String,
    enableLayerNames: List<String>,
    enableExtensionNames: List<String>,
    enableDeviceLayerNames: List<String>,
    enableDeviceExtensionNames: List<String>,
    var windowSize: VkExtent2D,
    createSurface: (surface: MutableProperty<VkSurface>, instance: VkInstance) -> VkResult
) {
    companion object {
        const val MAX_FRAMES_IN_FLIGHT = 2
    }

    val device: VkDevice

    val renderPass: VkRenderPass
        get() = swapchainRecreator.current.renderPass

    val commandBuffer: VkCommandBuffer
        get() = swapchainRecreator.current.commandBuffers[currentImageIndex]

    val framebuffer: VkFramebuffer
        get() = swapchainRecreator.current.framebuffers[currentImageIndex]

    val swapchainImage: VkImage
        get() = swapchainRecreator.current.swapchainImages[currentImageIndex]

    val swapchainExtent: VkExtent2D
        get() = swapchainRecreator.current.extent

    var mustRecreateSwapchain = false

    private val instance: VkInstance

    private val surface: VkSurface

    private val physicalDevices: List<VkPhysicalDevice>

    private val primaryPhysicalDevice: VkPhysicalDevice

    private val physicalDeviceMemoryProperties: VkPhysicalDeviceMemoryProperties

    private val deviceQueueFamilyIndices: DeviceQueueFamilyIndices

    private val swapchainSupportDetails: SwapchainSupportDetails

    private val surfaceFormat: VkSurfaceFormatKHR

    private val queue: VkQueue

    private val presentQueue: VkQueue

    private val commandPool: VkCommandPool

    private val swapchainRecreator: SwapchainRecreator

    private val imageAvailableSemaphores: List<VkSemaphore>

    private val renderCompleteSemaphores: List<VkSemaphore>

    private val inFlightFences: List<VkFence>

    private var currentFrame = 0

    private var currentImageIndex = 0

    private val destroyers = mutableListOf<() -> Unit>()

    init {
        try {
            instance = checkNotNull(createInstance(applicationName, engineName, enableLayerNames, enableExtensionNames))
            destroyers.add { vk.destroyInstance(instance) }

            val surfaceRef = MutableProperty<VkSurface>()
            createSurface(surfaceRef, instance)
            surface = checkNotNull(surfaceRef.value)
            destroyers.add { vk.destroySurfaceKHR(instance, surface) }

            val physicalDevicesRef = mutableListOf<VkPhysicalDevice>()
            vk.enumeratePhysicalDevices(instance, physicalDevicesRef)
            check(physicalDevicesRef.isNotEmpty())
            physicalDevices = physicalDevicesRef.toList()
            primaryPhysicalDevice = physicalDevices.first()

            val physicalDeviceMemoryPropertiesRef = MutableProperty<VkPhysicalDeviceMemoryProperties>()
            vk.getPhysicalDeviceMemoryProperties(primaryPhysicalDevice, physicalDeviceMemoryPropertiesRef)
            physicalDeviceMemoryProperties = checkNotNull(physicalDeviceMemoryPropertiesRef.value)

            deviceQueueFamilyIndices = checkNotNull(DeviceQueueFamilyIndices.find(primaryPhysicalDevice, surface))
            device = checkNotNull(createDevice(
                    primaryPhysicalDevice,
                    deviceQueueFamilyIndices,
                    enableDeviceLayerNames,
                    enableDeviceExtensionNames
            ))
            destroyers.add { vk.destroyDevice(device) }

            swapchainSupportDetails = checkNotNull(SwapchainSupportDetails.querySwapchainSupport(primaryPhysicalDevice, surface))
            surfaceFormat = swapchainSupportDetails.chooseSwapSurfaceFormat()

            val queueRef = MutableProperty<VkQueue>()
            vk.getDeviceQueue(device, deviceQueueFamilyIndices.graphicsQueueFamilyIndex, 0, queueRef)
            queue = checkNotNull(queueRef.value)

            presentQueue = if (
                    deviceQueueFamilyIndices.graphicsQueueFamilyIndex != deviceQueueFamilyIndices.presentQueueFamilyIndex
            ) {
                val presentQueueRef = MutableProperty<VkQueue>()
                vk.getDeviceQueue(device, deviceQueueFamilyIndices.presentQueueFamilyIndex, 0, presentQueueRef)
                checkNotNull(presentQueueRef.value)
            } else {
                queue
            }

            commandPool = checkNotNull(createCommandPool(device, deviceQueueFamilyIndices.graphicsQueueFamilyIndex))
            destroyers.add { vk.destroyCommandPool(device, commandPool) }

            val depthFormat = checkNotNull(findDepthFormat(primaryPhysicalDevice))
            swapchainRecreator = object : SwapchainRecreator(device, commandPool, swapchainSupportDetails, windowSize) {
                override fun createSwapchain(oldSwapchain: VkSwapchainKHR?, extent: VkExtent2D) = createSwapchain(
                        oldSwapchain,
                        extent,
                        device,
                        deviceQueueFamilyIndices,
                        swapchainSupportDetails,
                        surface,
                        surfaceFormat
                )

                override fun createImageView(image: VkImage) = createImageView(device, image, surfaceFormat.format)

                override fun createRenderPass() = createRenderPass(
                        device,
                        surfaceFormat,
                        checkNotNull(findDepthFormat(primaryPhysicalDevice))
                )

                override fun createDepthImage(extent: VkExtent2D) = createDepthImage(
                        device,
                        extent,
                        depthFormat
                )

                override fun allocateDepthImageMemory(depthImage: VkImage) = allocateDepthImageMemory(
                        device,
                        commandPool,
                        queue,
                        depthImage,
                        depthFormat,
                        physicalDeviceMemoryProperties
                )

                override fun createDepthImageView(depthImage: VkImage) = createDepthImageView(
                        device,
                        depthImage,
                        depthFormat
                )

                override fun createFramebuffer(
                    swapchainImageView: VkImageView,
                    renderPass: VkRenderPass,
                    depthResource: SwapchainRecreatorDepthResource,
                    extent: VkExtent2D
                ) = createFramebuffer(device, renderPass, swapchainImageView, depthResource.depthImageView, extent)

                override fun allocateCommandBuffers(
                    commandPool: VkCommandPool,
                    size: Int
                ) = allocateRenderCommandBuffer(device, commandPool, size)
            }

            val semaphoreCreateInfo = VkSemaphoreCreateInfo(VkStructureType.VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO)
            imageAvailableSemaphores = List(MAX_FRAMES_IN_FLIGHT) {
                val semaphoreRef = MutableProperty<VkSemaphore>()
                vk.createSemaphore(device, semaphoreCreateInfo, semaphoreRef)
                val semaphore = checkNotNull(semaphoreRef.value)
                destroyers.add { vk.destroySemaphore(device, semaphore) }
                semaphore
            }
            renderCompleteSemaphores = List(MAX_FRAMES_IN_FLIGHT) {
                val semaphoreRef = MutableProperty<VkSemaphore>()
                vk.createSemaphore(device, semaphoreCreateInfo, semaphoreRef)
                val semaphore = checkNotNull(semaphoreRef.value)
                destroyers.add { vk.destroySemaphore(device, semaphore) }
                semaphore
            }

            inFlightFences = List(MAX_FRAMES_IN_FLIGHT) {
                val fenceCreateInfo = VkFenceCreateInfo(
                        VkStructureType.VK_STRUCTURE_TYPE_FENCE_CREATE_INFO,
                        listOf(VkFenceCreateFlagBits.VK_FENCE_CREATE_SIGNALED_BIT)
                )
                val fenceRef = MutableProperty<VkFence>()
                vk.createFence(device, fenceCreateInfo, fenceRef)
                val fence = checkNotNull(fenceRef.value)
                destroyers.add { vk.destroyFence(device, fence) }
                fence
            }
        } catch (e: IllegalStateException) {
            destroy()
            throw e
        }
    }

    fun cmd(scope: (commandBuffer: VkCommandBuffer, framebuffer: VkFramebuffer, swapchainImage: VkImage) -> Unit) {
        swapchainRecreator.current.swapchainImages.size.forEachIndexes {
            cmd(
                    swapchainRecreator.current.commandBuffers[it],
                    swapchainRecreator.current.framebuffers[it],
                    swapchainRecreator.current.swapchainImages[it],
                    scope
            )
        }
    }

    fun submit(cmdScope: ((commandBuffer: VkCommandBuffer, framebuffer: VkFramebuffer, swapchainImage: VkImage) -> Unit)? = null) {
        if (mustRecreateSwapchain) {
            vk.queueWaitIdle(queue)
            swapchainRecreator.recreate(windowSize)
            mustRecreateSwapchain = false
        }

        vk.waitForFences(device, listOf(inFlightFences[currentFrame]), true, Long.MAX_VALUE)
        vk.resetFences(device, listOf(inFlightFences[currentFrame]))

        val currentImageIndexRef = MutableProperty<Int>()
        vk.acquireNextImageKHR(
                device,
                swapchainRecreator.current.swapchain,
                Long.MAX_VALUE,
                imageAvailableSemaphores[currentFrame],
                null,
                currentImageIndexRef
        )
        currentImageIndexRef.value?.let { currentImageIndex = it }
        if (cmdScope != null) {
            vk.resetCommandBuffer(commandBuffer, listOf())
            cmd(commandBuffer, framebuffer, swapchainImage, cmdScope)
        }

        val submitInfo = VkSubmitInfo(
                VkStructureType.VK_STRUCTURE_TYPE_SUBMIT_INFO,
                listOf(imageAvailableSemaphores[currentFrame]),
                listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT),
                listOf(commandBuffer),
                listOf(renderCompleteSemaphores[currentFrame])
        )

        vk.queueSubmit(queue, listOf(submitInfo), inFlightFences[currentFrame])

        val presentInfo = VkPresentInfoKHR(
                VK_STRUCTURE_TYPE_PRESENT_INFO_KHR,
                listOf(renderCompleteSemaphores[currentFrame]),
                listOf(swapchainRecreator.current.swapchain),
                listOf(currentImageIndex),
                null
        )

        val queuePresentResult = vk.queuePresentKHR(presentQueue, presentInfo)
        if (queuePresentResult == VK_ERROR_OUT_OF_DATE_KHR || queuePresentResult == VK_SUBOPTIMAL_KHR) {
            mustRecreateSwapchain = true
        }
        currentFrame = (currentFrame + 1) % MAX_FRAMES_IN_FLIGHT
    }

    fun reset() {
        vk.queueWaitIdle(queue)
        swapchainRecreator.current.commandBuffers.forEach {
            vk.resetCommandBuffer(it, listOf())
        }
    }

    fun destroy() {
        swapchainRecreator.destroy()
        destroyers.reversed().forEach { it.invoke() }
    }

    private fun cmd(
        commandBuffer: VkCommandBuffer,
        framebuffer: VkFramebuffer,
        swapchainImage: VkImage,
        scope: (commandBuffer: VkCommandBuffer, framebuffer: VkFramebuffer, swapchainImage: VkImage) -> Unit
    ) {
        val usage = listOf(VkCommandBufferUsageFlagBits.VK_COMMAND_BUFFER_USAGE_SIMULTANEOUS_USE_BIT)
        val beginInfo = VkCommandBufferBeginInfo(VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO, usage, null)
        vk.resetCommandBuffer(commandBuffer, listOf())
        vk.beginCommandBuffer(commandBuffer, beginInfo)
        scope(commandBuffer, framebuffer, swapchainImage)
        vk.endCommandBuffer(commandBuffer)
    }
}
