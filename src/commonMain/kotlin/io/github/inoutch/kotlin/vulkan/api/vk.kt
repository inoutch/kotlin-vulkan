package io.github.inoutch.kotlin.vulkan.api

expect object vk {
    fun createBuffer(device: VkDevice, createInfo: VkBufferCreateInfo): VkBuffer

    fun destroyBuffer(device: VkDevice, buffer: VkBuffer)

    fun createBufferView(device: VkDevice, createInfo: VkBufferViewCreateInfo): VkBufferView

    fun allocateCommandBuffers(device: VkDevice, allocateInfo: VkCommandBufferAllocateInfo): List<VkCommandBuffer>

    fun beginCommandBuffer(commandBuffer: VkCommandBuffer, beginInfo: VkCommandBufferBeginInfo)

    fun endCommandBuffer(commandBuffer: VkCommandBuffer)

    fun cmdBeginRenderPass(commandBuffer: VkCommandBuffer, beginInfo: VkRenderPassBeginInfo, contents: VkSubpassContents)

    fun cmdEndRenderPass(commandBuffer: VkCommandBuffer)

    fun cmdSetViewport(commandBuffer: VkCommandBuffer, firstViewport: Int, viewports: List<VkViewport>)

    fun cmdSetScissor(commandBuffer: VkCommandBuffer, firstScissor: Int, scissors: List<VkRect2D>)

    fun cmdBindPipeline(commandBuffer: VkCommandBuffer, pipelineBindPoint: VkPipelineBindPoint, pipeline: VkPipeline)

    fun cmdBindVertexBuffers(commandBuffer: VkCommandBuffer, firstBinding: Int, buffers: List<VkBuffer>, offsets: List<Long>)

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
        clearColor: Vector4,
        ranges: List<VkImageSubresourceRange>
    )

    fun cmdClearDepthStencilImage(
        commandBuffer: VkCommandBuffer,
        image: VkImage,
        imageLayout: VkImageLayout,
        depthStencilValue: VkClearDepthStencilValue,
        ranges: List<VkImageSubresourceRange>
    )

    fun resetCommandBuffer(
        commandBuffer: VkCommandBuffer,
        flags: List<VkCommandBufferResetFlagBits>
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

    fun createCommandPool(device: VkDevice, createInfo: VkCommandPoolCreateInfo): VkCommandPool

    fun createDescriptorPool(device: VkDevice, createInfo: VkDescriptorPoolCreateInfo): VkDescriptorPool

    fun allocateDescriptorSets(device: VkDevice, allocateInfo: VkDescriptorSetAllocateInfo): List<VkDescriptorSet>

    fun updateDescriptorSets(device: VkDevice, descriptorWrites: List<VkWriteDescriptorSet>, descriptorCopies: List<VkCopyDescriptorSet>)

    fun createDescriptorSetLayout(
        device: VkDevice,
        createInfo: VkDescriptorSetLayoutCreateInfo
    ): VkDescriptorSetLayout

    fun createDevice(
        physicalDevice: VkPhysicalDevice,
        createInfo: VkDeviceCreateInfo
    ): VkDevice

    fun deviceWaitIdle(device: VkDevice)

    fun allocateMemory(device: VkDevice, allocateInfo: VkMemoryAllocateInfo): VkDeviceMemory

    fun bindBufferMemory(device: VkDevice, buffer: VkBuffer, memory: VkDeviceMemory, memoryOffset: Long)

    fun mapMemory(device: VkDevice, memory: VkDeviceMemory, offset: Long, size: Long, flags: List<VkPipelineStageFlagBits>): MappedMemory

    fun createFence(device: VkDevice, createInfo: VkFenceCreateInfo): VkFence

    fun waitForFences(device: VkDevice, fences: List<VkFence>, waitAll: Boolean, timeout: Long)

    fun resetFences(device: VkDevice, fences: List<VkFence>)

    fun getPhysicalDeviceFormatProperties(physicalDevice: VkPhysicalDevice, format: VkFormat): VkFormatProperties

    fun createFramebuffer(device: VkDevice, createInfo: VkFramebufferCreateInfo): VkFramebuffer

    fun createImage(device: VkDevice, createInfo: VkImageCreateInfo): VkImage

    fun bindImageMemory(device: VkDevice, image: VkImage, memory: VkDeviceMemory, memoryOffset: Long)

    fun createImageView(device: VkDevice, createInfo: VkImageViewCreateInfo): VkImageView

    fun createInstance(createInfo: VkInstanceCreateInfo): VkInstance

    fun getBufferMemoryRequirements(device: VkDevice, buffer: VkBuffer): VkMemoryRequirements

    fun getImageMemoryRequirements(device: VkDevice, image: VkImage): VkMemoryRequirements

    fun enumeratePhysicalDevices(instance: VkInstance): List<VkPhysicalDevice>

    fun getPhysicalDeviceMemoryProperties(physicalDevice: VkPhysicalDevice): VkPhysicalDeviceMemoryProperties

    fun getPhysicalDeviceProperties(physicalDevice: VkPhysicalDevice): VkPhysicalDeviceProperties

    fun createGraphicsPipelines(
        device: VkDevice,
        pipelineCache: VkPipelineCache?,
        createInfos: List<VkGraphicsPipelineCreateInfo>
    ): VkPipeline

    fun createPipelineCache(device: VkDevice, createInfo: VkPipelineCacheCreateInfo): VkPipelineCache

    fun createPipelineLayout(device: VkDevice, createInfo: VkPipelineLayoutCreateInfo): VkPipelineLayout

    fun getDeviceQueue(device: VkDevice, queueFamilyIndex: Int, queueIndex: Int): VkQueue

    fun queueSubmit(queue: VkQueue, submitInfos: List<VkSubmitInfo>, fence: VkFence?)

    fun queuePresentKHR(queue: VkQueue, presentInfo: VkPresentInfoKHR)

    fun queueWaitIdle(queue: VkQueue)

    fun getPhysicalDeviceQueueFamilyProperties(physicalDevice: VkPhysicalDevice): List<VkQueueFamilyProperties>

    fun createRenderPass(device: VkDevice, createInfo: VkRenderPassCreateInfo): VkRenderPass

    fun createSampler(device: VkDevice, createInfo: VkSamplerCreateInfo): VkSampler

    fun createSemaphore(device: VkDevice, createInfo: VkSemaphoreCreateInfo): VkSemaphore

    fun createShaderModule(device: VkDevice, shaderModuleCreateInfo: VkShaderModuleCreateInfo): VkShaderModule

    fun getImageSubresourceLayout(device: VkDevice, image: VkImage, subresource: VkImageSubresource): VkSubresourceLayout

    fun getPhysicalDeviceSurfaceSupportKHR(
        physicalDevice: VkPhysicalDevice,
        queueFamilyIndex: Int,
        surface: VkSurface
    ): Boolean

    fun getPhysicalDeviceSurfacePresentModesKHR(
        physicalDevice: VkPhysicalDevice,
        surface: VkSurface
    ): List<VkPresentModeKHR>

    fun getPhysicalDeviceSurfaceCapabilitiesKHR(physicalDevice: VkPhysicalDevice, surface: VkSurface): VkSurfaceCapabilitiesKHR

    fun getPhysicalDeviceSurfaceFormatsKHR(physicalDevice: VkPhysicalDevice, surface: VkSurface): List<VkSurfaceFormatKHR>

    fun createSwapchainKHR(device: VkDevice, createInfo: VkSwapchainCreateInfoKHR): VkSwapchainKHR

    fun getSwapchainImagesKHR(device: VkDevice, swapchain: VkSwapchainKHR): List<VkImage>

    fun acquireNextImageKHR(
        device: VkDevice,
        swapchain: VkSwapchainKHR,
        timeout: Long,
        semaphore: VkSemaphore,
        fence: VkFence?
    ): Int
}
