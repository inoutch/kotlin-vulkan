package io.github.inoutch.kotlin.vulkan.utility

import io.github.inoutch.kotlin.vulkan.api.VkCommandBuffer
import io.github.inoutch.kotlin.vulkan.api.VkCommandPool
import io.github.inoutch.kotlin.vulkan.api.VkDevice
import io.github.inoutch.kotlin.vulkan.api.VkDeviceMemory
import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.VkFramebuffer
import io.github.inoutch.kotlin.vulkan.api.VkImage
import io.github.inoutch.kotlin.vulkan.api.VkImageView
import io.github.inoutch.kotlin.vulkan.api.VkRenderPass
import io.github.inoutch.kotlin.vulkan.api.VkSwapchainKHR
import io.github.inoutch.kotlin.vulkan.api.vk

abstract class SwapchainRecreator(
    private val device: VkDevice,
    private val commandPool: VkCommandPool,
    private val swapchainSupportDetails: SwapchainSupportDetails,
    initialExtent2D: VkExtent2D
) {
    var current: SwapchainRecreatorProperties
        private set

    private val destroyers = mutableListOf<() -> Unit>()

    init {
        current = recreateProperties(null, initialExtent2D)
    }

    abstract fun createSwapchain(oldSwapchain: VkSwapchainKHR?, extent: VkExtent2D): VkSwapchainKHR?

    abstract fun createImageView(image: VkImage): VkImageView?

    abstract fun createRenderPass(): VkRenderPass?

    abstract fun createDepthImage(extent: VkExtent2D): VkImage?

    abstract fun createDepthImageView(depthImage: VkImage): VkImageView?

    abstract fun allocateDepthImageMemory(depthImage: VkImage): VkDeviceMemory?

    abstract fun allocateCommandBuffers(commandPool: VkCommandPool, size: Int): List<VkCommandBuffer>

    abstract fun createFramebuffer(
        swapchainImageView: VkImageView,
        renderPass: VkRenderPass,
        depthResource: SwapchainRecreatorDepthResource,
        extent: VkExtent2D
    ): VkFramebuffer?

    fun recreate(extent: VkExtent2D) {
        current = recreateProperties(current.swapchain, extent)
    }

    fun destroy() {
        destroyers.reversed().forEach { it.invoke() }
        destroyers.clear()
    }

    private fun recreateProperties(oldSwapchain: VkSwapchainKHR?, extent: VkExtent2D): SwapchainRecreatorProperties {
        val destroyers = mutableListOf<() -> Unit>()
        try {
            val newExtent = swapchainSupportDetails.chooseSwapExtent(extent)
            val swapchain = checkNotNull(createSwapchain(oldSwapchain, newExtent))
            destroyers.add { vk.destroySwapchainKHR(device, current.swapchain) }

            val swapchainImages = mutableListOf<VkImage>()
            vk.getSwapchainImagesKHR(device, swapchain, swapchainImages)

            val swapchainImageViews = swapchainImages.map {
                val imageView = checkNotNull(createImageView(it))
                destroyers.add { vk.destroyImageView(device, imageView) }
                imageView
            }

            val renderPass = checkNotNull(createRenderPass())
            destroyers.add { vk.destroyRenderPass(device, renderPass) }

            val depthResources = swapchainImages.map {
                val depthImage = checkNotNull(createDepthImage(newExtent))
                destroyers.add { vk.destroyImage(device, depthImage) }

                val depthImageMemory = checkNotNull(allocateDepthImageMemory(depthImage))
                destroyers.add { vk.freeMemory(device, depthImageMemory) }

                val depthImageView = checkNotNull(createDepthImageView(depthImage))
                destroyers.add { vk.destroyImageView(device, depthImageView) }

                SwapchainRecreatorDepthResource(depthImage, depthImageView, depthImageMemory)
            }

            val commandBuffers = allocateCommandBuffers(commandPool, swapchainImages.size)
            check(commandBuffers.isNotEmpty())
            destroyers.add { vk.freeCommandBuffers(device, commandPool, commandBuffers) }

            val framebuffers = swapchainImageViews.mapIndexed { index, imageView ->
                val framebuffer = checkNotNull(createFramebuffer(imageView, renderPass, depthResources[index], newExtent))
                destroyers.add { vk.destroyFramebuffer(device, framebuffer) }
                framebuffer
            }

            this.destroy()
            this.destroyers.addAll(destroyers)
            return object : SwapchainRecreatorProperties {
                override val extent: VkExtent2D = newExtent
                override val swapchain: VkSwapchainKHR = swapchain
                override val swapchainImages: List<VkImage> = swapchainImages.toList()
                override val swapchainImageViews: List<VkImageView> = swapchainImageViews
                override val renderPass: VkRenderPass = renderPass
                override val depthResources: List<SwapchainRecreatorDepthResource> = depthResources
                override val framebuffers: List<VkFramebuffer> = framebuffers
                override val commandBuffers: List<VkCommandBuffer> = commandBuffers
            }
        } catch (e: IllegalStateException) {
            destroyers.reversed().forEach { it.invoke() }
            throw e
        }
    }
}
