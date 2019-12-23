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
import io.github.inoutch.kotlin.vulkan.api.VkBlendFactor.VK_BLEND_FACTOR_ONE
import io.github.inoutch.kotlin.vulkan.api.VkBlendFactor.VK_BLEND_FACTOR_ZERO
import io.github.inoutch.kotlin.vulkan.api.VkBlendOp
import io.github.inoutch.kotlin.vulkan.api.VkBorderColor
import io.github.inoutch.kotlin.vulkan.api.VkColorComponentFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkCommandBuffer
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferAllocateInfo
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferLevel
import io.github.inoutch.kotlin.vulkan.api.VkCommandPool
import io.github.inoutch.kotlin.vulkan.api.VkCommandPoolCreateFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkCommandPoolCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkCompareOp.VK_COMPARE_OP_ALWAYS
import io.github.inoutch.kotlin.vulkan.api.VkCompareOp.VK_COMPARE_OP_LESS
import io.github.inoutch.kotlin.vulkan.api.VkComponentMapping
import io.github.inoutch.kotlin.vulkan.api.VkComponentSwizzle
import io.github.inoutch.kotlin.vulkan.api.VkCompositeAlphaFlagBitsKHR
import io.github.inoutch.kotlin.vulkan.api.VkCullModeFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorSetLayout
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorSetLayoutBinding
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorSetLayoutCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkDevice
import io.github.inoutch.kotlin.vulkan.api.VkDeviceCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkDeviceMemory
import io.github.inoutch.kotlin.vulkan.api.VkDeviceQueueCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkDynamicState
import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.VkExtent3D
import io.github.inoutch.kotlin.vulkan.api.VkFilter
import io.github.inoutch.kotlin.vulkan.api.VkFormat
import io.github.inoutch.kotlin.vulkan.api.VkFormatFeatureFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkFramebuffer
import io.github.inoutch.kotlin.vulkan.api.VkFramebufferCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkFrontFace
import io.github.inoutch.kotlin.vulkan.api.VkGraphicsPipelineCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkImage
import io.github.inoutch.kotlin.vulkan.api.VkImageAspectFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkImageCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkImageLayout
import io.github.inoutch.kotlin.vulkan.api.VkImageLayout.VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL
import io.github.inoutch.kotlin.vulkan.api.VkImageLayout.VK_IMAGE_LAYOUT_PREINITIALIZED
import io.github.inoutch.kotlin.vulkan.api.VkImageMemoryBarrier
import io.github.inoutch.kotlin.vulkan.api.VkImageSubresourceRange
import io.github.inoutch.kotlin.vulkan.api.VkImageTiling
import io.github.inoutch.kotlin.vulkan.api.VkImageType
import io.github.inoutch.kotlin.vulkan.api.VkImageUsageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkImageUsageFlagBits.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT
import io.github.inoutch.kotlin.vulkan.api.VkImageUsageFlagBits.VK_IMAGE_USAGE_TRANSFER_DST_BIT
import io.github.inoutch.kotlin.vulkan.api.VkImageView
import io.github.inoutch.kotlin.vulkan.api.VkImageViewCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkImageViewType
import io.github.inoutch.kotlin.vulkan.api.VkInstance
import io.github.inoutch.kotlin.vulkan.api.VkInstanceCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkLogicOp
import io.github.inoutch.kotlin.vulkan.api.VkMemoryAllocateInfo
import io.github.inoutch.kotlin.vulkan.api.VkMemoryPropertyFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkMemoryRequirements
import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDevice
import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDeviceMemoryProperties
import io.github.inoutch.kotlin.vulkan.api.VkPipeline
import io.github.inoutch.kotlin.vulkan.api.VkPipelineBindPoint
import io.github.inoutch.kotlin.vulkan.api.VkPipelineCache
import io.github.inoutch.kotlin.vulkan.api.VkPipelineColorBlendAttachmentState
import io.github.inoutch.kotlin.vulkan.api.VkPipelineColorBlendStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineDepthStencilStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineDynamicStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineInputAssemblyStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineLayout
import io.github.inoutch.kotlin.vulkan.api.VkPipelineMultisampleStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineRasterizationStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineShaderStageCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineStageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkPipelineVertexInputStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineViewportStateCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPolygonMode
import io.github.inoutch.kotlin.vulkan.api.VkPrimitiveTopology
import io.github.inoutch.kotlin.vulkan.api.VkQueue
import io.github.inoutch.kotlin.vulkan.api.VkRect2D
import io.github.inoutch.kotlin.vulkan.api.VkRenderPass
import io.github.inoutch.kotlin.vulkan.api.VkRenderPassCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkSampleCountFlagBits.VK_SAMPLE_COUNT_1_BIT
import io.github.inoutch.kotlin.vulkan.api.VkSampler
import io.github.inoutch.kotlin.vulkan.api.VkSamplerAddressMode
import io.github.inoutch.kotlin.vulkan.api.VkSamplerCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkSamplerMipmapMode
import io.github.inoutch.kotlin.vulkan.api.VkShaderModule
import io.github.inoutch.kotlin.vulkan.api.VkShaderModuleCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkShaderStageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkSharingMode
import io.github.inoutch.kotlin.vulkan.api.VkStencilOp
import io.github.inoutch.kotlin.vulkan.api.VkStencilOpState
import io.github.inoutch.kotlin.vulkan.api.VkStructureType
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_DESCRIPTOR_SET_LAYOUT_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_GRAPHICS_PIPELINE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_DEPTH_STENCIL_STATE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_DYNAMIC_STATE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_INPUT_ASSEMBLY_STATE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_MULTISAMPLE_STATE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_RASTERIZATION_STATE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_VERTEX_INPUT_STATE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_RENDER_PASS_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_SAMPLER_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO
import io.github.inoutch.kotlin.vulkan.api.VkSubpassDependency
import io.github.inoutch.kotlin.vulkan.api.VkSubpassDescription
import io.github.inoutch.kotlin.vulkan.api.VkSurface
import io.github.inoutch.kotlin.vulkan.api.VkSurfaceFormatKHR
import io.github.inoutch.kotlin.vulkan.api.VkSwapchainCreateInfoKHR
import io.github.inoutch.kotlin.vulkan.api.VkSwapchainKHR
import io.github.inoutch.kotlin.vulkan.api.VkViewport
import io.github.inoutch.kotlin.vulkan.api.vk
import io.github.inoutch.kotlin.vulkan.utility.DeviceQueueFamilyIndices
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty
import io.github.inoutch.kotlin.vulkan.utility.SingleCommandBuffer
import io.github.inoutch.kotlin.vulkan.utility.SwapchainSupportDetails
import io.github.inoutch.kotlin.vulkan.utility.Version
import io.github.inoutch.kotlin.vulkan.utility.findSupportedFormat
import io.github.inoutch.kotlin.vulkan.utility.hasStencilComponent

fun createInstance(
    applicationName: String,
    engineName: String,
    enableLayerNames: List<String>,
    enableExtensionNames: List<String>
): VkInstance? {
    val applicationInfo = VkApplicationInfo(
            VkStructureType.VK_STRUCTURE_TYPE_APPLICATION_INFO,
            applicationName,
            Version(1, 0, 0),
            engineName,
            Version(1, 0, 0),
            Version(1, 0, 3))

    val instanceCreateInfo = VkInstanceCreateInfo(
            VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO,
            0,
            applicationInfo,
            enableLayerNames,
            enableExtensionNames)

    val instanceRef = MutableProperty<VkInstance>()
    vk.createInstance(instanceCreateInfo, instanceRef)
    return instanceRef.value
}

fun createDevice(
    physicalDevice: VkPhysicalDevice,
    deviceQueueFamilyIndexes: DeviceQueueFamilyIndices,
    enableDeviceLayerNames: List<String>,
    enableDeviceExtensionNames: List<String>
): VkDevice? {
    val deviceQueueCreateInfos = mutableListOf<VkDeviceQueueCreateInfo>()
    deviceQueueCreateInfos.add(VkDeviceQueueCreateInfo(
            VkStructureType.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO,
            emptyList(),
            deviceQueueFamilyIndexes.graphicsQueueFamilyIndex))
    if (deviceQueueFamilyIndexes.graphicsQueueFamilyIndex != deviceQueueFamilyIndexes.presentQueueFamilyIndex) {
        deviceQueueCreateInfos.add(VkDeviceQueueCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO,
                emptyList(),
                deviceQueueFamilyIndexes.presentQueueFamilyIndex))
    }
    val deviceCreateInfo = VkDeviceCreateInfo(
            VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO,
            emptyList(),
            deviceQueueCreateInfos,
            enableDeviceLayerNames,
            enableDeviceExtensionNames,
            null)
    val deviceRef = MutableProperty<VkDevice>()
    vk.createDevice(physicalDevice, deviceCreateInfo, deviceRef)
    return deviceRef.value
}

fun createRenderPass(
    device: VkDevice,
    surfaceFormat: VkSurfaceFormatKHR,
    depthFormat: VkFormat
): VkRenderPass? {
    val colorAttachment = VkAttachmentDescription(
            emptyList(),
            surfaceFormat.format,
            listOf(VK_SAMPLE_COUNT_1_BIT),
            VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_LOAD,
            VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_STORE,
            VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_DONT_CARE,
            VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_DONT_CARE,
            initialLayout = VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED,
            finalLayout = VkImageLayout.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR)

    val depthAttachment = VkAttachmentDescription(
            emptyList(),
            depthFormat,
            listOf(VK_SAMPLE_COUNT_1_BIT),
            VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_LOAD,
            VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_STORE,
            VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_DONT_CARE,
            VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_DONT_CARE,
            initialLayout = VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED,
            finalLayout = VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_ATTACHMENT_STENCIL_READ_ONLY_OPTIMAL)

    val colorAttachmentRef = VkAttachmentReference(0, VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL)
    val depthAttachmentRef = VkAttachmentReference(1, VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL)
    val subpass = VkSubpassDescription(
            emptyList(),
            VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS,
            listOf(),
            listOf(colorAttachmentRef),
            listOf(),
            depthAttachmentRef,
            listOf())

    val dependency = VkSubpassDependency(
            VK_SUBPASS_EXTERNAL,
            0,
            listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT),
            listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT),
            listOf(),
            listOf(VkAccessFlagBits.VK_ACCESS_COLOR_ATTACHMENT_READ_BIT,
                    VkAccessFlagBits.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT),
            listOf())

    val renderCreateInfo = VkRenderPassCreateInfo(
            VK_STRUCTURE_TYPE_RENDER_PASS_CREATE_INFO,
            0,
            listOf(colorAttachment, depthAttachment),
            listOf(subpass),
            listOf(dependency))
    val renderPass = MutableProperty<VkRenderPass>()
    vk.createRenderPass(device, renderCreateInfo, renderPass)
    return renderPass.value
}

fun createShaderStages(vertShaderModule: VkShaderModule, fragShaderModule: VkShaderModule):
        List<VkPipelineShaderStageCreateInfo> {
    val vertShaderState = VkPipelineShaderStageCreateInfo(
            VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO,
            emptyList(),
            listOf(VkShaderStageFlagBits.VK_SHADER_STAGE_VERTEX_BIT),
            vertShaderModule,
            "main",
            null
    )
    val fragShaderStage = VkPipelineShaderStageCreateInfo(
            VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO,
            emptyList(),
            listOf(VkShaderStageFlagBits.VK_SHADER_STAGE_FRAGMENT_BIT),
            fragShaderModule,
            "main",
            null
    )
    return listOf(vertShaderState, fragShaderStage)
}

fun createGraphicsPipeline(
    device: VkDevice,
    pipelineCache: VkPipelineCache?,
    pipelineLayout: VkPipelineLayout,
    renderPass: VkRenderPass,
    vertShaderModule: VkShaderModule,
    fragShaderModule: VkShaderModule,
    viewport: VkViewport,
    scissor: VkRect2D,
    depthTest: Boolean,
    cullMode: VkCullModeFlagBits,
    polygonMode: VkPolygonMode,
    srcFactor: VkBlendFactor,
    dstFactor: VkBlendFactor
): VkPipeline? {
    val shaderStages = createShaderStages(vertShaderModule, fragShaderModule)

    val vertexInputState = VkPipelineVertexInputStateCreateInfo(
            VK_STRUCTURE_TYPE_PIPELINE_VERTEX_INPUT_STATE_CREATE_INFO,
            0,
            listOf()/* create2DVertexBindingDescriptions() */,
            listOf()/* create2DVertexAttributeDescriptions() */)

    val inputAssemblyStateCreateInfo = VkPipelineInputAssemblyStateCreateInfo(
            VK_STRUCTURE_TYPE_PIPELINE_INPUT_ASSEMBLY_STATE_CREATE_INFO,
            0,
            VkPrimitiveTopology.VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST,
            false)

    val viewportStateCreateInfo = VkPipelineViewportStateCreateInfo(
            VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO,
            0,
            listOf(viewport),
            listOf(scissor))

    val rasterizationStateCreateInfo = VkPipelineRasterizationStateCreateInfo(
            VK_STRUCTURE_TYPE_PIPELINE_RASTERIZATION_STATE_CREATE_INFO,
            0,
            polygonMode,
            cullMode,
            VkFrontFace.VK_FRONT_FACE_COUNTER_CLOCKWISE,
            false,
            false,
            false,
            0.0f,
            0.0f,
            0.0f,
            1.0f)

    val multisampleStencilOpState = VkPipelineMultisampleStateCreateInfo(
            VK_STRUCTURE_TYPE_PIPELINE_MULTISAMPLE_STATE_CREATE_INFO,
            0,
            listOf(VK_SAMPLE_COUNT_1_BIT),
            false,
            0.0f,
            null,
            alphaToCoverageEnable = false,
            alphaToOneEnable = false)

    val stencilOpState = VkStencilOpState(
            VkStencilOp.VK_STENCIL_OP_KEEP,
            VkStencilOp.VK_STENCIL_OP_KEEP,
            VkStencilOp.VK_STENCIL_OP_KEEP,
            VK_COMPARE_OP_ALWAYS,
            0,
            0,
            0)
    val depthStencilStateCreateInfo = VkPipelineDepthStencilStateCreateInfo(
            VK_STRUCTURE_TYPE_PIPELINE_DEPTH_STENCIL_STATE_CREATE_INFO,
            0,
            depthTest,
            depthTest,
            VK_COMPARE_OP_LESS,
            false,
            false,
            stencilOpState,
            stencilOpState,
            0.0f,
            1.0f)

    val colorWriteMask = VkPipelineColorBlendAttachmentState(
            true,
            srcFactor,
            dstFactor,
            VkBlendOp.VK_BLEND_OP_ADD,
            VK_BLEND_FACTOR_ONE,
            VK_BLEND_FACTOR_ZERO,
            VkBlendOp.VK_BLEND_OP_ADD,
            listOf(VkColorComponentFlagBits.VK_COLOR_COMPONENT_R_BIT,
                    VkColorComponentFlagBits.VK_COLOR_COMPONENT_G_BIT,
                    VkColorComponentFlagBits.VK_COLOR_COMPONENT_B_BIT,
                    VkColorComponentFlagBits.VK_COLOR_COMPONENT_A_BIT))
    val colorBlendStateCreateInfo = VkPipelineColorBlendStateCreateInfo(
            0,
            false,
            VkLogicOp.VK_LOGIC_OP_CLEAR,
            listOf(colorWriteMask),
            floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f))

    val dynamicStateCreateInfo = VkPipelineDynamicStateCreateInfo(
            VK_STRUCTURE_TYPE_PIPELINE_DYNAMIC_STATE_CREATE_INFO,
            0,
            listOf(VkDynamicState.VK_DYNAMIC_STATE_VIEWPORT,
                    VkDynamicState.VK_DYNAMIC_STATE_SCISSOR))

    val pipelineCreateInfo = VkGraphicsPipelineCreateInfo(
            VK_STRUCTURE_TYPE_GRAPHICS_PIPELINE_CREATE_INFO,
            emptyList(),
            shaderStages,
            vertexInputState,
            inputAssemblyStateCreateInfo,
            null,
            viewportStateCreateInfo,
            rasterizationStateCreateInfo,
            multisampleStencilOpState,
            depthStencilStateCreateInfo,
            colorBlendStateCreateInfo,
            dynamicStateCreateInfo,
            pipelineLayout,
            renderPass,
            0,
            null,
            0
    )
    val pipeline = MutableProperty<VkPipeline>()
    vk.createGraphicsPipelines(device, pipelineCache, listOf(pipelineCreateInfo), pipeline)
    return pipeline.value
}

fun findDepthFormat(physicalDevice: VkPhysicalDevice): VkFormat? {
    return findSupportedFormat(
            physicalDevice,
            listOf(VkFormat.VK_FORMAT_D32_SFLOAT,
                    VkFormat.VK_FORMAT_D32_SFLOAT_S8_UINT,
                    VkFormat.VK_FORMAT_D24_UNORM_S8_UINT),
            VkImageTiling.VK_IMAGE_TILING_OPTIMAL,
            listOf(VkFormatFeatureFlagBits.VK_FORMAT_FEATURE_DEPTH_STENCIL_ATTACHMENT_BIT))
}

fun createImage2D(
    device: VkDevice,
    size: VkExtent2D,
    format: VkFormat,
    tiling: VkImageTiling,
    usage: List<VkImageUsageFlagBits>
): VkImage? {

    val createInfo = VkImageCreateInfo(
            VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO,
            emptyList(),
            VkImageType.VK_IMAGE_TYPE_2D,
            format,
            VkExtent3D(size.width, size.height, 1),
            1,
            1,
            VK_SAMPLE_COUNT_1_BIT,
            tiling,
            usage,
            VkSharingMode.VK_SHARING_MODE_EXCLUSIVE,
            listOf(),
            VK_IMAGE_LAYOUT_PREINITIALIZED)

    val image = MutableProperty<VkImage>()
    vk.createImage(device, createInfo, image)
    return image.value
}

fun allocateImageMemory(
    device: VkDevice,
    image: VkImage,
    physicalDeviceMemoryProperties: VkPhysicalDeviceMemoryProperties,
    memoryProperties: List<VkMemoryPropertyFlagBits>
): VkDeviceMemory? {
    val memoryRequirementsRef = MutableProperty<VkMemoryRequirements>()
    vk.getImageMemoryRequirements(device, image, memoryRequirementsRef)
    val memoryRequirements = memoryRequirementsRef.value ?: return null
    val allocateInfo = VkMemoryAllocateInfo(
            VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO,
            memoryRequirements.size,
            physicalDeviceMemoryProperties.findMemoryTypeIndex(memoryRequirements, memoryProperties) ?: return null
    )
    val imageMemory = MutableProperty<VkDeviceMemory>()
    vk.allocateMemory(device, allocateInfo, imageMemory)
    return imageMemory.value
}

fun createImageView(
    device: VkDevice,
    image: VkImage,
    format: VkFormat,
    aspectFlagBits: List<VkImageAspectFlagBits> = listOf(VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT)
): VkImageView? {
    val createInfo = VkImageViewCreateInfo(
            VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO,
            emptyList(),
            image,
            VkImageViewType.VK_IMAGE_VIEW_TYPE_2D,
            format,
            VkComponentMapping(
                    VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY,
                    VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY,
                    VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY,
                    VkComponentSwizzle.VK_COMPONENT_SWIZZLE_IDENTITY),
            VkImageSubresourceRange(aspectFlagBits, 0, 1, 0, 1))
    val imageView = MutableProperty<VkImageView>()
    vk.createImageView(device, createInfo, imageView)
    return imageView.value
}

fun allocateDepthImageMemory(
    device: VkDevice,
    commandPool: VkCommandPool,
    queue: VkQueue,
    depthImage: VkImage,
    depthFormat: VkFormat,
    physicalDeviceMemoryProperties: VkPhysicalDeviceMemoryProperties
): VkDeviceMemory? {
    val depthImageMemory = allocateImageMemory(
            device,
            depthImage,
            physicalDeviceMemoryProperties,
            listOf(VkMemoryPropertyFlagBits.VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT)
    ) ?: return null
    vk.bindImageMemory(device, depthImage, depthImageMemory, 0)

    transitionImageLayout(
            device,
            commandPool,
            queue,
            depthImage,
            depthFormat,
            VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED,
            VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL
    )
    return depthImageMemory
}

fun createSwapchain(
    oldSwapchain: VkSwapchainKHR?,
    extent: VkExtent2D,
    device: VkDevice,
    deviceQueueFamilyIndexes: DeviceQueueFamilyIndices,
    swapchainSupportDetails: SwapchainSupportDetails,
    surface: VkSurface,
    surfaceFormat: VkSurfaceFormatKHR
): VkSwapchainKHR? {
    val imageCount = swapchainSupportDetails.chooseImageCount()
    val presentMode = swapchainSupportDetails.chooseSwapPresentMode()
    val preTransform = swapchainSupportDetails.chooseTransform()
    val imageSharingMode: VkSharingMode
    val queueFamilyIndexes: List<Int>
    if (deviceQueueFamilyIndexes.graphicsQueueFamilyIndex != deviceQueueFamilyIndexes.presentQueueFamilyIndex) {
        imageSharingMode = VkSharingMode.VK_SHARING_MODE_CONCURRENT
        queueFamilyIndexes = listOf(
                deviceQueueFamilyIndexes.graphicsQueueFamilyIndex,
                deviceQueueFamilyIndexes.presentQueueFamilyIndex)
    } else {
        imageSharingMode = VkSharingMode.VK_SHARING_MODE_EXCLUSIVE
        queueFamilyIndexes = listOf(deviceQueueFamilyIndexes.graphicsQueueFamilyIndex)
    }

    val createInfo = VkSwapchainCreateInfoKHR(
            VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO,
            emptyList(),
            surface,
            imageCount,
            surfaceFormat.format,
            surfaceFormat.colorSpace,
            extent,
            1,
            listOf(VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT, VK_IMAGE_USAGE_TRANSFER_DST_BIT),
            imageSharingMode,
            queueFamilyIndexes,
            preTransform,
            VkCompositeAlphaFlagBitsKHR.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR,
            presentMode,
            true,
            oldSwapchain)
    val swapchain = MutableProperty<VkSwapchainKHR>()
    vk.createSwapchainKHR(device, createInfo, swapchain)
    return swapchain.value
}

fun createDepthImage(
    device: VkDevice,
    extent: VkExtent2D,
    depthFormat: VkFormat
): VkImage? {
    return createImage2D(
            device,
            extent,
            depthFormat,
            VkImageTiling.VK_IMAGE_TILING_OPTIMAL,
            listOf(VkImageUsageFlagBits.VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT)
    )
}

fun createDepthImageView(device: VkDevice, depthImage: VkImage, depthFormat: VkFormat): VkImageView? {
    return createImageView(
            device,
            depthImage,
            depthFormat,
            listOf(VkImageAspectFlagBits.VK_IMAGE_ASPECT_DEPTH_BIT)
    )
}

fun createFramebuffer(
    device: VkDevice,
    renderPass: VkRenderPass,
    swapchainImageView: VkImageView,
    depthImageView: VkImageView,
    extent: VkExtent2D
): VkFramebuffer? {
    val createInfo = VkFramebufferCreateInfo(
            VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO,
            emptyList(),
            renderPass,
            listOf(swapchainImageView, depthImageView),
            extent.width,
            extent.height,
            1)

    val framebufferRef = MutableProperty<VkFramebuffer>()
    vk.createFramebuffer(device, createInfo, framebufferRef)
    return framebufferRef.value
}

fun createCommandPool(device: VkDevice, queueFamilyIndex: Int): VkCommandPool? {
    val createInfo = VkCommandPoolCreateInfo(
            VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO,
            listOf(VkCommandPoolCreateFlagBits.VK_COMMAND_POOL_CREATE_RESET_COMMAND_BUFFER_BIT),
            queueFamilyIndex
    )
    val commandPoolRef = MutableProperty<VkCommandPool>()
    vk.createCommandPool(device, createInfo, commandPoolRef)
    return commandPoolRef.value
}

fun createSampler(device: VkDevice, magFilter: VkFilter, minFilter: VkFilter): VkSampler? {
    val createInfo = VkSamplerCreateInfo(
            VK_STRUCTURE_TYPE_SAMPLER_CREATE_INFO,
            0,
            magFilter,
            minFilter,
            VkSamplerMipmapMode.VK_SAMPLER_MIPMAP_MODE_LINEAR,
            VkSamplerAddressMode.VK_SAMPLER_ADDRESS_MODE_REPEAT,
            VkSamplerAddressMode.VK_SAMPLER_ADDRESS_MODE_REPEAT,
            VkSamplerAddressMode.VK_SAMPLER_ADDRESS_MODE_REPEAT,
            0.0f,
            false,
            1.0f,
            false,
            VK_COMPARE_OP_ALWAYS,
            0.0f,
            0.0f,
            VkBorderColor.VK_BORDER_COLOR_FLOAT_OPAQUE_BLACK, false)
    val sampler = MutableProperty<VkSampler>()
    vk.createSampler(device, createInfo, sampler)
    return sampler.value
}

fun allocateRenderCommandBuffer(
    device: VkDevice,
    commandPool: VkCommandPool,
    size: Int
): List<VkCommandBuffer> {
    val allocateInfo = VkCommandBufferAllocateInfo(
            VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO,
            commandPool,
            VkCommandBufferLevel.VK_COMMAND_BUFFER_LEVEL_PRIMARY,
            size)
    val commandBuffers = mutableListOf<VkCommandBuffer>()
    vk.allocateCommandBuffers(device, allocateInfo, commandBuffers)
    return commandBuffers
}

fun transitionImageLayout(
    device: VkDevice,
    commandPool: VkCommandPool,
    queue: VkQueue,
    image: VkImage,
    format: VkFormat,
    oldLayout: VkImageLayout,
    newLayout: VkImageLayout
) {
    val srcAccessMask: List<VkAccessFlagBits>
    val dstAccessMask: List<VkAccessFlagBits>
    val sourceStage: List<VkPipelineStageFlagBits>
    val destinationStage: List<VkPipelineStageFlagBits>
    val aspectMask = mutableListOf<VkImageAspectFlagBits>()

    if (newLayout == VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL) {
        aspectMask.add(VkImageAspectFlagBits.VK_IMAGE_ASPECT_DEPTH_BIT)
        if (format.hasStencilComponent()) {
            aspectMask.add(VkImageAspectFlagBits.VK_IMAGE_ASPECT_STENCIL_BIT)
        }
    } else {
        aspectMask.add(VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT)
    }

    if (oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED &&
            newLayout == VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL) {
        srcAccessMask = listOf()
        dstAccessMask = listOf(VkAccessFlagBits.VK_ACCESS_TRANSFER_WRITE_BIT)

        sourceStage = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT)
        destinationStage = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TRANSFER_BIT)
    } else if (oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL &&
            newLayout == VkImageLayout.VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL) {
        srcAccessMask = listOf(VkAccessFlagBits.VK_ACCESS_TRANSFER_WRITE_BIT)
        dstAccessMask = listOf(VkAccessFlagBits.VK_ACCESS_SHADER_READ_BIT)

        sourceStage = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TRANSFER_BIT)
        destinationStage = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_FRAGMENT_SHADER_BIT)
    } else if (oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED &&
            newLayout == VkImageLayout.VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL) {
        srcAccessMask = listOf()
        dstAccessMask = listOf(VkAccessFlagBits.VK_ACCESS_SHADER_READ_BIT)

        sourceStage = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT)
        destinationStage = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_FRAGMENT_SHADER_BIT)
    } else if (oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED &&
            newLayout == VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL) {
        srcAccessMask = listOf()
        dstAccessMask = listOf(
                VkAccessFlagBits.VK_ACCESS_DEPTH_STENCIL_ATTACHMENT_READ_BIT,
                VkAccessFlagBits.VK_ACCESS_DEPTH_STENCIL_ATTACHMENT_WRITE_BIT)

        sourceStage = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT)
        destinationStage = listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_EARLY_FRAGMENT_TESTS_BIT)
    } else {
        throw IllegalStateException("Unsupported layout transition")
    }

    val barrier = VkImageMemoryBarrier(
            VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER,
            srcAccessMask,
            dstAccessMask,
            oldLayout,
            newLayout,
            VK_QUEUE_FAMILY_IGNORED,
            VK_QUEUE_FAMILY_IGNORED,
            image,
            VkImageSubresourceRange(aspectMask, 0, 1, 0, 1)
    )

    val singleCommandBuffer = SingleCommandBuffer(device, commandPool, queue)
    singleCommandBuffer.submit {
        vk.cmdPipelineBarrier(
                it,
                sourceStage,
                destinationStage,
                listOf(),
                listOf(),
                listOf(),
                listOf(barrier)
        )
    }
    singleCommandBuffer.destroy()
}

fun createShaderModule(device: VkDevice, bytes: ByteArray): VkShaderModule? {
    val createInfo = VkShaderModuleCreateInfo(VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO, 0, bytes)
    val shaderModule = MutableProperty<VkShaderModule>()
    vk.createShaderModule(device, createInfo, shaderModule)
    return shaderModule.value
}

fun createDescriptorSetLayout(
    device: VkDevice,
    descriptorSetLayoutBindings: List<VkDescriptorSetLayoutBinding>
): VkDescriptorSetLayout? {
    val createInfo = VkDescriptorSetLayoutCreateInfo(
            VK_STRUCTURE_TYPE_DESCRIPTOR_SET_LAYOUT_CREATE_INFO,
            listOf(),
            descriptorSetLayoutBindings
    )
    val descriptorSetLayoutRef = MutableProperty<VkDescriptorSetLayout>()
    vk.createDescriptorSetLayout(device, createInfo, descriptorSetLayoutRef)
    return descriptorSetLayoutRef.value
}
