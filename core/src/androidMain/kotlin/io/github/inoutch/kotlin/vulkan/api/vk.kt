package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.error.UnsupportedVulkanError
import io.github.inoutch.kotlin.vulkan.utility.MappedMemory
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty

@Suppress("ClassName")
actual object vk {
    actual fun createBuffer(device: VkDevice, createInfo: VkBufferCreateInfo, buffer: MutableProperty<VkBuffer>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyBuffer(device: VkDevice, buffer: VkBuffer) {
        throw UnsupportedVulkanError()
    }

    actual fun createBufferView(device: VkDevice, createInfo: VkBufferViewCreateInfo, view: MutableProperty<VkBufferView>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyBufferView(device: VkDevice, bufferView: VkBufferView) {
        throw UnsupportedVulkanError()
    }

    actual fun allocateCommandBuffers(device: VkDevice, allocateInfo: VkCommandBufferAllocateInfo, commandBuffers: MutableList<VkCommandBuffer>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun freeCommandBuffers(device: VkDevice, commandPool: VkCommandPool, commandBuffers: List<VkCommandBuffer>) {
        throw UnsupportedVulkanError()
    }

    actual fun beginCommandBuffer(commandBuffer: VkCommandBuffer, beginInfo: VkCommandBufferBeginInfo): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun endCommandBuffer(commandBuffer: VkCommandBuffer): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun cmdBeginRenderPass(commandBuffer: VkCommandBuffer, beginInfo: VkRenderPassBeginInfo, contents: VkSubpassContents) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdEndRenderPass(commandBuffer: VkCommandBuffer) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdSetViewport(commandBuffer: VkCommandBuffer, firstViewport: Int, viewports: List<VkViewport>) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdSetScissor(commandBuffer: VkCommandBuffer, firstScissor: Int, scissors: List<VkRect2D>) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdBindPipeline(commandBuffer: VkCommandBuffer, pipelineBindPoint: VkPipelineBindPoint, pipeline: VkPipeline) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdBindVertexBuffers(commandBuffer: VkCommandBuffer, firstBinding: Int, buffers: List<VkBuffer>, offsets: List<Long>) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdBindIndexBuffer(commandBuffer: VkCommandBuffer, buffer: VkBuffer, offset: VkDeviceSize, indexType: VkIndexType) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdCopyBuffer(
        commandBuffer: VkCommandBuffer,
        srcBuffer: VkBuffer,
        dstBuffer: VkBuffer,
        regions: List<VkBufferCopy>
    ) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdDraw(commandBuffer: VkCommandBuffer, vertexCount: Int, instanceCount: Int, firstVertex: Int, firstInstance: Int) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdDrawIndexed(
            commandBuffer: VkCommandBuffer,
            indexCount: Int,
            instanceCount: Int,
            firstIndex: Int,
            vertexOffset: Int,
            firstInstance: Int
    ) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdPipelineBarrier(commandBuffer: VkCommandBuffer, srcStageMask: List<VkPipelineStageFlagBits>, dstStageMask: List<VkPipelineStageFlagBits>, dependencyFlags: List<VkDependencyFlagBits>, memoryBarriers: List<VkMemoryBarrier>, bufferMemoryBarriers: List<VkBufferMemoryBarrier>, imageMemoryBarriers: List<VkImageMemoryBarrier>) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdClearColorImage(commandBuffer: VkCommandBuffer, image: VkImage, imageLayout: VkImageLayout, clearColor: VkClearColorValue, ranges: List<VkImageSubresourceRange>) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdClearDepthStencilImage(commandBuffer: VkCommandBuffer, image: VkImage, imageLayout: VkImageLayout, depthStencilValue: VkClearDepthStencilValue, ranges: List<VkImageSubresourceRange>) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdCopyBufferToImage(commandBuffer: VkCommandBuffer, srcBuffer: VkBuffer, srcImage: VkImage, dstImageLayout: VkImageLayout, regions: List<VkBufferImageCopy>) {
        throw UnsupportedVulkanError()
    }

    actual fun cmdBindDescriptorSets(commandBuffer: VkCommandBuffer, pipelineBindPoint: VkPipelineBindPoint, layout: VkPipelineLayout, firstSet: Int, descriptorSets: List<VkDescriptorSet>, dynamicOffsets: List<Int>) {
        throw UnsupportedVulkanError()
    }

    actual fun resetCommandBuffer(commandBuffer: VkCommandBuffer, flags: List<VkCommandBufferResetFlagBits>) {
        throw UnsupportedVulkanError()
    }

    actual fun createCommandPool(device: VkDevice, createInfo: VkCommandPoolCreateInfo, commandPool: MutableProperty<VkCommandPool>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyCommandPool(device: VkDevice, commandPool: VkCommandPool) {
        throw UnsupportedVulkanError()
    }

    actual fun createDescriptorPool(device: VkDevice, createInfo: VkDescriptorPoolCreateInfo, descriptorPool: MutableProperty<VkDescriptorPool>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyDescriptorPool(device: VkDevice, descriptorPool: VkDescriptorPool) {
        throw UnsupportedVulkanError()
    }

    actual fun allocateDescriptorSets(device: VkDevice, allocateInfo: VkDescriptorSetAllocateInfo, descriptorSets: MutableList<VkDescriptorSet>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun freeDescriptorSets(device: VkDevice, descriptorPool: VkDescriptorPool, descriptorSets: List<VkDescriptorSet>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun updateDescriptorSets(device: VkDevice, descriptorWrites: List<VkWriteDescriptorSet>, descriptorCopies: List<VkCopyDescriptorSet>) {
        throw UnsupportedVulkanError()
    }

    actual fun createDescriptorSetLayout(device: VkDevice, createInfo: VkDescriptorSetLayoutCreateInfo, setLayout: MutableProperty<VkDescriptorSetLayout>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyDescriptorSetLayout(device: VkDevice, descriptorSetLayout: VkDescriptorSetLayout) {
        throw UnsupportedVulkanError()
    }

    actual fun createDevice(physicalDevice: VkPhysicalDevice, createInfo: VkDeviceCreateInfo, device: MutableProperty<VkDevice>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyDevice(device: VkDevice) {
        throw UnsupportedVulkanError()
    }

    actual fun deviceWaitIdle(device: VkDevice): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun allocateMemory(device: VkDevice, allocateInfo: VkMemoryAllocateInfo, memory: MutableProperty<VkDeviceMemory>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun bindBufferMemory(device: VkDevice, buffer: VkBuffer, memory: VkDeviceMemory, memoryOffset: Long): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun mapMemory(device: VkDevice, memory: VkDeviceMemory, offset: Long, size: Long, flags: List<VkPipelineStageFlagBits>, data: MutableProperty<MappedMemory>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun unmapMemory(device: VkDevice, memory: VkDeviceMemory) {
        throw UnsupportedVulkanError()
    }

    actual fun freeMemory(device: VkDevice, memory: VkDeviceMemory) {
        throw UnsupportedVulkanError()
    }

    actual fun createFence(device: VkDevice, createInfo: VkFenceCreateInfo, fence: MutableProperty<VkFence>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyFence(device: VkDevice, fence: VkFence) {
        throw UnsupportedVulkanError()
    }

    actual fun waitForFences(device: VkDevice, fences: List<VkFence>, waitAll: Boolean, timeout: Long): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun resetFences(device: VkDevice, fences: List<VkFence>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun getPhysicalDeviceFormatProperties(physicalDevice: VkPhysicalDevice, format: VkFormat, formatProperties: MutableProperty<VkFormatProperties>) {
        throw UnsupportedVulkanError()
    }

    actual fun createFramebuffer(device: VkDevice, createInfo: VkFramebufferCreateInfo, framebuffer: MutableProperty<VkFramebuffer>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyFramebuffer(device: VkDevice, framebuffer: VkFramebuffer) {
        throw UnsupportedVulkanError()
    }

    actual fun createImage(device: VkDevice, createInfo: VkImageCreateInfo, image: MutableProperty<VkImage>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyImage(device: VkDevice, image: VkImage) {
        throw UnsupportedVulkanError()
    }

    actual fun bindImageMemory(device: VkDevice, image: VkImage, memory: VkDeviceMemory, memoryOffset: Long): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun createImageView(device: VkDevice, createInfo: VkImageViewCreateInfo, imageView: MutableProperty<VkImageView>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyImageView(device: VkDevice, imageView: VkImageView) {
        throw UnsupportedVulkanError()
    }

    actual fun createInstance(createInfo: VkInstanceCreateInfo, instance: MutableProperty<VkInstance>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyInstance(instance: VkInstance) {
        throw UnsupportedVulkanError()
    }

    actual fun getBufferMemoryRequirements(device: VkDevice, buffer: VkBuffer, memoryRequirements: MutableProperty<VkMemoryRequirements>) {
        throw UnsupportedVulkanError()
    }

    actual fun getImageMemoryRequirements(device: VkDevice, image: VkImage, memoryRequirements: MutableProperty<VkMemoryRequirements>) {
        throw UnsupportedVulkanError()
    }

    actual fun enumeratePhysicalDevices(instance: VkInstance, physicalDevices: MutableList<VkPhysicalDevice>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun enumerateDeviceExtensionProperties(
            physicalDevice: VkPhysicalDevice,
            name: String?,
            properties: MutableList<VkExtensionProperties>
    ): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun enumerateInstanceLayerProperties(properties: MutableList<VkLayerProperties>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun getPhysicalDeviceMemoryProperties(physicalDevice: VkPhysicalDevice, memoryProperties: MutableProperty<VkPhysicalDeviceMemoryProperties>) {
        throw UnsupportedVulkanError()
    }

    actual fun getPhysicalDeviceProperties(physicalDevice: VkPhysicalDevice, properties: MutableProperty<VkPhysicalDeviceProperties>) {
        throw UnsupportedVulkanError()
    }

    actual fun getPhysicalDeviceFeatures(physicalDevice: VkPhysicalDevice, features: MutableProperty<VkPhysicalDeviceFeatures>) {
        throw UnsupportedVulkanError()
    }

    actual fun createGraphicsPipelines(device: VkDevice, pipelineCache: VkPipelineCache?, createInfos: List<VkGraphicsPipelineCreateInfo>, pipeline: MutableProperty<VkPipeline>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyPipeline(device: VkDevice, pipeline: VkPipeline) {
        throw UnsupportedVulkanError()
    }

    actual fun createPipelineCache(device: VkDevice, createInfo: VkPipelineCacheCreateInfo, pipelineCache: MutableProperty<VkPipelineCache>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyPipelineCache(device: VkDevice, pipelineCache: VkPipelineCache) {
        throw UnsupportedVulkanError()
    }

    actual fun createPipelineLayout(device: VkDevice, createInfo: VkPipelineLayoutCreateInfo, pipelineLayout: MutableProperty<VkPipelineLayout>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyPipelineLayout(device: VkDevice, pipelineLayout: VkPipelineLayout) {
        throw UnsupportedVulkanError()
    }

    actual fun getDeviceQueue(device: VkDevice, queueFamilyIndex: Int, queueIndex: Int, queue: MutableProperty<VkQueue>) {
        throw UnsupportedVulkanError()
    }

    actual fun queueSubmit(queue: VkQueue, submitInfos: List<VkSubmitInfo>, fence: VkFence?): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun queuePresentKHR(queue: VkQueue, presentInfo: VkPresentInfoKHR): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun queueWaitIdle(queue: VkQueue): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun getPhysicalDeviceQueueFamilyProperties(physicalDevice: VkPhysicalDevice, queueFamilyProperties: MutableList<VkQueueFamilyProperties>) {
        throw UnsupportedVulkanError()
    }

    actual fun createRenderPass(device: VkDevice, createInfo: VkRenderPassCreateInfo, renderPass: MutableProperty<VkRenderPass>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyRenderPass(device: VkDevice, renderPass: VkRenderPass) {
        throw UnsupportedVulkanError()
    }

    actual fun createSampler(device: VkDevice, createInfo: VkSamplerCreateInfo, sampler: MutableProperty<VkSampler>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroySampler(device: VkDevice, sampler: VkSampler) {
        throw UnsupportedVulkanError()
    }

    actual fun createSemaphore(device: VkDevice, createInfo: VkSemaphoreCreateInfo, semaphore: MutableProperty<VkSemaphore>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroySemaphore(device: VkDevice, semaphore: VkSemaphore) {
        throw UnsupportedVulkanError()
    }

    actual fun createShaderModule(device: VkDevice, shaderModuleCreateInfo: VkShaderModuleCreateInfo, shaderModule: MutableProperty<VkShaderModule>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroyShaderModule(device: VkDevice, shaderModule: VkShaderModule) {
        throw UnsupportedVulkanError()
    }

    actual fun getImageSubresourceLayout(device: VkDevice, image: VkImage, subresource: VkImageSubresource, layout: MutableProperty<VkSubresourceLayout>) {
        throw UnsupportedVulkanError()
    }

    actual fun getPhysicalDeviceSurfaceSupportKHR(physicalDevice: VkPhysicalDevice, queueFamilyIndex: Int, surface: VkSurface, supported: MutableProperty<Boolean>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun getPhysicalDeviceSurfacePresentModesKHR(physicalDevice: VkPhysicalDevice, surface: VkSurface, presentModes: MutableList<VkPresentModeKHR>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun getPhysicalDeviceSurfaceCapabilitiesKHR(physicalDevice: VkPhysicalDevice, surface: VkSurface, surfaceCapabilities: MutableProperty<VkSurfaceCapabilitiesKHR>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun getPhysicalDeviceSurfaceFormatsKHR(physicalDevice: VkPhysicalDevice, surface: VkSurface, surfaceFormats: MutableList<VkSurfaceFormatKHR>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun createSwapchainKHR(device: VkDevice, createInfo: VkSwapchainCreateInfoKHR, swapchain: MutableProperty<VkSwapchainKHR>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroySwapchainKHR(device: VkDevice, swapchain: VkSwapchainKHR) {
        throw UnsupportedVulkanError()
    }

    actual fun getSwapchainImagesKHR(device: VkDevice, swapchain: VkSwapchainKHR, swapchainImages: MutableList<VkImage>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun acquireNextImageKHR(device: VkDevice, swapchain: VkSwapchainKHR, timeout: Long, semaphore: VkSemaphore?, fence: VkFence?, index: MutableProperty<Int>): VkResult {
        throw UnsupportedVulkanError()
    }

    actual fun destroySurfaceKHR(instance: VkInstance, surface: VkSurface) {
        throw UnsupportedVulkanError()
    }
}
