package io.github.inoutch.kotlin.vulkan.example

import io.github.inoutch.kotlin.vulkan.api.VK_QUEUE_FAMILY_IGNORED
import io.github.inoutch.kotlin.vulkan.api.VK_SUBPASS_EXTERNAL
import io.github.inoutch.kotlin.vulkan.api.VkAccessFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkApplicationInfo
import io.github.inoutch.kotlin.vulkan.api.VkAttachmentDescription
import io.github.inoutch.kotlin.vulkan.api.VkAttachmentLoadOp
import io.github.inoutch.kotlin.vulkan.api.VkAttachmentReference
import io.github.inoutch.kotlin.vulkan.api.VkAttachmentStoreOp
import io.github.inoutch.kotlin.vulkan.api.VkBlendFactor
import io.github.inoutch.kotlin.vulkan.api.VkBlendOp
import io.github.inoutch.kotlin.vulkan.api.VkBorderColor
import io.github.inoutch.kotlin.vulkan.api.VkBuffer
import io.github.inoutch.kotlin.vulkan.api.VkBufferCopy
import io.github.inoutch.kotlin.vulkan.api.VkBufferCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkBufferImageCopy
import io.github.inoutch.kotlin.vulkan.api.VkBufferUsageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkClearColorValue
import io.github.inoutch.kotlin.vulkan.api.VkClearDepthStencilValue
import io.github.inoutch.kotlin.vulkan.api.VkClearValue
import io.github.inoutch.kotlin.vulkan.api.VkColorComponentFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkColorSpaceKHR
import io.github.inoutch.kotlin.vulkan.api.VkCommandBuffer
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferAllocateInfo
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferBeginInfo
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferLevel
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferUsageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkCommandPool
import io.github.inoutch.kotlin.vulkan.api.VkCommandPoolCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkCompareOp
import io.github.inoutch.kotlin.vulkan.api.VkComponentMapping
import io.github.inoutch.kotlin.vulkan.api.VkCompositeAlphaFlagBitsKHR
import io.github.inoutch.kotlin.vulkan.api.VkCullModeFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorBufferInfo
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorImageInfo
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorPool
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorPoolCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorPoolSize
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorSet
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorSetAllocateInfo
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorSetLayout
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorSetLayoutBinding
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorSetLayoutCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorType
import io.github.inoutch.kotlin.vulkan.api.VkDevice
import io.github.inoutch.kotlin.vulkan.api.VkDeviceCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkDeviceMemory
import io.github.inoutch.kotlin.vulkan.api.VkDeviceQueueCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkDeviceSize
import io.github.inoutch.kotlin.vulkan.api.VkExtensionProperties
import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.VkExtent3D
import io.github.inoutch.kotlin.vulkan.api.VkFence
import io.github.inoutch.kotlin.vulkan.api.VkFenceCreateFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkFenceCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkFilter
import io.github.inoutch.kotlin.vulkan.api.VkFormat
import io.github.inoutch.kotlin.vulkan.api.VkFormatFeatureFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkFormatProperties
import io.github.inoutch.kotlin.vulkan.api.VkFramebuffer
import io.github.inoutch.kotlin.vulkan.api.VkFramebufferCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkFrontFace
import io.github.inoutch.kotlin.vulkan.api.VkGraphicsPipelineCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkImage
import io.github.inoutch.kotlin.vulkan.api.VkImageAspectFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkImageCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkImageLayout
import io.github.inoutch.kotlin.vulkan.api.VkImageMemoryBarrier
import io.github.inoutch.kotlin.vulkan.api.VkImageSubresourceLayers
import io.github.inoutch.kotlin.vulkan.api.VkImageSubresourceRange
import io.github.inoutch.kotlin.vulkan.api.VkImageTiling
import io.github.inoutch.kotlin.vulkan.api.VkImageType
import io.github.inoutch.kotlin.vulkan.api.VkImageUsageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkImageView
import io.github.inoutch.kotlin.vulkan.api.VkImageViewCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkImageViewType
import io.github.inoutch.kotlin.vulkan.api.VkIndexType
import io.github.inoutch.kotlin.vulkan.api.VkInstance
import io.github.inoutch.kotlin.vulkan.api.VkInstanceCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkLayerProperties
import io.github.inoutch.kotlin.vulkan.api.VkLogicOp
import io.github.inoutch.kotlin.vulkan.api.VkMemoryAllocateInfo
import io.github.inoutch.kotlin.vulkan.api.VkMemoryPropertyFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkMemoryRequirements
import io.github.inoutch.kotlin.vulkan.api.VkOffset2D
import io.github.inoutch.kotlin.vulkan.api.VkOffset3D
import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDevice
import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDeviceFeatures
import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDeviceMemoryProperties
import io.github.inoutch.kotlin.vulkan.api.VkPipeline
import io.github.inoutch.kotlin.vulkan.api.VkPipelineBindPoint
import io.github.inoutch.kotlin.vulkan.api.VkPipelineColorBlendAttachmentState
import io.github.inoutch.kotlin.vulkan.api.VkPipelineColorBlendStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineDepthStencilStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineInputAssemblyStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineLayout
import io.github.inoutch.kotlin.vulkan.api.VkPipelineLayoutCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineMultisampleStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineRasterizationStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineShaderStageCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineStageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkPipelineVertexInputStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineViewportStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPolygonMode
import io.github.inoutch.kotlin.vulkan.api.VkPresentInfoKHR
import io.github.inoutch.kotlin.vulkan.api.VkPresentModeKHR
import io.github.inoutch.kotlin.vulkan.api.VkPrimitiveTopology
import io.github.inoutch.kotlin.vulkan.api.VkQueue
import io.github.inoutch.kotlin.vulkan.api.VkQueueFamilyProperties
import io.github.inoutch.kotlin.vulkan.api.VkQueueFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkRect2D
import io.github.inoutch.kotlin.vulkan.api.VkRenderPass
import io.github.inoutch.kotlin.vulkan.api.VkRenderPassBeginInfo
import io.github.inoutch.kotlin.vulkan.api.VkRenderPassCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkResult
import io.github.inoutch.kotlin.vulkan.api.VkSampleCountFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkSampler
import io.github.inoutch.kotlin.vulkan.api.VkSamplerAddressMode
import io.github.inoutch.kotlin.vulkan.api.VkSamplerCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkSamplerMipmapMode
import io.github.inoutch.kotlin.vulkan.api.VkSemaphore
import io.github.inoutch.kotlin.vulkan.api.VkSemaphoreCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkShaderModule
import io.github.inoutch.kotlin.vulkan.api.VkShaderModuleCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkShaderStageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkSharingMode
import io.github.inoutch.kotlin.vulkan.api.VkStencilOp
import io.github.inoutch.kotlin.vulkan.api.VkStencilOpState
import io.github.inoutch.kotlin.vulkan.api.VkStructureType
import io.github.inoutch.kotlin.vulkan.api.VkSubmitInfo
import io.github.inoutch.kotlin.vulkan.api.VkSubpassContents
import io.github.inoutch.kotlin.vulkan.api.VkSubpassDependency
import io.github.inoutch.kotlin.vulkan.api.VkSubpassDescription
import io.github.inoutch.kotlin.vulkan.api.VkSurface
import io.github.inoutch.kotlin.vulkan.api.VkSurfaceCapabilitiesKHR
import io.github.inoutch.kotlin.vulkan.api.VkSurfaceFormatKHR
import io.github.inoutch.kotlin.vulkan.api.VkSwapchainCreateInfoKHR
import io.github.inoutch.kotlin.vulkan.api.VkSwapchainKHR
import io.github.inoutch.kotlin.vulkan.api.VkVertexInputAttributeDescription
import io.github.inoutch.kotlin.vulkan.api.VkVertexInputBindingDescription
import io.github.inoutch.kotlin.vulkan.api.VkVertexInputRate
import io.github.inoutch.kotlin.vulkan.api.VkViewport
import io.github.inoutch.kotlin.vulkan.api.VkWriteDescriptorSet
import io.github.inoutch.kotlin.vulkan.api.vk
import io.github.inoutch.kotlin.vulkan.example.math.Mat4
import io.github.inoutch.kotlin.vulkan.example.math.Vec2
import io.github.inoutch.kotlin.vulkan.example.math.Vec3
import io.github.inoutch.kotlin.vulkan.example.shader.tutorialDepthBufferingFrag
import io.github.inoutch.kotlin.vulkan.example.shader.tutorialDepthBufferingVert
import io.github.inoutch.kotlin.vulkan.utility.MappedMemory
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty
import io.github.inoutch.kotlin.vulkan.utility.Version
import io.github.inoutch.kotlin.vulkan.utility.getProperties
import io.github.inoutch.kotlin.vulkan.utility.getProperty
import kotlin.math.PI
import kotlin.math.max

const val FLOAT_BYTE_SIZE = 4
const val INT_BYTE_SIZE = 4

const val MAX_FRAMES_IN_FLIGHT = 2

val validationLayers = listOf("VK_LAYER_KHRONOS_validation")

const val enableValidationLayers = false

data class QueueFamilyIndices(
    val graphicsFamily: Int,
    val presentFamily: Int
) {
    fun isComplete(): Boolean {
        return graphicsFamily != -1 && presentFamily != -1
    }
}

data class SwapChainSupportDetails(
    val capabilities: VkSurfaceCapabilitiesKHR,
    val formats: List<VkSurfaceFormatKHR>,
    val presentModes: List<VkPresentModeKHR>
)

data class Vertex(
    val pos: Vec3,
    val col: Vec3,
    val texCoord: Vec2
) {
    companion object {
        const val SIZE = (Vec3.SIZE + Vec3.SIZE + Vec2.SIZE) * FLOAT_BYTE_SIZE

        fun getBindingDescription(): VkVertexInputBindingDescription {
            return VkVertexInputBindingDescription(
                    0,
                    SIZE,
                    VkVertexInputRate.VK_VERTEX_INPUT_RATE_VERTEX
            )
        }

        fun getAttributeDescriptions(): List<VkVertexInputAttributeDescription> {
            val value1 = VkVertexInputAttributeDescription(
                    0,
                    0,
                    VkFormat.VK_FORMAT_R32G32B32_SFLOAT,
                    0
            )
            val value2 = VkVertexInputAttributeDescription(
                    1,
                    0,
                    VkFormat.VK_FORMAT_R32G32B32_SFLOAT,
                    Vec3.SIZE
            )
            val value3 = VkVertexInputAttributeDescription(
                    2,
                    0,
                    VkFormat.VK_FORMAT_R32G32_SFLOAT,
                    Vec3.SIZE + Vec3.SIZE
            )
            return listOf(value1, value2, value3)
        }
    }
}

class UniformBufferObject(
    private val model: Mat4,
    private val view: Mat4,
    private val proj: Mat4
) {
    companion object {
        const val SIZE = (Mat4.SIZE * 3).toLong()
    }

    fun flatten(): FloatArray {
        return floatArrayOf(
                *model.flatten(),
                *view.flatten(),
                *proj.flatten()
        )
    }
}

val vertices = listOf(
        listOf(listOf(-0.5f, -0.5f, 0.0f), listOf(-0.5f, -0.5f, 0.0f), listOf(1.0f, 0.0f)),
        listOf(listOf(0.5f, -0.5f, 0.0f), listOf(0.0f, 1.0f, 0.0f), listOf(0.0f, 0.0f)),
        listOf(listOf(0.5f, 0.5f, 0.0f), listOf(0.0f, 0.0f, 1.0f), listOf(0.0f, 1.0f)),
        listOf(listOf(-0.5f, 0.5f, 0.0f), listOf(1.0f, 1.0f, 1.0f), listOf(1.0f, 1.0f)),

        listOf(listOf(-0.5f, -0.5f, -0.5f), listOf(1.0f, 0.0f, 0.0f), listOf(1.0f, 0.0f)),
        listOf(listOf(0.5f, -0.5f, -0.5f), listOf(0.0f, 1.0f, 0.0f), listOf(0.0f, 0.0f)),
        listOf(listOf(0.5f, 0.5f, -0.5f), listOf(0.0f, 0.0f, 1.0f), listOf(0.0f, 1.0f)),
        listOf(listOf(-0.5f, 0.5f, -0.5f), listOf(1.0f, 1.0f, 1.0f), listOf(1.0f, 1.0f))
).flatten().flatten().toFloatArray()

val indices = listOf(
        0, 1, 2, 2, 3, 0,
        4, 5, 6, 6, 7, 4
)

class HelloTriangleApplication(private val delegate: Delegate) {
    interface Delegate {
        fun getSwapchainExtensionNames(): List<String>
        fun createSurface(instance: VkInstance, surface: MutableProperty<VkSurface>): VkResult
        fun getRequiredInstanceExtensions(): List<String>
        fun getFramebufferSize(width: MutableProperty<Int>, height: MutableProperty<Int>)
        fun destroy()
    }

    private lateinit var instance: VkInstance
    private lateinit var surface: VkSurface

    private val physicalDevice: VkPhysicalDevice
        get() = checkNotNull(physicalDeviceImpl)

    private var physicalDeviceImpl: VkPhysicalDevice? = null
    private lateinit var device: VkDevice

    private lateinit var graphicsQueue: VkQueue
    private lateinit var presentQueue: VkQueue

    private lateinit var swapChain: VkSwapchainKHR
    private lateinit var swapChainImages: List<VkImage>
    private lateinit var swapChainImageFormat: VkFormat
    private lateinit var swapChainExtent: VkExtent2D
    private lateinit var swapChainImageViews: List<VkImageView>
    private lateinit var swapChainFramebuffers: List<VkFramebuffer>
    private lateinit var renderPass: VkRenderPass
    private lateinit var descriptorSetLayout: VkDescriptorSetLayout
    private lateinit var pipelineLayout: VkPipelineLayout
    private lateinit var graphicsPipeline: VkPipeline

    private lateinit var commandPool: VkCommandPool

    private lateinit var depthImage: VkImage
    private lateinit var depthImageMemory: VkDeviceMemory
    private lateinit var depthImageView: VkImageView

    private lateinit var textureImage: VkImage
    private lateinit var textureImageMemory: VkDeviceMemory
    private lateinit var textureImageView: VkImageView
    private lateinit var textureSampler: VkSampler

    private lateinit var vertexBuffer: VkBuffer
    private lateinit var vertexBufferMemory: VkDeviceMemory
    private lateinit var indexBuffer: VkBuffer
    private lateinit var indexBufferMemory: VkDeviceMemory

    private lateinit var uniformBuffers: List<VkBuffer>
    private lateinit var uniformBuffersMemory: List<VkDeviceMemory>

    private lateinit var descriptorPool: VkDescriptorPool
    private lateinit var descriptorSets: List<VkDescriptorSet>

    private lateinit var commandBuffers: List<VkCommandBuffer>

    private lateinit var imageAvailableSemaphores: List<VkSemaphore>
    private lateinit var renderFinishedSemaphores: List<VkSemaphore>

    private lateinit var inFlightFences: List<VkFence>
    private lateinit var imagesInFlight: MutableList<VkFence?>

    private var currentFrame = 0

    private var framebufferResized = false

    fun resize() {
        framebufferResized = true
    }

    fun initVulkan() {
        createInstance()
        createSurface()
        pickPhysicalDevice()
        createLogicalDevice()
        createSwapChain()
        createImageViews()
        createRenderPass()
        createDescriptorSetLayout()
        createGraphicsPipeline()
        createCommandPool()
        createDepthResources()
        createFramebuffers()
        createTextureImage()
        createTextureImageView()
        createTextureSampler()
        createVertexBuffer()
        createIndexBuffer()
        createUniformBuffers()
        createDescriptorPool()
        createDescriptorSets()
        createCommandBuffers()
        createSyncObjects()
    }

    private fun cleanupSwapChain() {
        vk.destroyImageView(device, depthImageView)
        vk.destroyImage(device, depthImage)
        vk.freeMemory(device, depthImageMemory)

        for (framebuffer in swapChainFramebuffers) {
            vk.destroyFramebuffer(device, framebuffer)
        }

        vk.freeCommandBuffers(device, commandPool, commandBuffers)

        vk.destroyPipeline(device, graphicsPipeline)
        vk.destroyPipelineLayout(device, pipelineLayout)
        vk.destroyRenderPass(device, renderPass)

        for (imageView in swapChainImageViews) {
            vk.destroyImageView(device, imageView)
        }

        vk.destroyCommandPool(device, commandPool)

        vk.destroyDevice(device)

        for (i in swapChainImages.indices) {
            vk.destroyBuffer(device, uniformBuffers[i])
            vk.freeMemory(device, uniformBuffersMemory[i])
        }

        vk.destroyDescriptorPool(
                device,
                descriptorPool
        )
    }

    fun cleanup() {
        vk.deviceWaitIdle(device)

        cleanupSwapChain()

        vk.destroySampler(device, textureSampler)
        vk.destroyImageView(device, textureImageView)

        vk.destroyImage(device, textureImage)
        vk.freeMemory(device, textureImageMemory)

        vk.destroyDescriptorSetLayout(device, descriptorSetLayout)

        vk.destroyBuffer(device, indexBuffer)
        vk.freeMemory(device, indexBufferMemory)

        vk.destroyBuffer(device, vertexBuffer)
        vk.freeMemory(device, vertexBufferMemory)

        for (i in 0 until MAX_FRAMES_IN_FLIGHT) {
            vk.destroySemaphore(device, renderFinishedSemaphores[i])
            vk.destroySemaphore(device, imageAvailableSemaphores[i])
            vk.destroyFence(device, inFlightFences[i])
        }

        vk.destroyCommandPool(device, commandPool)

        vk.destroyDevice(device)

        vk.destroySurfaceKHR(instance, surface)
        vk.destroyInstance(instance)

        delegate.destroy()
    }

    private fun recreateSwapChain() {
        val widthRef = MutableProperty<Int>()
        val heightRef = MutableProperty<Int>()

        delegate.getFramebufferSize(widthRef, heightRef)

        vk.deviceWaitIdle(device)

        cleanupSwapChain()

        createSwapChain()
        createImageViews()
        createRenderPass()
        createGraphicsPipeline()
        createDepthResources()
        createFramebuffers()
        createUniformBuffers()
        createDescriptorPool()
        createDescriptorSets()
        createCommandBuffers()
    }

    private fun createInstance() {
        check(!enableValidationLayers || checkValidationLayerSupport()) {
            "Validation layers requested, but not available"
        }

        val appInfo = VkApplicationInfo(
                VkStructureType.VK_STRUCTURE_TYPE_APPLICATION_INFO,
                applicationName = "Hello Triangle",
                applicationVersion = Version(1, 0, 0),
                engineName = "No Engine",
                engineVersion = Version(1, 0, 0),
                apiVersion = Version(1, 0, 3)
        )

        val extensions = getRequiredExtensions()
        val createInfo = VkInstanceCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO,
                0,
                appInfo,
                listOf(),
                extensions
        )

        instance = getProperty { vk.createInstance(createInfo, it) }
    }

    private fun createSurface() {
        surface = getProperty { delegate.createSurface(instance, it) }
    }

    private fun pickPhysicalDevice() {
        val devices = getProperties<VkPhysicalDevice> { vk.enumeratePhysicalDevices(instance, it) }
        for (device in devices) {
            if (isDeviceSuitable(device)) {
                physicalDeviceImpl = device
            }
        }

        checkNotNull(physicalDeviceImpl) {
            "Failed to find a suitable GPU"
        }
    }

    private fun createLogicalDevice() {
        val indices = findQueueFamilies(physicalDevice)

        val queueCreateInfos = mutableListOf<VkDeviceQueueCreateInfo>()
        val uniqueQueueFamilies = if (indices.graphicsFamily == indices.presentFamily) {
            listOf(indices.graphicsFamily)
        } else {
            listOf(indices.graphicsFamily, indices.presentFamily)
        }

        val queuePriority = 1.0f
        for (queueFamily in uniqueQueueFamilies) {
            val createInfo = VkDeviceQueueCreateInfo(
                    VkStructureType.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO,
                    listOf(),
                    queueFamily,
                    listOf(queuePriority)
            )
            queueCreateInfos.add(createInfo)
        }

        val deviceFeatures = VkPhysicalDeviceFeatures(
                robustBufferAccess = false,
                fullDrawIndexUint32 = false,
                imageCubeArray = false,
                independentBlend = false,
                geometryShader = false,
                tessellationShader = false,
                sampleRateShading = false,
                dualSrcBlend = false,
                logicOp = false,
                multiDrawIndirect = false,
                drawIndirectFirstInstance = false,
                depthClamp = false,
                depthBiasClamp = false,
                fillModeNonSolid = false,
                depthBounds = false,
                wideLines = false,
                largePoints = false,
                alphaToOne = false,
                multiViewport = false,
                samplerAnisotropy = true
        )

        val createInfo = VkDeviceCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO,
                flags = listOf(),
                queueCreateInfos = queueCreateInfos,
                enabledLayerNames = listOf(),
                enabledExtensionNames = delegate.getSwapchainExtensionNames(),
                enabledFeatures = deviceFeatures
        )
        device = getProperty { vk.createDevice(physicalDevice, createInfo, it) }

        graphicsQueue = getProperty { vk.getDeviceQueue(device, indices.graphicsFamily, 0, it) }
        presentQueue = if (indices.graphicsFamily == indices.presentFamily) {
            graphicsQueue
        } else {
            getProperty { vk.getDeviceQueue(device, indices.presentFamily, 0, it) }
        }
    }

    private fun createSwapChain() {
        val swapChainSupport = querySwapChainSupport(physicalDevice)

        val surfaceFormat = chooseSwapSurfaceFormat(swapChainSupport.formats)
        val presentMode = chooseSwapPresentMode(swapChainSupport.presentModes)
        val extent = chooseSwapExtent(swapChainSupport.capabilities)

        var imageCount = swapChainSupport.capabilities.minImageCount + 1
        if (swapChainSupport.capabilities.maxImageCount > 0 && imageCount > swapChainSupport.capabilities.maxImageCount) {
            imageCount = swapChainSupport.capabilities.maxImageCount
        }

        val indices = findQueueFamilies(physicalDevice)
        val queueFamilyIndices = listOf(indices.graphicsFamily, indices.graphicsFamily)

        val createInfo = VkSwapchainCreateInfoKHR(
                VkStructureType.VK_STRUCTURE_TYPE_SWAPCHAIN_CREATE_INFO_KHR,
                listOf(),
                surface,
                imageCount,
                surfaceFormat.format,
                surfaceFormat.colorSpace,
                extent,
                1,
                listOf(VkImageUsageFlagBits.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT),
                if (indices.graphicsFamily != indices.presentFamily) {
                    VkSharingMode.VK_SHARING_MODE_CONCURRENT
                } else {
                    VkSharingMode.VK_SHARING_MODE_EXCLUSIVE
                },
                if (indices.graphicsFamily != indices.presentFamily) {
                    queueFamilyIndices
                } else {
                    emptyList()
                },
                swapChainSupport.capabilities.currentTransform,
                VkCompositeAlphaFlagBitsKHR.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR,
                presentMode,
                true,
                null
        )

        swapChain = getProperty { vk.createSwapchainKHR(device, createInfo, it) }
        swapChainImages = getProperties { vk.getSwapchainImagesKHR(device, swapChain, it) }
        swapChainImageFormat = surfaceFormat.format
        swapChainExtent = extent
    }

    private fun createImageViews() {
        swapChainImageViews = swapChainImages.map {
            createImageView(it, swapChainImageFormat, listOf(VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT))
        }
    }

    private fun createRenderPass() {
        val colorAttachment = VkAttachmentDescription(
                flags = listOf(),
                format = swapChainImageFormat,
                samples = listOf(VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT),
                loadOp = VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_CLEAR,
                storeOp = VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_STORE,
                stencilLoadOp = VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_DONT_CARE,
                stencilStoreOp = VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_DONT_CARE,
                initialLayout = VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED,
                finalLayout = VkImageLayout.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR
        )

        val depthAttachment = VkAttachmentDescription(
                flags = listOf(),
                format = findDepthFormat(),
                samples = listOf(VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT),
                loadOp = VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_CLEAR,
                storeOp = VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_DONT_CARE,
                stencilLoadOp = VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_DONT_CARE,
                stencilStoreOp = VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_DONT_CARE,
                initialLayout = VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED,
                finalLayout = VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL
        )

        val colorAttachmentRef = VkAttachmentReference(
                attachment = 0,
                layout = VkImageLayout.VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL
        )

        val depthAttachmentRef = VkAttachmentReference(
                attachment = 1,
                layout = VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL
        )

        val subpass = VkSubpassDescription(
                flags = listOf(),
                pipelineBindPoint = VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS,
                inputAttachments = listOf(),
                colorAttachments = listOf(colorAttachmentRef),
                resolveAttachments = listOf(),
                depthStencilAttachment = depthAttachmentRef,
                preserveAttachments = listOf()
        )

        val dependency = VkSubpassDependency(
                srcSubpassIndex = VK_SUBPASS_EXTERNAL,
                dstSubpassIndex = 0,
                srcStageMask = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT),
                dstStageMask = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT),
                srcAccessMask = listOf(),
                dstAccessMask = listOf(
                        VkAccessFlagBits.VK_ACCESS_COLOR_ATTACHMENT_READ_BIT,
                        VkAccessFlagBits.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT
                ),
                dependencyFlags = listOf()
        )

        val attachments = listOf(colorAttachment, depthAttachment)
        val renderPassInfo = VkRenderPassCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_RENDER_PASS_CREATE_INFO,
                0,
                attachments,
                listOf(subpass),
                listOf(dependency)
        )

        renderPass = getProperty { vk.createRenderPass(device, renderPassInfo, it) }
    }

    private fun createDescriptorSetLayout() {
        val uboLayoutBinding = VkDescriptorSetLayoutBinding(
                0,
                VkDescriptorType.VK_DESCRIPTOR_TYPE_UNIFORM_BUFFER,
                1,
                listOf(VkShaderStageFlagBits.VK_SHADER_STAGE_VERTEX_BIT),
                null
        )

        val samplerLayoutBinding = VkDescriptorSetLayoutBinding(
                1,
                VkDescriptorType.VK_DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER,
                1,
                listOf(VkShaderStageFlagBits.VK_SHADER_STAGE_FRAGMENT_BIT),
                null
        )

        val bindings = listOf(uboLayoutBinding, samplerLayoutBinding)
        val layoutInfo = VkDescriptorSetLayoutCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_DESCRIPTOR_SET_LAYOUT_CREATE_INFO,
                flags = listOf(),
                bindings = bindings
        )

        descriptorSetLayout = getProperty { vk.createDescriptorSetLayout(device, layoutInfo, it) }
    }

    private fun createGraphicsPipeline() {
        val vertShaderCode = tutorialDepthBufferingVert
        val fragShaderCode = tutorialDepthBufferingFrag

        val vertShaderModule = createShaderModule(vertShaderCode)
        val fragShaderModule = createShaderModule(fragShaderCode)

        val vertShaderStageInfo = VkPipelineShaderStageCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO,
                flags = listOf(),
                stage = listOf(VkShaderStageFlagBits.VK_SHADER_STAGE_VERTEX_BIT),
                module = vertShaderModule,
                name = "main",
                specializationInfo = null
        )

        val fragShaderStageInfo = VkPipelineShaderStageCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO,
                flags = listOf(),
                stage = listOf(VkShaderStageFlagBits.VK_SHADER_STAGE_FRAGMENT_BIT),
                module = fragShaderModule,
                name = "main",
                specializationInfo = null
        )

        val shaderStages = listOf(vertShaderStageInfo, fragShaderStageInfo)

        val bindingDescription = Vertex.getBindingDescription()
        val attributeDescriptions = Vertex.getAttributeDescriptions()
        val vertexInputInfo = VkPipelineVertexInputStateCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_VERTEX_INPUT_STATE_CREATE_INFO,
                flags = 0,
                vertexBindingDescriptions = listOf(bindingDescription),
                vertexAttributeDescriptions = attributeDescriptions
        )

        val inputAssembly = VkPipelineInputAssemblyStateCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_INPUT_ASSEMBLY_STATE_CREATE_INFO,
                flags = 0,
                topology = VkPrimitiveTopology.VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST,
                primitiveRestartEnable = false
        )

        val viewport = VkViewport(
                x = 0.0f,
                y = 0.0f,
                width = swapChainExtent.width.toFloat(),
                height = swapChainExtent.height.toFloat(),
                minDepth = 0.0f,
                maxDepth = 1.0f
        )
        val scissor = VkRect2D(
                VkOffset2D(0, 0),
                swapChainExtent
        )

        val viewportState = VkPipelineViewportStateCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO,
                flags = 0,
                viewports = listOf(viewport),
                scissors = listOf(scissor)
        )

        val rasterizer = VkPipelineRasterizationStateCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_RASTERIZATION_STATE_CREATE_INFO,
                flags = 0,
                polygonMode = VkPolygonMode.VK_POLYGON_MODE_FILL,
                cullMode = VkCullModeFlagBits.VK_CULL_MODE_BACK_BIT,
                frontFace = VkFrontFace.VK_FRONT_FACE_COUNTER_CLOCKWISE,
                depthClampEnable = false,
                rasterizerDiscardEnable = false,
                depthBiasEnable = false,
                depthBiasConstantFactor = 0.0f,
                depthBiasClamp = 0.0f,
                depthBiasSlopeFactor = 0.0f,
                lineWidth = 1.0f
        )

        val multisampling = VkPipelineMultisampleStateCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_MULTISAMPLE_STATE_CREATE_INFO,
                flags = 0,
                rasterizationSamples = listOf(VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT),
                sampleShadingEnable = false,
                minSampleShading = 0.0f,
                sampleMask = null,
                alphaToCoverageEnable = false,
                alphaToOneEnable = false
        )

        val depthStencil = VkPipelineDepthStencilStateCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_DEPTH_STENCIL_STATE_CREATE_INFO,
                flags = 0,
                depthTestEnable = true,
                depthWriteEnable = true,
                depthCompareOp = VkCompareOp.VK_COMPARE_OP_LESS,
                depthBoundsTestEnable = false,
                stencilTestEnable = false,
                front = VkStencilOpState(
                        VkStencilOp.VK_STENCIL_OP_KEEP,
                        VkStencilOp.VK_STENCIL_OP_KEEP,
                        VkStencilOp.VK_STENCIL_OP_KEEP,
                        VkCompareOp.VK_COMPARE_OP_NEVER,
                        0,
                        0,
                        0
                ),
                back = VkStencilOpState(
                        VkStencilOp.VK_STENCIL_OP_KEEP,
                        VkStencilOp.VK_STENCIL_OP_KEEP,
                        VkStencilOp.VK_STENCIL_OP_KEEP,
                        VkCompareOp.VK_COMPARE_OP_NEVER,
                        0,
                        0,
                        0
                ),
                minDepthBounds = 0.0f,
                maxDepthBounds = 0.0f
        )

        val colorBlendAttachment = VkPipelineColorBlendAttachmentState(
                blendEnable = false,
                srcColorBlendFactor = VkBlendFactor.VK_BLEND_FACTOR_ZERO,
                dstColorBlendFactor = VkBlendFactor.VK_BLEND_FACTOR_ZERO,
                colorBlendOp = VkBlendOp.VK_BLEND_OP_ADD,
                srcAlphaBlendFactor = VkBlendFactor.VK_BLEND_FACTOR_ZERO,
                dstAlphaBlendFactor = VkBlendFactor.VK_BLEND_FACTOR_ZERO,
                alphaBlendOp = VkBlendOp.VK_BLEND_OP_ADD,
                colorWriteMask = listOf(
                        VkColorComponentFlagBits.VK_COLOR_COMPONENT_R_BIT,
                        VkColorComponentFlagBits.VK_COLOR_COMPONENT_G_BIT,
                        VkColorComponentFlagBits.VK_COLOR_COMPONENT_B_BIT,
                        VkColorComponentFlagBits.VK_COLOR_COMPONENT_A_BIT
                )
        )

        val colorBlending = VkPipelineColorBlendStateCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_COLOR_BLEND_STATE_CREATE_INFO,
                flags = 0,
                logicOpEnable = false,
                logicOp = VkLogicOp.VK_LOGIC_OP_COPY,
                attachments = listOf(colorBlendAttachment),
                blendConstants = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f)
        )

        val pipelineLayoutInfo = VkPipelineLayoutCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_LAYOUT_CREATE_INFO,
                flags = 0,
                setLayouts = listOf(descriptorSetLayout),
                pushConstantRanges = listOf()
        )

        pipelineLayout = getProperty { vk.createPipelineLayout(device, pipelineLayoutInfo, it) }

        val pipelineInfo = VkGraphicsPipelineCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_GRAPHICS_PIPELINE_CREATE_INFO,
                flags = listOf(),
                stages = shaderStages,
                vertexInputState = vertexInputInfo,
                inputAssemblyState = inputAssembly,
                tessellationState = null,
                viewportState = viewportState,
                rasterizationState = rasterizer,
                multisampleState = multisampling,
                depthStencilState = depthStencil,
                colorBlendState = colorBlending,
                dynamicState = null,
                layout = pipelineLayout,
                renderPass = renderPass,
                subpass = 0,
                basePipelineHandle = null,
                basePipelineIndex = 0
        )

        graphicsPipeline = getProperty { vk.createGraphicsPipelines(device, null, listOf(pipelineInfo), it) }

        vk.destroyShaderModule(device, vertShaderModule)
        vk.destroyShaderModule(device, fragShaderModule)
    }

    private fun createFramebuffers() {
        swapChainFramebuffers = swapChainImageViews.map {
            val attachments = listOf(it, depthImageView)

            val framebufferInfo = VkFramebufferCreateInfo(
                    VkStructureType.VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO,
                    flags = listOf(),
                    renderPass = renderPass,
                    attachments = attachments,
                    width = swapChainExtent.width,
                    height = swapChainExtent.height,
                    layers = 1
            )
            getProperty<VkFramebuffer> { vk.createFramebuffer(device, framebufferInfo, it) }
        }
    }

    private fun createCommandPool() {
        val queueFamilyIndices = findQueueFamilies(physicalDevice)
        val poolInfo = VkCommandPoolCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO,
                flags = listOf(),
                queueFamilyIndex = queueFamilyIndices.graphicsFamily
        )

        commandPool = getProperty { vk.createCommandPool(device, poolInfo, it) }
    }

    private fun createDepthResources() {
        val depthFormat = findDepthFormat()
        val depthImageRef = MutableProperty<VkImage>()
        val depthImageMemoryRef = MutableProperty<VkDeviceMemory>()

        createImage(
                swapChainExtent.width,
                swapChainExtent.height,
                depthFormat,
                VkImageTiling.VK_IMAGE_TILING_OPTIMAL,
                listOf(VkImageUsageFlagBits.VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT),
                listOf(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT),
                depthImageRef,
                depthImageMemoryRef
        )

        depthImage = checkNotNull(depthImageRef.value)
        depthImageMemory = checkNotNull(depthImageMemoryRef.value)

        depthImageView = createImageView(
                depthImage,
                depthFormat,
                listOf(VkImageAspectFlagBits.VK_IMAGE_ASPECT_DEPTH_BIT)
        )
    }

    private fun findSupportedFormat(
        candidates: List<VkFormat>,
        tiling: VkImageTiling,
        features: List<VkFormatFeatureFlagBits>
    ): VkFormat {
        val computedFeatures = features.sumBy { it.bit }
        return checkNotNull(candidates.find { candidate ->
            val props: VkFormatProperties = getProperty {
                vk.getPhysicalDeviceFormatProperties(physicalDevice, candidate, it)
            }
            when (tiling) {
                VkImageTiling.VK_IMAGE_TILING_LINEAR -> {
                    (props.linearTilingFeatures.sumBy { it.bit } and computedFeatures) == computedFeatures
                }
                VkImageTiling.VK_IMAGE_TILING_OPTIMAL -> {
                    (props.optimalTilingFeatures.sumBy { it.bit } and computedFeatures) == computedFeatures
                }
                else -> false
            }
        })
    }

    private fun findDepthFormat(): VkFormat {
        return findSupportedFormat(
                listOf(
                        VkFormat.VK_FORMAT_D32_SFLOAT,
                        VkFormat.VK_FORMAT_D32_SFLOAT_S8_UINT,
                        VkFormat.VK_FORMAT_D24_UNORM_S8_UINT
                ),
                VkImageTiling.VK_IMAGE_TILING_OPTIMAL,
                listOf(VkFormatFeatureFlagBits.VK_FORMAT_FEATURE_DEPTH_STENCIL_ATTACHMENT_BIT)
        )
    }

    private fun hasStencilComponent(format: VkFormat): Boolean {
        return (
                format == VkFormat.VK_FORMAT_D32_SFLOAT_S8_UINT ||
                        format == VkFormat.VK_FORMAT_D24_UNORM_S8_UINT
                )
    }

    private fun createTextureImage() {
        // TODO: Set actual image bytes
        val texWidth = 1
        val texHeight = 1
        val pixels = byteArrayOf(-1, -1, -1, -1)
        val imageSize = texWidth * texHeight * 4

        val stagingBufferRef = MutableProperty<VkBuffer>()
        val stagingBufferMemoryRef = MutableProperty<VkDeviceMemory>()
        createBuffer(
                imageSize.toLong(),
                listOf(VkBufferUsageFlagBits.VK_BUFFER_USAGE_TRANSFER_SRC_BIT),
                listOf(
                        VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT,
                        VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_COHERENT_BIT
                ),
                stagingBufferRef,
                stagingBufferMemoryRef
        )

        val stagingBuffer = checkNotNull(stagingBufferRef.value)
        val stagingBufferMemory = checkNotNull(stagingBufferMemoryRef.value)
        val data: MappedMemory = getProperty {
            vk.mapMemory(
                    device,
                    stagingBufferMemory,
                    0,
                    imageSize.toLong(),
                    listOf(),
                    it
            )
        }
        data.copy(0, pixels.size.toLong(), pixels)
        data.destroy()

        val textureImageRef = MutableProperty<VkImage>()
        val textureImageMemoryRef = MutableProperty<VkDeviceMemory>()
        createImage(
                texWidth,
                texHeight,
                VkFormat.VK_FORMAT_R8G8B8A8_SRGB,
                VkImageTiling.VK_IMAGE_TILING_OPTIMAL,
                listOf(
                        VkImageUsageFlagBits.VK_IMAGE_USAGE_TRANSFER_DST_BIT,
                        VkImageUsageFlagBits.VK_IMAGE_USAGE_SAMPLED_BIT
                ),
                listOf(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT),
                textureImageRef,
                textureImageMemoryRef
        )

        textureImage = checkNotNull(textureImageRef.value)
        textureImageMemory = checkNotNull(textureImageMemoryRef.value)

        transitionImageLayout(
                textureImage,
                VkFormat.VK_FORMAT_R8G8B8A8_SRGB,
                VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED,
                VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL
        )
        copyBufferToImage(stagingBuffer, textureImage, texWidth, texHeight)
        transitionImageLayout(
                textureImage,
                VkFormat.VK_FORMAT_R8G8B8A8_SRGB,
                VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL,
                VkImageLayout.VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL
        )

        vk.destroyBuffer(device, stagingBuffer)
        vk.freeMemory(device, stagingBufferMemory)
    }

    private fun createTextureImageView() {
        textureImageView = createImageView(
                textureImage,
                VkFormat.VK_FORMAT_R8G8B8A8_SRGB,
                listOf(VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT)
        )
    }

    private fun createTextureSampler() {
        val samplerInfo = VkSamplerCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_SAMPLER_CREATE_INFO,
                flags = 0,
                magFilter = VkFilter.VK_FILTER_LINEAR,
                minFilter = VkFilter.VK_FILTER_LINEAR,
                mipmapMode = VkSamplerMipmapMode.VK_SAMPLER_MIPMAP_MODE_LINEAR,
                addressModeU = VkSamplerAddressMode.VK_SAMPLER_ADDRESS_MODE_REPEAT,
                addressModeV = VkSamplerAddressMode.VK_SAMPLER_ADDRESS_MODE_REPEAT,
                addressModeW = VkSamplerAddressMode.VK_SAMPLER_ADDRESS_MODE_REPEAT,
                mipLodBias = 0.0f,
                anisotropyEnable = true,
                maxAnisotropy = 16.0f,
                compareEnable = false,
                compareOp = VkCompareOp.VK_COMPARE_OP_ALWAYS,
                minLod = 0.0f,
                maxLod = 0.0f,
                borderColor = VkBorderColor.VK_BORDER_COLOR_FLOAT_TRANSPARENT_BLACK,
                unnormalizedCoordinates = false
        )

        textureSampler = getProperty { vk.createSampler(device, samplerInfo, it) }
    }

    private fun createImageView(image: VkImage, format: VkFormat, aspectFlags: List<VkImageAspectFlagBits>): VkImageView {
        val viewInfo = VkImageViewCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO,
                listOf(),
                image,
                VkImageViewType.VK_IMAGE_VIEW_TYPE_2D,
                format,
                VkComponentMapping(),
                VkImageSubresourceRange(
                        aspectFlags,
                        0,
                        1,
                        0,
                        1
                )
        )
        return getProperty { vk.createImageView(device, viewInfo, it) }
    }

    private fun createImage(
        width: Int,
        height: Int,
        format: VkFormat,
        tiling: VkImageTiling,
        usage: List<VkImageUsageFlagBits>,
        properties: List<VkMemoryPropertyFlagBits>,
        image: MutableProperty<VkImage>,
        imageMemory: MutableProperty<VkDeviceMemory>
    ) {
        val imageInfo = VkImageCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO,
                flags = listOf(),
                imageType = VkImageType.VK_IMAGE_TYPE_2D,
                format = format,
                extent = VkExtent3D(width, height, 1),
                mipLevels = 1,
                arrayLayers = 1,
                samples = VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT,
                tiling = tiling,
                usage = usage,
                sharingMode = VkSharingMode.VK_SHARING_MODE_EXCLUSIVE,
                queueFamilyIndices = listOf(),
                initialLayout = VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED
        )
        vk.createImage(device, imageInfo, image)
        val imageNotNull = checkNotNull(image.value)

        val memRequirements = getProperty<VkMemoryRequirements> {
            vk.getImageMemoryRequirements(device, imageNotNull, it)
        }
        val allocInfo = VkMemoryAllocateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO,
                memRequirements.size,
                findMemoryType(memRequirements.memoryTypeBits, properties)
        )
        vk.allocateMemory(device, allocInfo, imageMemory)
        val imageMemoryNotNull = checkNotNull(imageMemory.value)

        vk.bindImageMemory(device, imageNotNull, imageMemoryNotNull, 0)
    }

    private fun transitionImageLayout(
        image: VkImage,
        format: VkFormat,
        oldLayout: VkImageLayout,
        newLayout: VkImageLayout
    ) {
        val commandBuffer = beginSingleTimeCommands()

        val srcAccessMask: List<VkAccessFlagBits>
        val dstAccessMask: List<VkAccessFlagBits>
        val sourceStage: VkPipelineStageFlagBits
        val destinationStage: VkPipelineStageFlagBits

        if (
                oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED &&
                newLayout == VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL
        ) {
            srcAccessMask = listOf()
            dstAccessMask = listOf(VkAccessFlagBits.VK_ACCESS_TRANSFER_WRITE_BIT)

            sourceStage = VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT
            destinationStage = VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TRANSFER_BIT
        } else if (
                oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL &&
                newLayout == VkImageLayout.VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL
        ) {
            srcAccessMask = listOf(VkAccessFlagBits.VK_ACCESS_TRANSFER_WRITE_BIT)
            dstAccessMask = listOf(VkAccessFlagBits.VK_ACCESS_SHADER_READ_BIT)

            sourceStage = VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TRANSFER_BIT
            destinationStage = VkPipelineStageFlagBits.VK_PIPELINE_STAGE_FRAGMENT_SHADER_BIT
        } else if (
            oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED &&
            newLayout == VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL
        ) {
            srcAccessMask = listOf()
            dstAccessMask = listOf(
                    VkAccessFlagBits.VK_ACCESS_DEPTH_STENCIL_ATTACHMENT_READ_BIT,
                    VkAccessFlagBits.VK_ACCESS_DEPTH_STENCIL_ATTACHMENT_WRITE_BIT
            )

            sourceStage = VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT
            destinationStage = VkPipelineStageFlagBits.VK_PIPELINE_STAGE_EARLY_FRAGMENT_TESTS_BIT
        } else {
            throw IllegalStateException("Unsupported layout transition")
        }

        val aspectFlags: List<VkImageAspectFlagBits> = if (
                newLayout == VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL
        ) {
            if (hasStencilComponent(format)) {
                listOf(
                        VkImageAspectFlagBits.VK_IMAGE_ASPECT_DEPTH_BIT,
                        VkImageAspectFlagBits.VK_IMAGE_ASPECT_STENCIL_BIT
                )
            } else {
                listOf(VkImageAspectFlagBits.VK_IMAGE_ASPECT_DEPTH_BIT)
            }
        } else {
            listOf(VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT)
        }
        val barrier = VkImageMemoryBarrier(
                VkStructureType.VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER,
                srcAccessMask = srcAccessMask,
                dstAccessMask = dstAccessMask,
                oldLayout = oldLayout,
                newLayout = newLayout,
                srcQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED,
                dstQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED,
                image = image,
                subresourceRange = VkImageSubresourceRange(
                        aspectFlags,
                        0,
                        1,
                        0,
                        1
                )
        )

        vk.cmdPipelineBarrier(
                commandBuffer,
                listOf(sourceStage),
                listOf(destinationStage),
                listOf(),
                listOf(),
                listOf(),
                listOf(barrier)
        )

        endSingleTimeCommands(commandBuffer)
    }

    private fun copyBufferToImage(
        buffer: VkBuffer,
        image: VkImage,
        width: Int,
        height: Int
    ) {
        val commandBuffer = beginSingleTimeCommands()
        val region = VkBufferImageCopy(
                0,
                0,
                0,
                VkImageSubresourceLayers(
                        listOf(VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT),
                        0,
                        0,
                        1
                ),
                VkOffset3D(0, 0, 0),
                VkExtent3D(width, height, 1)
        )

        vk.cmdCopyBufferToImage(
                commandBuffer,
                buffer,
                image,
                VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL,
                listOf(region)
        )

        endSingleTimeCommands(commandBuffer)
    }

    private fun createVertexBuffer() {
        val bufferSize = (Vertex.SIZE * vertices.size).toLong()

        val stagingBufferRef = MutableProperty<VkBuffer>()
        val stagingBufferMemoryRef = MutableProperty<VkDeviceMemory>()

        createBuffer(
                bufferSize,
                listOf(VkBufferUsageFlagBits.VK_BUFFER_USAGE_TRANSFER_SRC_BIT),
                listOf(
                        VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT,
                        VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_COHERENT_BIT
                ),
                stagingBufferRef,
                stagingBufferMemoryRef
        )

        val stagingBuffer = checkNotNull(stagingBufferRef.value)
        val stagingBufferMemory = checkNotNull(stagingBufferMemoryRef.value)

        val data: MappedMemory = getProperty {
            vk.mapMemory(device, stagingBufferMemory, 0, bufferSize, listOf(), it)
        }
        data.copy(0, vertices.size.toLong(), vertices)
        data.destroy()

        val vertexBufferRef = MutableProperty<VkBuffer>()
        val vertexBufferMemoryRef = MutableProperty<VkDeviceMemory>()
        createBuffer(
                bufferSize,
                listOf(
                        VkBufferUsageFlagBits.VK_BUFFER_USAGE_TRANSFER_DST_BIT,
                        VkBufferUsageFlagBits.VK_BUFFER_USAGE_VERTEX_BUFFER_BIT
                ),
                listOf(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT),
                vertexBufferRef,
                vertexBufferMemoryRef
        )
        vertexBuffer = checkNotNull(vertexBufferRef.value)
        vertexBufferMemory = checkNotNull(vertexBufferMemoryRef.value)

        copyBuffer(stagingBuffer, vertexBuffer, bufferSize)

        vk.destroyBuffer(device, stagingBuffer)
        vk.freeMemory(device, stagingBufferMemory)
    }

    private fun createIndexBuffer() {
        val bufferSize = (INT_BYTE_SIZE * indices.size).toLong()

        val stagingBufferRef = MutableProperty<VkBuffer>()
        val stagingBufferMemoryRef = MutableProperty<VkDeviceMemory>()

        createBuffer(
                bufferSize,
                listOf(VkBufferUsageFlagBits.VK_BUFFER_USAGE_TRANSFER_SRC_BIT),
                listOf(
                        VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT,
                        VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_COHERENT_BIT
                ),
                stagingBufferRef,
                stagingBufferMemoryRef
        )

        val stagingBuffer = checkNotNull(stagingBufferRef.value)
        val stagingBufferMemory = checkNotNull(stagingBufferMemoryRef.value)

        val data: MappedMemory = getProperty {
            vk.mapMemory(device, stagingBufferMemory, 0, bufferSize, listOf(), it)
        }
        data.copy(0, indices.size.toLong(), indices.toIntArray())
        data.destroy()

        val indexBufferRef = MutableProperty<VkBuffer>()
        val indexBufferMemoryRef = MutableProperty<VkDeviceMemory>()
        createBuffer(
                bufferSize,
                listOf(
                        VkBufferUsageFlagBits.VK_BUFFER_USAGE_TRANSFER_DST_BIT,
                        VkBufferUsageFlagBits.VK_BUFFER_USAGE_INDEX_BUFFER_BIT
                ),
                listOf(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT),
                indexBufferRef,
                indexBufferMemoryRef
        )
        indexBuffer = checkNotNull(indexBufferRef.value)
        indexBufferMemory = checkNotNull(indexBufferMemoryRef.value)

        copyBuffer(stagingBuffer, indexBuffer, bufferSize)

        vk.destroyBuffer(device, stagingBuffer)
        vk.freeMemory(device, depthImageMemory)
    }

    private fun createUniformBuffers() {
        val bufferSize = UniformBufferObject.SIZE
        val uniformBuffers = mutableListOf<VkBuffer>()
        val uniformBuffersMemory = mutableListOf<VkDeviceMemory>()

        for (i in swapChainImages.indices) {
            val uniformBufferRef = MutableProperty<VkBuffer>()
            val uniformBufferMemoryRef = MutableProperty<VkDeviceMemory>()
            createBuffer(
                    bufferSize,
                    listOf(VkBufferUsageFlagBits.VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT),
                    listOf(
                            VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT,
                            VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_HOST_COHERENT_BIT
                    ),
                    uniformBufferRef,
                    uniformBufferMemoryRef
            )
            uniformBuffers.add(checkNotNull(uniformBufferRef.value))
            uniformBuffersMemory.add(checkNotNull(uniformBufferMemoryRef.value))
        }

        this.uniformBuffers = uniformBuffers
        this.uniformBuffersMemory = uniformBuffersMemory
    }

    private fun createDescriptorPool() {
        val poolSizes = listOf(
                VkDescriptorPoolSize(
                        type = VkDescriptorType.VK_DESCRIPTOR_TYPE_UNIFORM_BUFFER,
                        descriptorCount = swapChainImages.size
                ),
                VkDescriptorPoolSize(
                        type = VkDescriptorType.VK_DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER,
                        descriptorCount = swapChainImages.size
                )
        )

        val poolInfo = VkDescriptorPoolCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_DESCRIPTOR_POOL_CREATE_INFO,
                flags = listOf(),
                maxSets = swapChainImages.size,
                poolSizes = poolSizes
        )

        descriptorPool = getProperty { vk.createDescriptorPool(device, poolInfo, it) }
    }

    private fun createDescriptorSets() {
        val layouts = List(swapChainImages.size) { descriptorSetLayout }
        val allocateInfo = VkDescriptorSetAllocateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_DESCRIPTOR_SET_ALLOCATE_INFO,
                descriptorPool,
                swapChainImages.size,
                layouts
        )

        descriptorSets = getProperties { vk.allocateDescriptorSets(device, allocateInfo, it) }
        for (i in swapChainImages.indices) {
            val bufferInfo = VkDescriptorBufferInfo(
                    buffer = uniformBuffers[i],
                    offset = 0,
                    range = UniformBufferObject.SIZE
            )

            val imageInfo = VkDescriptorImageInfo(
                    sampler = textureSampler,
                    imageView = textureImageView,
                    imageLayout = VkImageLayout.VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL
            )

            val descriptorWrites = listOf(
                    VkWriteDescriptorSet(
                            dstSet = descriptorSets[i],
                            dstBinding = 0,
                            dstArrayElement = 0,
                            descriptorType = VkDescriptorType.VK_DESCRIPTOR_TYPE_UNIFORM_BUFFER,
                            imageInfo = listOf(),
                            bufferInfo = listOf(bufferInfo),
                            texelBufferView = listOf()
                    ),
                    VkWriteDescriptorSet(
                            dstSet = descriptorSets[i],
                            dstBinding = 1,
                            dstArrayElement = 0,
                            descriptorType = VkDescriptorType.VK_DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER,
                            imageInfo = listOf(imageInfo),
                            bufferInfo = listOf(),
                            texelBufferView = listOf()
                    )
            )

            vk.updateDescriptorSets(
                    device,
                    descriptorWrites,
                    listOf()
            )
        }
    }

    private fun createBuffer(
        size: VkDeviceSize,
        usage: List<VkBufferUsageFlagBits>,
        properties: List<VkMemoryPropertyFlagBits>,
        bufferRef: MutableProperty<VkBuffer>,
        bufferMemoryRef: MutableProperty<VkDeviceMemory>
    ) {
        val bufferInfo = VkBufferCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO,
                flags = listOf(),
                size = size,
                usage = usage,
                sharingMode = VkSharingMode.VK_SHARING_MODE_EXCLUSIVE,
                queueFamilyIndices = null
        )

        vk.createBuffer(device, bufferInfo, bufferRef)

        val buffer = checkNotNull(bufferRef.value)
        val memRequirements: VkMemoryRequirements = getProperty {
            vk.getBufferMemoryRequirements(device, buffer, it)
        }

        val allocateInfo = VkMemoryAllocateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO,
                memRequirements.size,
                findMemoryType(memRequirements.memoryTypeBits, properties)
        )

        vk.allocateMemory(device, allocateInfo, bufferMemoryRef)
        val bufferMemory = checkNotNull(bufferMemoryRef.value)

        vk.bindBufferMemory(device, buffer, bufferMemory, 0)
    }

    private fun beginSingleTimeCommands(): VkCommandBuffer {
        val allocateInfo = VkCommandBufferAllocateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO,
                commandPool,
                VkCommandBufferLevel.VK_COMMAND_BUFFER_LEVEL_PRIMARY,
                1
        )

        val commandBuffer = getProperties<VkCommandBuffer> {
            vk.allocateCommandBuffers(device, allocateInfo, it)
        }.first()
        val beginInfo = VkCommandBufferBeginInfo(
                VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO,
                listOf(VkCommandBufferUsageFlagBits.VK_COMMAND_BUFFER_USAGE_ONE_TIME_SUBMIT_BIT),
                null
        )

        vk.beginCommandBuffer(commandBuffer, beginInfo)

        return commandBuffer
    }

    private fun endSingleTimeCommands(commandBuffer: VkCommandBuffer) {
        vk.endCommandBuffer(commandBuffer)

        val submitInfo = VkSubmitInfo(
                VkStructureType.VK_STRUCTURE_TYPE_SUBMIT_INFO,
                listOf(),
                listOf(),
                listOf(commandBuffer),
                listOf()
        )

        vk.queueSubmit(graphicsQueue, listOf(submitInfo), null)
        vk.queueWaitIdle(graphicsQueue)

        vk.freeCommandBuffers(device, commandPool, listOf(commandBuffer))
    }

    private fun copyBuffer(srcBuffer: VkBuffer, dstBuffer: VkBuffer, size: VkDeviceSize) {
        val commandBuffer = beginSingleTimeCommands()

        val copyRegion = VkBufferCopy(
                0,
                0,
                size
        )

        vk.cmdCopyBuffer(commandBuffer, srcBuffer, dstBuffer, listOf(copyRegion))

        endSingleTimeCommands(commandBuffer)
    }

    private fun findMemoryType(typeFilter: Int, properties: List<VkMemoryPropertyFlagBits>): Int {
        val computedProperties = properties.sumBy { it.bit }
        val memProperties: VkPhysicalDeviceMemoryProperties = getProperty {
            vk.getPhysicalDeviceMemoryProperties(physicalDevice, it)
        }
        for (i in memProperties.memoryTypes.indices) {
            val memoryType = memProperties.memoryTypes[i]
            if (
                    typeFilter and (1 shl i) != 0 &&
                    (memoryType.propertyFlags.sumBy { it.bit } and computedProperties) == computedProperties
            ) {
                return i
            }
        }
        throw IllegalStateException("Failed to find suitable memory type")
    }

    private fun createCommandBuffers() {
        val allocInfo = VkCommandBufferAllocateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO,
                commandPool = commandPool,
                level = VkCommandBufferLevel.VK_COMMAND_BUFFER_LEVEL_PRIMARY,
                commandBufferCount = swapChainFramebuffers.size
        )

        commandBuffers = getProperties { vk.allocateCommandBuffers(device, allocInfo, it) }
        for (i in commandBuffers.indices) {
            val commandBuffer = commandBuffers[i]
            val beginInfo = VkCommandBufferBeginInfo(
                    VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO,
                    flags = listOf(),
                    inheritanceInfo = null
            )

            vk.beginCommandBuffer(commandBuffer, beginInfo)
            val renderPassInfo = VkRenderPassBeginInfo(
                    VkStructureType.VK_STRUCTURE_TYPE_RENDER_PASS_BEGIN_INFO,
                    renderPass,
                    swapChainFramebuffers[i],
                    VkRect2D(VkOffset2D(0, 0), swapChainExtent),
                    listOf(
                            VkClearValue(VkClearColorValue(0.0f, 0.0f, 0.0f, 1.0f)),
                            VkClearValue(VkClearDepthStencilValue(1.0f, 0))
                    )
            )

            vk.cmdBeginRenderPass(
                    commandBuffer,
                    renderPassInfo,
                    VkSubpassContents.VK_SUBPASS_CONTENTS_INLINE
            )

            vk.cmdBindPipeline(
                    commandBuffer,
                    VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS,
                    graphicsPipeline
            )

            vk.cmdBindVertexBuffers(
                    commandBuffer,
                    0,
                    listOf(vertexBuffer),
                    listOf(0)
            )

            vk.cmdBindIndexBuffer(
                    commandBuffer,
                    indexBuffer,
                    0,
                    VkIndexType.VK_INDEX_TYPE_UINT32
            )

            vk.cmdBindDescriptorSets(
                    commandBuffer,
                    VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS,
                    pipelineLayout,
                    0,
                    listOf(descriptorSets[i]),
                    listOf()
            )

//            vk.cmdDrawIndexed(commandBuffer, indices.size, 1, 0, 0, 0)

            vk.cmdEndRenderPass(commandBuffer)

            check(vk.endCommandBuffer(commandBuffer).isSucceeded()) {
                "Failed to record command buffer"
            }
        }
    }

    private fun createSyncObjects() {
        val semaphoreCreateInfo = VkSemaphoreCreateInfo(VkStructureType.VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO)
        val fenceInfo = VkFenceCreateInfo(
                sType = VkStructureType.VK_STRUCTURE_TYPE_FENCE_CREATE_INFO,
                flags = listOf(VkFenceCreateFlagBits.VK_FENCE_CREATE_SIGNALED_BIT)
        )
        val imageAvailableSemaphores = mutableListOf<VkSemaphore>()
        val renderFinishedSemaphores = mutableListOf<VkSemaphore>()
        val inFlightFences = mutableListOf<VkFence>()
        for (i in 0 until MAX_FRAMES_IN_FLIGHT) {
            imageAvailableSemaphores.add(getProperty { vk.createSemaphore(device, semaphoreCreateInfo, it) })
            renderFinishedSemaphores.add(getProperty { vk.createSemaphore(device, semaphoreCreateInfo, it) })
            inFlightFences.add(getProperty { vk.createFence(device, fenceInfo, it) })
        }

        this.imageAvailableSemaphores = imageAvailableSemaphores
        this.renderFinishedSemaphores = renderFinishedSemaphores
        this.inFlightFences = inFlightFences
        this.imagesInFlight = MutableList(swapChainImages.size) { null }
    }

    private fun updateUniformBuffer(currentImage: Int) {
        val time = 1.0f / 60.0f

        val ubo = UniformBufferObject(
                Mat4.createRotation(Vec3(0.0f, 0.0f, 1.0f), time),
                Mat4.createLookAt(Vec3(2.0f, 2.0f, 2.0f), Vec3(0.0f, 0.0f, 0.0f), Vec3(0.0f, 0.0f, 1.0f)),
                Mat4.createPerspective(
                        45.0f / 180.0f * PI.toFloat(),
                        swapChainExtent.width.toFloat() / swapChainExtent.height.toFloat(),
                        0.1f,
                        10.0f
                )
        )

        val data: MappedMemory = getProperty {
            vk.mapMemory(
                    device,
                    uniformBuffersMemory[currentImage],
                    0,
                    UniformBufferObject.SIZE,
                    listOf(),
                    it
            )
        }
        val floatArray = ubo.flatten()
        data.copy(0, floatArray.size.toLong(), floatArray)
        data.destroy()
    }

    fun drawFrame() {
        vk.waitForFences(device, listOf(inFlightFences[currentFrame]), true, Long.MAX_VALUE)

        val imageIndexRef = MutableProperty<Int>()
        var result = vk.acquireNextImageKHR(
                device,
                swapChain,
                Long.MAX_VALUE,
                imageAvailableSemaphores[currentFrame],
                null,
                imageIndexRef
        )
        if (result == VkResult.VK_ERROR_OUT_OF_DATE_KHR) {
            recreateSwapChain()
            return
        }
        check(result == VkResult.VK_SUCCESS || result == VkResult.VK_SUBOPTIMAL_KHR) {
            "Failed to acquire swap chain image"
        }
        val imageIndex = checkNotNull(imageIndexRef.value)

        updateUniformBuffer(imageIndex)

        imagesInFlight[imageIndex]?.let {
            vk.waitForFences(device, listOf(it), true, Long.MAX_VALUE)
        }

        imagesInFlight[imageIndex] = inFlightFences[currentFrame]

        val waitSemaphores = listOf(imageAvailableSemaphores[currentFrame])
        val waitStages = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT)
        val signalSemaphores = listOf(renderFinishedSemaphores[currentFrame])

        val submitInfo = VkSubmitInfo(
                VkStructureType.VK_STRUCTURE_TYPE_SUBMIT_INFO,
                waitSemaphores,
                waitStages,
                listOf(commandBuffers[imageIndex]),
                signalSemaphores
        )

        vk.resetFences(device, listOf(inFlightFences[currentFrame]))

        result = vk.queueSubmit(graphicsQueue, listOf(submitInfo), inFlightFences[currentFrame])
        check(result.isSucceeded()) {
            "Error($result): Failed to submit draw command buffer"
        }

        val presentInfo = VkPresentInfoKHR(
                VkStructureType.VK_STRUCTURE_TYPE_PRESENT_INFO_KHR,
                signalSemaphores,
                listOf(swapChain),
                listOf(imageIndex),
                null
        )

        result = vk.queuePresentKHR(presentQueue, presentInfo)
        if (result == VkResult.VK_ERROR_OUT_OF_DATE_KHR || result == VkResult.VK_SUBOPTIMAL_KHR || framebufferResized) {
            framebufferResized = false
            recreateSwapChain()
        }
        check(result.isSucceeded()) { "Failed to present swap chain image" }

        currentFrame = (currentFrame + 1) % MAX_FRAMES_IN_FLIGHT
    }

    private fun createShaderModule(code: ByteArray): VkShaderModule {
        val createInfo = VkShaderModuleCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO,
                0,
                code
        )
        return getProperty { vk.createShaderModule(device, createInfo, it) }
    }

    private fun chooseSwapSurfaceFormat(availableFormats: List<VkSurfaceFormatKHR>): VkSurfaceFormatKHR {
        for (availableFormat in availableFormats) {
            if (
                    availableFormat.format == VkFormat.VK_FORMAT_B8G8R8A8_SRGB &&
                    availableFormat.colorSpace == VkColorSpaceKHR.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR
            ) {
                return availableFormat
            }
        }
        return availableFormats.first()
    }

    private fun chooseSwapPresentMode(availablePresentModes: List<VkPresentModeKHR>): VkPresentModeKHR {
        for (availablePresentMode in availablePresentModes) {
            if (availablePresentMode == VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR) {
                return availablePresentMode
            }
        }
        return VkPresentModeKHR.VK_PRESENT_MODE_FIFO_KHR
    }

    private fun chooseSwapExtent(capabilities: VkSurfaceCapabilitiesKHR): VkExtent2D {
        return if (capabilities.currentExtent.width != Int.MAX_VALUE) {
            capabilities.currentExtent
        } else {
            val widthRef = MutableProperty<Int>()
            val heightRef = MutableProperty<Int>()

            delegate.getFramebufferSize(widthRef, heightRef)
            val width = checkNotNull(widthRef.value)
            val height = checkNotNull(heightRef.value)

            VkExtent2D(
                    max(capabilities.minImageExtent.width, width),
                    max(capabilities.minImageExtent.height, height)
            )
        }
    }

    private fun querySwapChainSupport(device: VkPhysicalDevice): SwapChainSupportDetails {
        val capabilities: VkSurfaceCapabilitiesKHR = getProperty {
            vk.getPhysicalDeviceSurfaceCapabilitiesKHR(device, surface, it)
        }
        val surfaceFormats: List<VkSurfaceFormatKHR> = getProperties {
            vk.getPhysicalDeviceSurfaceFormatsKHR(device, surface, it)
        }

        val presentModes = getProperties<VkPresentModeKHR> {
            vk.getPhysicalDeviceSurfacePresentModesKHR(
                    device,
                    surface,
                    it
            )
        }
        return SwapChainSupportDetails(capabilities, surfaceFormats, presentModes)
    }

    private fun isDeviceSuitable(device: VkPhysicalDevice): Boolean {
        val indices = findQueueFamilies(device)

        var swapChainAdequate = false
        val extensionsSupported = checkDeviceExtensionSupport(device)
        if (extensionsSupported) {
            val swapChainSupport = querySwapChainSupport(device)
            swapChainAdequate = swapChainSupport.formats.isNotEmpty() && swapChainSupport.presentModes.isNotEmpty()
        }
        val supportedFeatures: VkPhysicalDeviceFeatures = getProperty { vk.getPhysicalDeviceFeatures(device, it) }

        return indices.isComplete() && extensionsSupported && swapChainAdequate && supportedFeatures.samplerAnisotropy
    }

    private fun checkDeviceExtensionSupport(device: VkPhysicalDevice): Boolean {
        val properties: List<VkExtensionProperties> = getProperties {
            vk.enumerateDeviceExtensionProperties(device, null, it)
        }
        val requirements = delegate.getSwapchainExtensionNames().toMutableList()
        requirements.removeAll {
            for (property in properties) {
                if (property.extensionName.startsWith(it)) {
                    return@removeAll true
                }
            }
            false
        }
        return requirements.isEmpty()
    }

    private fun findQueueFamilies(device: VkPhysicalDevice): QueueFamilyIndices {
        val queueFamilies = getProperties<VkQueueFamilyProperties> {
            vk.getPhysicalDeviceQueueFamilyProperties(device, it)
        }
        var graphicsFamily = -1
        var presentFamily = -1
        for (i in queueFamilies.indices) {
            val queueFamily = queueFamilies[i]
            if (queueFamily.queueFlags and VkQueueFlagBits.VK_QUEUE_GRAPHICS_BIT.bit != 0) {
                graphicsFamily = i
            }

            val presentSupport: Boolean = getProperty {
                vk.getPhysicalDeviceSurfaceSupportKHR(
                        device,
                        i,
                        surface,
                        it
                )
            }
            if (presentSupport) {
                presentFamily = i
            }

            val indices = QueueFamilyIndices(graphicsFamily, presentFamily)
            if (indices.isComplete()) {
                return indices
            }
        }
        return QueueFamilyIndices(graphicsFamily, presentFamily)
    }

    private fun getRequiredExtensions(): List<String> {
        return delegate.getRequiredInstanceExtensions()
    }

    private fun checkValidationLayerSupport(): Boolean {
        val availableLayers: List<VkLayerProperties> = getProperties { vk.enumerateInstanceLayerProperties(it) }
        for (layerName in validationLayers) {
            var layerFound = false
            for (layerProperties in availableLayers) {
                if (layerName == layerProperties.layerName) {
                    layerFound = true
                    break
                }
            }

            if (!layerFound) {
                return false
            }
        }
        return true
    }
}
