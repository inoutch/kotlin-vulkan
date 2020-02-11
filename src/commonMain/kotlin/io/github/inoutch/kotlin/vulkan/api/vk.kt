package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MappedMemory
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty

const val VK_LOD_CLAMP_NONE = 1000.0f
const val VK_REMAINING_MIP_LEVELS = 0.inv()
const val VK_REMAINING_ARRAY_LAYERS = 0.inv()
const val VK_WHOLE_SIZE = 0L.inv()
const val VK_ATTACHMENT_UNUSED = 0.inv()
const val VK_TRUE = 1
const val VK_FALSE = 0
const val VK_QUEUE_FAMILY_IGNORED = 0.inv()
const val VK_SUBPASS_EXTERNAL = 0.inv()
const val VK_MAX_PHYSICAL_DEVICE_NAME_SIZE = 256
const val VK_UUID_SIZE = 16
const val VK_MAX_MEMORY_TYPES = 32
const val VK_MAX_MEMORY_HEAPS = 16
const val VK_MAX_EXTENSION_NAME_SIZE = 256
const val VK_MAX_DESCRIPTION_SIZE = 256

@Suppress("ClassName")
expect object vk {
    fun createBuffer(device: VkDevice, createInfo: VkBufferCreateInfo, buffer: MutableProperty<VkBuffer>): VkResult

    fun destroyBuffer(device: VkDevice, buffer: VkBuffer)

    fun createBufferView(device: VkDevice, createInfo: VkBufferViewCreateInfo, view: MutableProperty<VkBufferView>): VkResult

    fun destroyBufferView(device: VkDevice, bufferView: VkBufferView)

    fun allocateCommandBuffers(device: VkDevice, allocateInfo: VkCommandBufferAllocateInfo, commandBuffers: MutableList<VkCommandBuffer>): VkResult

    fun freeCommandBuffers(device: VkDevice, commandPool: VkCommandPool, commandBuffers: List<VkCommandBuffer>)

    fun beginCommandBuffer(commandBuffer: VkCommandBuffer, beginInfo: VkCommandBufferBeginInfo): VkResult

    fun endCommandBuffer(commandBuffer: VkCommandBuffer): VkResult

    fun cmdBeginRenderPass(commandBuffer: VkCommandBuffer, beginInfo: VkRenderPassBeginInfo, contents: VkSubpassContents)

    fun cmdEndRenderPass(commandBuffer: VkCommandBuffer)

    fun cmdSetViewport(commandBuffer: VkCommandBuffer, firstViewport: Int, viewports: List<VkViewport>)

    fun cmdSetScissor(commandBuffer: VkCommandBuffer, firstScissor: Int, scissors: List<VkRect2D>)

    fun cmdBindPipeline(commandBuffer: VkCommandBuffer, pipelineBindPoint: VkPipelineBindPoint, pipeline: VkPipeline)

    fun cmdBindVertexBuffers(commandBuffer: VkCommandBuffer, firstBinding: Int, buffers: List<VkBuffer>, offsets: List<Long>)

    fun cmdCopyBuffer(
        commandBuffer: VkCommandBuffer,
        srcBuffer: VkBuffer,
        dstBuffer: VkBuffer,
        regions: List<VkBufferCopy>
    )

    fun cmdDraw(commandBuffer: VkCommandBuffer, vertexCount: Int, instanceCount: Int, firstVertex: Int, firstInstance: Int)

    fun cmdPipelineBarrier(
        commandBuffer: VkCommandBuffer,
        srcStageMask: List<VkPipelineStageFlagBits>,
        dstStageMask: List<VkPipelineStageFlagBits>,
        dependencyFlags: List<VkDependencyFlagBits>,
        memoryBarriers: List<VkMemoryBarrier>,
        bufferMemoryBarriers: List<VkBufferMemoryBarrier>,
        imageMemoryBarriers: List<VkImageMemoryBarrier>
    )

    fun cmdClearColorImage(
        commandBuffer: VkCommandBuffer,
        image: VkImage,
        imageLayout: VkImageLayout,
        clearColor: VkClearColorValue,
        ranges: List<VkImageSubresourceRange>
    )

    fun cmdClearDepthStencilImage(
        commandBuffer: VkCommandBuffer,
        image: VkImage,
        imageLayout: VkImageLayout,
        depthStencilValue: VkClearDepthStencilValue,
        ranges: List<VkImageSubresourceRange>
    )

    fun cmdCopyBufferToImage(
        commandBuffer: VkCommandBuffer,
        srcBuffer: VkBuffer,
        srcImage: VkImage,
        dstImageLayout: VkImageLayout,
        regions: List<VkBufferImageCopy>
    )

    fun cmdBindDescriptorSets(
        commandBuffer: VkCommandBuffer,
        pipelineBindPoint: VkPipelineBindPoint,
        layout: VkPipelineLayout,
        firstSet: Int,
        descriptorSets: List<VkDescriptorSet>,
        dynamicOffsets: List<Int>
    )

    fun resetCommandBuffer(
        commandBuffer: VkCommandBuffer,
        flags: List<VkCommandBufferResetFlagBits>
    )

    fun createCommandPool(device: VkDevice, createInfo: VkCommandPoolCreateInfo, commandPool: MutableProperty<VkCommandPool>): VkResult

    fun destroyCommandPool(device: VkDevice, commandPool: VkCommandPool)

    fun createDescriptorPool(device: VkDevice, createInfo: VkDescriptorPoolCreateInfo, descriptorPool: MutableProperty<VkDescriptorPool>): VkResult

    fun destroyDescriptorPool(device: VkDevice, descriptorPool: VkDescriptorPool)

    fun allocateDescriptorSets(device: VkDevice, allocateInfo: VkDescriptorSetAllocateInfo, descriptorSets: MutableList<VkDescriptorSet>): VkResult

    fun freeDescriptorSets(device: VkDevice, descriptorPool: VkDescriptorPool, descriptorSets: List<VkDescriptorSet>): VkResult

    fun updateDescriptorSets(device: VkDevice, descriptorWrites: List<VkWriteDescriptorSet>, descriptorCopies: List<VkCopyDescriptorSet>)

    fun createDescriptorSetLayout(
        device: VkDevice,
        createInfo: VkDescriptorSetLayoutCreateInfo,
        setLayout: MutableProperty<VkDescriptorSetLayout>
    ): VkResult

    fun destroyDescriptorSetLayout(device: VkDevice, descriptorSetLayout: VkDescriptorSetLayout)

    fun createDevice(
        physicalDevice: VkPhysicalDevice,
        createInfo: VkDeviceCreateInfo,
        device: MutableProperty<VkDevice>
    ): VkResult

    fun destroyDevice(device: VkDevice)

    fun deviceWaitIdle(device: VkDevice): VkResult

    fun allocateMemory(device: VkDevice, allocateInfo: VkMemoryAllocateInfo, memory: MutableProperty<VkDeviceMemory>): VkResult

    fun bindBufferMemory(device: VkDevice, buffer: VkBuffer, memory: VkDeviceMemory, memoryOffset: Long): VkResult

    fun mapMemory(device: VkDevice, memory: VkDeviceMemory, offset: Long, size: Long, flags: List<VkPipelineStageFlagBits>, data: MutableProperty<MappedMemory>): VkResult

    fun unmapMemory(device: VkDevice, memory: VkDeviceMemory)

    fun freeMemory(device: VkDevice, memory: VkDeviceMemory)

    fun createFence(device: VkDevice, createInfo: VkFenceCreateInfo, fence: MutableProperty<VkFence>): VkResult

    fun destroyFence(device: VkDevice, fence: VkFence)

    fun waitForFences(device: VkDevice, fences: List<VkFence>, waitAll: Boolean, timeout: Long): VkResult

    fun resetFences(device: VkDevice, fences: List<VkFence>): VkResult

    fun getPhysicalDeviceFormatProperties(physicalDevice: VkPhysicalDevice, format: VkFormat, formatProperties: MutableProperty<VkFormatProperties>)

    fun createFramebuffer(device: VkDevice, createInfo: VkFramebufferCreateInfo, framebuffer: MutableProperty<VkFramebuffer>): VkResult

    fun destroyFramebuffer(device: VkDevice, framebuffer: VkFramebuffer)

    fun createImage(device: VkDevice, createInfo: VkImageCreateInfo, image: MutableProperty<VkImage>): VkResult

    fun destroyImage(device: VkDevice, image: VkImage)

    fun bindImageMemory(device: VkDevice, image: VkImage, memory: VkDeviceMemory, memoryOffset: Long): VkResult

    fun createImageView(device: VkDevice, createInfo: VkImageViewCreateInfo, imageView: MutableProperty<VkImageView>): VkResult

    fun destroyImageView(device: VkDevice, imageView: VkImageView)

    fun createInstance(createInfo: VkInstanceCreateInfo, instance: MutableProperty<VkInstance>): VkResult

    fun destroyInstance(instance: VkInstance)

    fun getBufferMemoryRequirements(device: VkDevice, buffer: VkBuffer, memoryRequirements: MutableProperty<VkMemoryRequirements>)

    fun getImageMemoryRequirements(device: VkDevice, image: VkImage, memoryRequirements: MutableProperty<VkMemoryRequirements>)

    fun enumeratePhysicalDevices(instance: VkInstance, physicalDevices: MutableList<VkPhysicalDevice>): VkResult

    fun getPhysicalDeviceMemoryProperties(physicalDevice: VkPhysicalDevice, memoryProperties: MutableProperty<VkPhysicalDeviceMemoryProperties>)

    fun getPhysicalDeviceProperties(physicalDevice: VkPhysicalDevice, properties: MutableProperty<VkPhysicalDeviceProperties>)

    fun createGraphicsPipelines(
        device: VkDevice,
        pipelineCache: VkPipelineCache?,
        createInfos: List<VkGraphicsPipelineCreateInfo>,
        pipeline: MutableProperty<VkPipeline>
    ): VkResult

    fun destroyPipeline(device: VkDevice, pipeline: VkPipeline)

    fun createPipelineCache(device: VkDevice, createInfo: VkPipelineCacheCreateInfo, pipelineCache: MutableProperty<VkPipelineCache>): VkResult

    fun destroyPipelineCache(device: VkDevice, pipelineCache: VkPipelineCache)

    fun createPipelineLayout(device: VkDevice, createInfo: VkPipelineLayoutCreateInfo, pipelineLayout: MutableProperty<VkPipelineLayout>): VkResult

    fun destroyPipelineLayout(device: VkDevice, pipelineLayout: VkPipelineLayout)

    fun getDeviceQueue(device: VkDevice, queueFamilyIndex: Int, queueIndex: Int, queue: MutableProperty<VkQueue>)

    fun queueSubmit(queue: VkQueue, submitInfos: List<VkSubmitInfo>, fence: VkFence?): VkResult

    fun queuePresentKHR(queue: VkQueue, presentInfo: VkPresentInfoKHR): VkResult

    fun queueWaitIdle(queue: VkQueue): VkResult

    fun getPhysicalDeviceQueueFamilyProperties(physicalDevice: VkPhysicalDevice, queueFamilyProperties: MutableList<VkQueueFamilyProperties>)

    fun createRenderPass(device: VkDevice, createInfo: VkRenderPassCreateInfo, renderPass: MutableProperty<VkRenderPass>): VkResult

    fun destroyRenderPass(device: VkDevice, renderPass: VkRenderPass)

    fun createSampler(device: VkDevice, createInfo: VkSamplerCreateInfo, sampler: MutableProperty<VkSampler>): VkResult

    fun destroySampler(device: VkDevice, sampler: VkSampler)

    fun createSemaphore(device: VkDevice, createInfo: VkSemaphoreCreateInfo, semaphore: MutableProperty<VkSemaphore>): VkResult

    fun destroySemaphore(device: VkDevice, semaphore: VkSemaphore)

    fun createShaderModule(device: VkDevice, shaderModuleCreateInfo: VkShaderModuleCreateInfo, shaderModule: MutableProperty<VkShaderModule>): VkResult

    fun destroyShaderModule(device: VkDevice, shaderModule: VkShaderModule)

    fun getImageSubresourceLayout(device: VkDevice, image: VkImage, subresource: VkImageSubresource, layout: MutableProperty<VkSubresourceLayout>)

    fun getPhysicalDeviceSurfaceSupportKHR(
        physicalDevice: VkPhysicalDevice,
        queueFamilyIndex: Int,
        surface: VkSurface,
        supported: MutableProperty<Boolean>
    ): VkResult

    fun getPhysicalDeviceSurfacePresentModesKHR(
        physicalDevice: VkPhysicalDevice,
        surface: VkSurface,
        presentModes: MutableList<VkPresentModeKHR>
    ): VkResult

    fun getPhysicalDeviceSurfaceCapabilitiesKHR(
        physicalDevice: VkPhysicalDevice,
        surface: VkSurface,
        surfaceCapabilities: MutableProperty<VkSurfaceCapabilitiesKHR>
    ): VkResult

    fun getPhysicalDeviceSurfaceFormatsKHR(
        physicalDevice: VkPhysicalDevice,
        surface: VkSurface,
        surfaceFormats: MutableList<VkSurfaceFormatKHR>
    ): VkResult

    fun createSwapchainKHR(device: VkDevice, createInfo: VkSwapchainCreateInfoKHR, swapchain: MutableProperty<VkSwapchainKHR>): VkResult

    fun destroySwapchainKHR(device: VkDevice, swapchain: VkSwapchainKHR)

    fun getSwapchainImagesKHR(device: VkDevice, swapchain: VkSwapchainKHR, swapchainImages: MutableList<VkImage>): VkResult

    fun acquireNextImageKHR(
        device: VkDevice,
        swapchain: VkSwapchainKHR,
        timeout: Long,
        semaphore: VkSemaphore?,
        fence: VkFence?,
        index: MutableProperty<Int>
    ): VkResult

    fun destroySurfaceKHR(instance: VkInstance, surface: VkSurface)
}
