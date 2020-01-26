package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.forEachIndexes
import io.github.inoutch.kotlin.vulkan.extension.toNative
import io.github.inoutch.kotlin.vulkan.utility.MappedMemory
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty
import io.github.inoutch.kotlin.vulkan.utility.memScoped
import java.nio.LongBuffer
import org.lwjgl.vulkan.KHRSurface
import org.lwjgl.vulkan.KHRSurface.vkDestroySurfaceKHR
import org.lwjgl.vulkan.KHRSwapchain
import org.lwjgl.vulkan.VK10

actual object vk {
    actual fun createBuffer(
        device: VkDevice,
        createInfo: VkBufferCreateInfo,
        buffer: MutableProperty<VkBuffer>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateBuffer(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            buffer set VkBuffer().apply { init(native.get(0)) }
        }
        result
    }

    actual fun destroyBuffer(device: VkDevice, buffer: VkBuffer) {
        VK10.vkDestroyBuffer(device.native, buffer.native, null)
    }

    actual fun createBufferView(device: VkDevice, createInfo: VkBufferViewCreateInfo, view: MutableProperty<VkBufferView>): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateBufferView(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            view set VkBufferView().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun destroyBufferView(device: VkDevice, bufferView: VkBufferView) {
        VK10.vkDestroyBufferView(device.native, bufferView.native, null)
    }

    actual fun allocateCommandBuffers(
        device: VkDevice,
        allocateInfo: VkCommandBufferAllocateInfo,
        commandBuffers: MutableList<VkCommandBuffer>
    ): VkResult = memScoped {
        val native = allocPointer(allocateInfo.commandBufferCount)
        val result = VK10.vkAllocateCommandBuffers(device.native, allocateInfo.toNative(this), native).toVkResult()
        if (result.isSucceeded()) {
            allocateInfo.commandBufferCount.forEachIndexes {
                commandBuffers.add(VkCommandBuffer().apply {
                    init(org.lwjgl.vulkan.VkCommandBuffer(native.get(it), device.native))
                })
            }
        }
        result
    }

    actual fun freeCommandBuffers(device: VkDevice, commandPool: VkCommandPool, commandBuffers: List<VkCommandBuffer>) = memScoped {
        val native = allocPointer(commandBuffers.size)

        commandBuffers.forEach {
            native.put(it.native)
            native.flip()
        }

        VK10.vkFreeCommandBuffers(device.native, commandPool.native, native)
    }

    actual fun beginCommandBuffer(commandBuffer: VkCommandBuffer, beginInfo: VkCommandBufferBeginInfo): VkResult = memScoped {
        VK10.vkBeginCommandBuffer(commandBuffer.native, beginInfo.toNative(this)).toVkResult()
    }

    actual fun endCommandBuffer(commandBuffer: VkCommandBuffer): VkResult = memScoped {
        VK10.vkEndCommandBuffer(commandBuffer.native).toVkResult()
    }

    actual fun cmdBeginRenderPass(
        commandBuffer: VkCommandBuffer,
        beginInfo: VkRenderPassBeginInfo,
        contents: VkSubpassContents
    ) = memScoped {
        VK10.vkCmdBeginRenderPass(commandBuffer.native, beginInfo.toNative(this), contents.value)
    }

    actual fun cmdEndRenderPass(commandBuffer: VkCommandBuffer) {
        VK10.vkCmdEndRenderPass(commandBuffer.native)
    }

    actual fun cmdSetViewport(commandBuffer: VkCommandBuffer, firstViewport: Int, viewports: List<VkViewport>) = memScoped {
        VK10.vkCmdSetViewport(commandBuffer.native, firstViewport, checkNotNull(viewports.toNative(this)))
    }

    actual fun cmdSetScissor(commandBuffer: VkCommandBuffer, firstScissor: Int, scissors: List<VkRect2D>) = memScoped {
        VK10.vkCmdSetScissor(commandBuffer.native, firstScissor, checkNotNull(scissors.toNative(this)))
    }

    actual fun cmdBindPipeline(commandBuffer: VkCommandBuffer, pipelineBindPoint: VkPipelineBindPoint, pipeline: VkPipeline) {
        VK10.vkCmdBindPipeline(commandBuffer.native, pipelineBindPoint.value, pipeline.native)
    }

    actual fun cmdBindVertexBuffers(commandBuffer: VkCommandBuffer, firstBinding: Int, buffers: List<VkBuffer>, offsets: List<Long>) {
        VK10.vkCmdBindVertexBuffers(
                commandBuffer.native, firstBinding, buffers.map { it.native }.toLongArray(),
                offsets.toLongArray())
    }

    actual fun cmdCopyBuffer(
        commandBuffer: VkCommandBuffer,
        srcBuffer: VkBuffer,
        dstBuffer: VkBuffer,
        regions: List<VkBufferCopy>
    ) = memScoped {
        VK10.vkCmdCopyBuffer(commandBuffer.native, srcBuffer.native, dstBuffer.native, regions.toNative(this))
    }

    actual fun cmdDraw(commandBuffer: VkCommandBuffer, vertexCount: Int, instanceCount: Int, firstVertex: Int, firstInstance: Int) {
        VK10.vkCmdDraw(commandBuffer.native, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual fun cmdPipelineBarrier(
        commandBuffer: VkCommandBuffer,
        srcStageMask: List<VkPipelineStageFlagBits>,
        dstStageMask: List<VkPipelineStageFlagBits>,
        dependencyFlags: List<VkDependencyFlagBits>,
        memoryBarriers: List<VkMemoryBarrier>,
        bufferMemoryBarriers: List<VkBufferMemoryBarrier>,
        imageMemoryBarriers: List<VkImageMemoryBarrier>
    ) = memScoped {
        VK10.vkCmdPipelineBarrier(
                commandBuffer.native,
                srcStageMask.sumBy { it.bit },
                dstStageMask.sumBy { it.bit },
                dependencyFlags.sumBy { it.bit },
                memoryBarriers.toNative(this),
                bufferMemoryBarriers.toNative(this),
                imageMemoryBarriers.toNative(this))
    }

    actual fun cmdClearColorImage(commandBuffer: VkCommandBuffer, image: VkImage, imageLayout: VkImageLayout, clearColor: VkClearColorValue, ranges: List<VkImageSubresourceRange>) = memScoped {
        VK10.vkCmdClearColorImage(
                commandBuffer.native,
                image.native,
                imageLayout.value,
                clearColor.toNative(this),
                ranges.toNative(this))
    }

    actual fun cmdClearDepthStencilImage(
        commandBuffer: VkCommandBuffer,
        image: VkImage,
        imageLayout: VkImageLayout,
        depthStencilValue: VkClearDepthStencilValue,
        ranges: List<VkImageSubresourceRange>
    ) = memScoped {
        VK10.vkCmdClearDepthStencilImage(
                commandBuffer.native,
                image.native,
                imageLayout.value,
                depthStencilValue.toNative(this),
                ranges.toNative(this))
    }

    actual fun cmdCopyBufferToImage(
        commandBuffer: VkCommandBuffer,
        srcBuffer: VkBuffer,
        srcImage: VkImage,
        dstImageLayout: VkImageLayout,
        regions: List<VkBufferImageCopy>
    ) = memScoped {
        VK10.vkCmdCopyBufferToImage(
                commandBuffer.native,
                srcBuffer.native,
                srcImage.native,
                dstImageLayout.value,
                regions.toNative(this))
    }

    actual fun cmdBindDescriptorSets(
        commandBuffer: VkCommandBuffer,
        pipelineBindPoint: VkPipelineBindPoint,
        layout: VkPipelineLayout,
        firstSet: Int,
        descriptorSets: List<VkDescriptorSet>,
        dynamicOffsets: List<Int>
    ) = memScoped {
        VK10.vkCmdBindDescriptorSets(
                commandBuffer.native,
                pipelineBindPoint.value,
                layout.native,
                firstSet,
                descriptorSets.map { it.native }.toLongArray().toNative(this),
                dynamicOffsets.toIntArray().toNative(this))
    }

    actual fun resetCommandBuffer(commandBuffer: VkCommandBuffer, flags: List<VkCommandBufferResetFlagBits>) {
        VK10.vkResetCommandBuffer(commandBuffer.native, flags.sumBy { it.value })
    }

    actual fun createCommandPool(
        device: VkDevice,
        createInfo: VkCommandPoolCreateInfo,
        commandPool: MutableProperty<VkCommandPool>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateCommandPool(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            commandPool set VkCommandPool().apply { init(native.get(0)) }
        }
        result
    }

    actual fun destroyCommandPool(device: VkDevice, commandPool: VkCommandPool) {
        VK10.vkDestroyCommandPool(device.native, commandPool.native, null)
    }

    actual fun createDescriptorPool(
        device: VkDevice,
        createInfo: VkDescriptorPoolCreateInfo,
        descriptorPool: MutableProperty<VkDescriptorPool>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateDescriptorPool(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            descriptorPool set VkDescriptorPool().apply { init(native.get(0)) }
        }
        result
    }

    actual fun destroyDescriptorPool(device: VkDevice, descriptorPool: VkDescriptorPool) {
        VK10.vkDestroyDescriptorPool(device.native, descriptorPool.native, null)
    }

    actual fun allocateDescriptorSets(
        device: VkDevice,
        allocateInfo: VkDescriptorSetAllocateInfo,
        descriptorSets: MutableList<VkDescriptorSet>
    ): VkResult = memScoped {
        val native = allocLong(allocateInfo.descriptorSetCount)
        val result = VK10.vkAllocateDescriptorSets(device.native, allocateInfo.toNative(this), native).toVkResult()
        if (result.isSucceeded()) {
            allocateInfo.descriptorSetCount.forEachIndexes {
                descriptorSets.add(VkDescriptorSet().apply { init(native.get(it)) })
            }
        }
        result
    }

    actual fun freeDescriptorSets(device: VkDevice, descriptorPool: VkDescriptorPool, descriptorSets: List<VkDescriptorSet>): VkResult = memScoped {
        val descriptorSetPointers = allocLong(descriptorSets.size)
        descriptorSets.forEachIndexed { index, descriptorSet ->
            descriptorSetPointers.put(descriptorSet.native)
            descriptorSetPointers.flip()
        }
        VK10.vkFreeDescriptorSets(device.native, descriptorPool.native, descriptorSetPointers).toVkResult()
    }

    actual fun updateDescriptorSets(device: VkDevice, descriptorWrites: List<VkWriteDescriptorSet>, descriptorCopies: MutableList<VkCopyDescriptorSet>) = memScoped {
        VK10.vkUpdateDescriptorSets(
                device.native,
                descriptorWrites.toNative(this),
                descriptorCopies.toNative(this))
    }

    actual fun createDescriptorSetLayout(device: VkDevice, createInfo: VkDescriptorSetLayoutCreateInfo, setLayout: MutableProperty<VkDescriptorSetLayout>): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateDescriptorSetLayout(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            setLayout set VkDescriptorSetLayout().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun destroyDescriptorSetLayout(device: VkDevice, descriptorSetLayout: VkDescriptorSetLayout) {
        VK10.vkDestroyDescriptorSetLayout(device.native, descriptorSetLayout.native, null)
    }

    actual fun createDevice(
        physicalDevice: VkPhysicalDevice,
        createInfo: VkDeviceCreateInfo,
        device: MutableProperty<VkDevice>
    ): VkResult = memScoped {
        val nativeCreateInfo = createInfo.toNative(this)
        val native = allocPointer()
        val result = VK10.vkCreateDevice(physicalDevice.native, nativeCreateInfo, null, native).toVkResult()
        if (result.isSucceeded()) {
            device set VkDevice().apply {
                init(org.lwjgl.vulkan.VkDevice(native.get(0), physicalDevice.native, nativeCreateInfo))
            }
        }
        result
    }

    actual fun destroyDevice(device: VkDevice) {
        VK10.vkDestroyDevice(device.native, null)
    }

    actual fun deviceWaitIdle(device: VkDevice): VkResult {
        return VK10.vkDeviceWaitIdle(device.native).toVkResult()
    }

    actual fun allocateMemory(device: VkDevice, allocateInfo: VkMemoryAllocateInfo, memory: MutableProperty<VkDeviceMemory>): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkAllocateMemory(device.native, allocateInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            memory set VkDeviceMemory().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun bindBufferMemory(device: VkDevice, buffer: VkBuffer, memory: VkDeviceMemory, memoryOffset: Long): VkResult {
        return VK10.vkBindBufferMemory(device.native, buffer.native, memory.native, memoryOffset).toVkResult()
    }

    actual fun mapMemory(device: VkDevice, memory: VkDeviceMemory, offset: Long, size: Long, flags: List<VkPipelineStageFlagBits>, data: MutableProperty<MappedMemory>): VkResult = memScoped {
        val native = allocPointer()
        val result = VK10.vkMapMemory(device.native, memory.native, offset, size, flags.sumBy { it.bit }, native).toVkResult()
        if (result.isSucceeded()) {
            data set MappedMemory(size).apply { init(native.get(0), device, memory) }
        }
        result
    }

    actual fun unmapMemory(device: VkDevice, memory: VkDeviceMemory) {
        VK10.vkUnmapMemory(device.native, memory.native)
    }

    actual fun freeMemory(device: VkDevice, memory: VkDeviceMemory) {
        VK10.vkFreeMemory(device.native, memory.native, null)
    }

    actual fun createFence(
        device: VkDevice,
        createInfo: VkFenceCreateInfo,
        fence: MutableProperty<VkFence>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateFence(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            fence set VkFence().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun destroyFence(device: VkDevice, fence: VkFence) {
        VK10.vkDestroyFence(device.native, fence.native, null)
    }

    actual fun waitForFences(
        device: VkDevice,
        fences: List<VkFence>,
        waitAll: Boolean,
        timeout: Long
    ): VkResult = memScoped {
        VK10.vkWaitForFences(device.native, fences.map { it.native }.toLongArray().toNative(this), waitAll, timeout).toVkResult()
    }

    actual fun resetFences(device: VkDevice, fences: List<VkFence>): VkResult = memScoped {
        VK10.vkResetFences(device.native, fences.map { it.native }.toLongArray().toNative(this)).toVkResult()
    }

    actual fun getPhysicalDeviceFormatProperties(
        physicalDevice: VkPhysicalDevice,
        format: VkFormat,
        formatProperties: MutableProperty<VkFormatProperties>
    ) = memScoped {
        val native = add(org.lwjgl.vulkan.VkFormatProperties.calloc())
        VK10.vkGetPhysicalDeviceFormatProperties(physicalDevice.native, format.value, native)
        formatProperties set native.toOrigin()
    }

    actual fun createFramebuffer(
        device: VkDevice,
        createInfo: VkFramebufferCreateInfo,
        framebuffer: MutableProperty<VkFramebuffer>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateFramebuffer(device.native, createInfo.toNative(this), null, native)
                .toVkResult()
        if (result.isSucceeded()) {
            framebuffer set VkFramebuffer().apply { init(native.get(0)) }
        }
        result
    }

    actual fun destroyFramebuffer(device: VkDevice, framebuffer: VkFramebuffer) {
        VK10.vkDestroyFramebuffer(device.native, framebuffer.native, null)
    }

    actual fun createImage(
        device: VkDevice,
        createInfo: VkImageCreateInfo,
        image: MutableProperty<VkImage>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateImage(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            image set VkImage().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun destroyImage(device: VkDevice, image: VkImage) {
        VK10.vkDestroyImage(device.native, image.native, null)
    }

    actual fun bindImageMemory(device: VkDevice, image: VkImage, memory: VkDeviceMemory, memoryOffset: Long): VkResult {
        return VK10.vkBindImageMemory(device.native, image.native, memory.native, memoryOffset).toVkResult()
    }

    actual fun createImageView(
        device: VkDevice,
        createInfo: VkImageViewCreateInfo,
        imageView: MutableProperty<VkImageView>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateImageView(device.native, createInfo.toNative(this), null, native)
                .toVkResult()
        if (result.isSucceeded()) {
            imageView set VkImageView().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun destroyImageView(device: VkDevice, imageView: VkImageView) {
        VK10.vkDestroyImageView(device.native, imageView.native, null)
    }

    actual fun createInstance(
        createInfo: VkInstanceCreateInfo,
        instance: MutableProperty<VkInstance>
    ): VkResult = memScoped {
        val native = allocPointer()
        val nativeCreateInfo = createInfo.toNative(this)
        val result = VK10.vkCreateInstance(nativeCreateInfo, null, native).toVkResult()
        if (result.isSucceeded()) {
            instance set VkInstance().apply { init(org.lwjgl.vulkan.VkInstance(native.get(0), nativeCreateInfo)) }
        }
        result
    }

    actual fun destroyInstance(instance: VkInstance) {
        VK10.vkDestroyInstance(instance.native, null)
    }

    actual fun getBufferMemoryRequirements(
        device: VkDevice,
        buffer: VkBuffer,
        memoryRequirements: MutableProperty<VkMemoryRequirements>
    ) = memScoped {
        val native = org.lwjgl.vulkan.VkMemoryRequirements.calloc()
        VK10.vkGetBufferMemoryRequirements(device.native, buffer.native, native)
        memoryRequirements set native.toOrigin()
    }

    actual fun getImageMemoryRequirements(
        device: VkDevice,
        image: VkImage,
        memoryRequirements: MutableProperty<VkMemoryRequirements>
    ) = memScoped {
        val native = org.lwjgl.vulkan.VkMemoryRequirements.calloc()
        VK10.vkGetImageMemoryRequirements(device.native, image.native, native)
        memoryRequirements set native.toOrigin()
    }

    actual fun enumeratePhysicalDevices(instance: VkInstance, physicalDevices: MutableList<VkPhysicalDevice>): VkResult = memScoped {
        val physicalDeviceCount = allocInt()
        var result = VK10.vkEnumeratePhysicalDevices(instance.native, physicalDeviceCount, null).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        val native = allocPointer(physicalDeviceCount.get(0))
        result = VK10.vkEnumeratePhysicalDevices(instance.native, physicalDeviceCount, native).toVkResult()
        if (result.isSucceeded()) {
            physicalDeviceCount.get(0).forEachIndexes {
                physicalDevices.add(VkPhysicalDevice().apply {
                    init(org.lwjgl.vulkan.VkPhysicalDevice(native.get(it), instance.native))
                })
            }
        }
        result
    }

    actual fun getPhysicalDeviceMemoryProperties(
        physicalDevice: VkPhysicalDevice,
        memoryProperties: MutableProperty<VkPhysicalDeviceMemoryProperties>
    ) = memScoped {
        val native = add(org.lwjgl.vulkan.VkPhysicalDeviceMemoryProperties.calloc())
        VK10.vkGetPhysicalDeviceMemoryProperties(physicalDevice.native, native)
        memoryProperties set native.toOrigin()
    }

    actual fun getPhysicalDeviceProperties(
        physicalDevice: VkPhysicalDevice,
        properties: MutableProperty<VkPhysicalDeviceProperties>
    ) = memScoped {
        val native = add(org.lwjgl.vulkan.VkPhysicalDeviceProperties.calloc())
        VK10.vkGetPhysicalDeviceProperties(physicalDevice.native, native)
        properties set native.toOrigin()
    }

    actual fun createGraphicsPipelines(device: VkDevice, pipelineCache: VkPipelineCache?, createInfos: List<VkGraphicsPipelineCreateInfo>, pipeline: MutableProperty<VkPipeline>): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateGraphicsPipelines(
                device.native,
                pipelineCache?.native ?: VK10.VK_NULL_HANDLE,
                createInfos.toNative(this),
                null,
                native).toVkResult()
        if (result.isSucceeded()) {
            pipeline set VkPipeline().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun destroyPipeline(device: VkDevice, pipeline: VkPipeline) {
        VK10.vkDestroyPipeline(device.native, pipeline.native, null)
    }

    actual fun createPipelineCache(device: VkDevice, createInfo: VkPipelineCacheCreateInfo, pipelineCache: MutableProperty<VkPipelineCache>): VkResult = memScoped {
        val native = LongBuffer.allocate(1)
        val result = VK10.vkCreatePipelineCache(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            pipelineCache set VkPipelineCache().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun destroyPipelineCache(device: VkDevice, pipelineCache: VkPipelineCache) {
        VK10.vkDestroyPipelineCache(device.native, pipelineCache.native, null)
    }

    actual fun createPipelineLayout(
        device: VkDevice,
        createInfo: VkPipelineLayoutCreateInfo,
        pipelineLayout: MutableProperty<VkPipelineLayout>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreatePipelineLayout(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            pipelineLayout set VkPipelineLayout().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun destroyPipelineLayout(device: VkDevice, pipelineLayout: VkPipelineLayout) {
        VK10.vkDestroyPipelineLayout(device.native, pipelineLayout.native, null)
    }

    actual fun getDeviceQueue(
        device: VkDevice,
        queueFamilyIndex: Int,
        queueIndex: Int,
        queue: MutableProperty<VkQueue>
    ) = memScoped {
        val native = allocPointer()
        VK10.vkGetDeviceQueue(device.native, queueFamilyIndex, queueIndex, native)
        queue set VkQueue().apply { init(org.lwjgl.vulkan.VkQueue(native.get(0), device.native)) }
    }

    actual fun queueSubmit(queue: VkQueue, submitInfos: List<VkSubmitInfo>, fence: VkFence?): VkResult = memScoped {
        VK10.vkQueueSubmit(queue.native, submitInfos.toNative(this), fence?.native ?: VK10.VK_NULL_HANDLE).toVkResult()
    }

    actual fun queuePresentKHR(queue: VkQueue, presentInfo: VkPresentInfoKHR): VkResult = memScoped {
        KHRSwapchain.vkQueuePresentKHR(queue.native, presentInfo.toNative(this)).toVkResult()
    }

    actual fun queueWaitIdle(queue: VkQueue): VkResult {
        return VK10.vkQueueWaitIdle(queue.native).toVkResult()
    }

    actual fun getPhysicalDeviceQueueFamilyProperties(
        physicalDevice: VkPhysicalDevice,
        queueFamilyProperties: MutableList<VkQueueFamilyProperties>
    ) = memScoped {
        val queueCount = allocInt()
        VK10.vkGetPhysicalDeviceQueueFamilyProperties(physicalDevice.native, queueCount, null)

        val native = add(org.lwjgl.vulkan.VkQueueFamilyProperties.calloc(queueCount.get(0)))
        VK10.vkGetPhysicalDeviceQueueFamilyProperties(physicalDevice.native, queueCount, native)
        queueCount.get(0).forEachIndexes { queueFamilyProperties.add(native[it].toOrigin()) }
    }

    actual fun createRenderPass(
        device: VkDevice,
        createInfo: VkRenderPassCreateInfo,
        renderPass: MutableProperty<VkRenderPass>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateRenderPass(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            renderPass set VkRenderPass().apply { init(native.get(0)) }
        }
        result
    }

    actual fun destroyRenderPass(device: VkDevice, renderPass: VkRenderPass) {
        VK10.vkDestroyRenderPass(device.native, renderPass.native, null)
    }

    actual fun createSampler(
        device: VkDevice,
        createInfo: VkSamplerCreateInfo,
        sampler: MutableProperty<VkSampler>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateSampler(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            sampler set VkSampler().apply { init(native.get(0), device) }
        }
        result
    }

    actual fun destroySampler(device: VkDevice, sampler: VkSampler) {
        VK10.vkDestroySampler(device.native, sampler.native, null)
    }

    actual fun createSemaphore(
        device: VkDevice,
        createInfo: VkSemaphoreCreateInfo,
        semaphore: MutableProperty<VkSemaphore>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateSemaphore(device.native, createInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            semaphore set VkSemaphore().apply { init(native.get(0)) }
        }
        result
    }

    actual fun destroySemaphore(device: VkDevice, semaphore: VkSemaphore) {
        VK10.vkDestroySemaphore(device.native, semaphore.native, null)
    }

    actual fun createShaderModule(
        device: VkDevice,
        shaderModuleCreateInfo: VkShaderModuleCreateInfo,
        shaderModule: MutableProperty<VkShaderModule>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = VK10.vkCreateShaderModule(device.native, shaderModuleCreateInfo.toNative(this), null, native).toVkResult()
        if (result.isSucceeded()) {
            shaderModule set VkShaderModule().apply { init(native.get(0)) }
        }
        result
    }

    actual fun destroyShaderModule(device: VkDevice, shaderModule: VkShaderModule) {
        VK10.vkDestroyShaderModule(device.native, shaderModule.native, null)
    }

    actual fun getImageSubresourceLayout(
        device: VkDevice,
        image: VkImage,
        subresource: VkImageSubresource,
        layout: MutableProperty<VkSubresourceLayout>
    ) = memScoped {
        val native = add(org.lwjgl.vulkan.VkSubresourceLayout.calloc())
        VK10.vkGetImageSubresourceLayout(device.native, image.native, subresource.toNative(this), native)
        layout set native.toOrigin()
    }

    actual fun getPhysicalDeviceSurfaceSupportKHR(
        physicalDevice: VkPhysicalDevice,
        queueFamilyIndex: Int,
        surface: VkSurface,
        supported: MutableProperty<Boolean>
    ): VkResult = memScoped {
        val supportedPresentation = allocInt(1)
        val result = KHRSurface.vkGetPhysicalDeviceSurfaceSupportKHR(
                physicalDevice.native,
                queueFamilyIndex,
                surface.native,
                supportedPresentation).toVkResult()
        if (result.isSucceeded()) {
            supported set (supportedPresentation.get(0) == VK10.VK_TRUE)
        }
        result
    }

    actual fun getPhysicalDeviceSurfacePresentModesKHR(
        physicalDevice: VkPhysicalDevice,
        surface: VkSurface,
        presentModes: MutableList<VkPresentModeKHR>
    ): VkResult = memScoped {
        val count = allocInt()
        var result = KHRSurface.vkGetPhysicalDeviceSurfacePresentModesKHR(physicalDevice.native, surface.native, count, null).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        val presentModeNatives = allocInt(count.get(0))
        result = KHRSurface.vkGetPhysicalDeviceSurfacePresentModesKHR(physicalDevice.native, surface.native, count, presentModeNatives).toVkResult()
        if (result.isSucceeded()) {
            count.get(0).forEachIndexes { i ->
                val mode = VkPresentModeKHR.values().find { it.value == presentModeNatives[i] }
                if (mode != null) {
                    presentModes.add(mode)
                }
            }
        }
        result
    }

    actual fun getPhysicalDeviceSurfaceCapabilitiesKHR(physicalDevice: VkPhysicalDevice, surface: VkSurface, surfaceCapabilities: MutableProperty<VkSurfaceCapabilitiesKHR>): VkResult = memScoped {
        val native = add(org.lwjgl.vulkan.VkSurfaceCapabilitiesKHR.calloc())
        val result = KHRSurface.vkGetPhysicalDeviceSurfaceCapabilitiesKHR(
                physicalDevice.native,
                surface.native,
                native).toVkResult()
        if (result.isSucceeded()) {
            surfaceCapabilities set native.toOrigin()
        }
        result
    }

    actual fun getPhysicalDeviceSurfaceFormatsKHR(
        physicalDevice: VkPhysicalDevice,
        surface: VkSurface,
        surfaceFormats: MutableList<VkSurfaceFormatKHR>
    ): VkResult = memScoped {
        val count = allocInt()
        var result = KHRSurface.vkGetPhysicalDeviceSurfaceFormatsKHR(
                physicalDevice.native,
                surface.native,
                count,
                null).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        val surfaceFormatNatives = add(org.lwjgl.vulkan.VkSurfaceFormatKHR.calloc(count.get(0)))
        result = KHRSurface.vkGetPhysicalDeviceSurfaceFormatsKHR(
                physicalDevice.native,
                surface.native,
                count,
                surfaceFormatNatives).toVkResult()
        if (result.isSucceeded()) {
            count.get(0).forEachIndexes { surfaceFormats.add(surfaceFormatNatives[it].toOrigin()) }
        }
        result
    }

    actual fun createSwapchainKHR(
        device: VkDevice,
        createInfo: VkSwapchainCreateInfoKHR,
        swapchain: MutableProperty<VkSwapchainKHR>
    ): VkResult = memScoped {
        val native = allocLong()
        val result = KHRSwapchain.vkCreateSwapchainKHR(
                device.native,
                createInfo.toNative(this),
                null,
                native).toVkResult()
        if (result.isSucceeded()) {
            swapchain set VkSwapchainKHR().apply { init(native.get(0)) }
        }
        result
    }

    actual fun destroySwapchainKHR(device: VkDevice, swapchain: VkSwapchainKHR) {
        KHRSwapchain.vkDestroySwapchainKHR(device.native, swapchain.native, null)
    }

    actual fun getSwapchainImagesKHR(
        device: VkDevice,
        swapchain: VkSwapchainKHR,
        swapchainImages: MutableList<VkImage>
    ): VkResult = memScoped {
        val imageCount = allocInt()
        var result = KHRSwapchain.vkGetSwapchainImagesKHR(
                device.native,
                swapchain.native,
                imageCount,
                null).toVkResult()

        if (!result.isSucceeded()) {
            return@memScoped result
        }

        // DO NOT DESTROY the images
        val native = allocLong(imageCount.get(0))
        result = KHRSwapchain.vkGetSwapchainImagesKHR(device.native, swapchain.native, imageCount, native).toVkResult()
        if (result.isSucceeded()) {
            imageCount.get(0).forEachIndexes { swapchainImages.add(VkImage().apply { init(native.get(it)) }) }
        }
        result
    }

    actual fun acquireNextImageKHR(
        device: VkDevice,
        swapchain: VkSwapchainKHR,
        timeout: Long,
        semaphore: VkSemaphore?,
        fence: VkFence?,
        index: MutableProperty<Int>
    ): VkResult = memScoped {
        val indexNative = allocInt()
        val result = KHRSwapchain.vkAcquireNextImageKHR(
                device.native,
                swapchain.native,
                timeout,
                semaphore?.native ?: VK10.VK_NULL_HANDLE,
                fence?.native ?: VK10.VK_NULL_HANDLE, indexNative).toVkResult()
        if (result.isSucceeded()) {
            index set indexNative.get(0)
        }
        result
    }

    actual fun destroySurfaceKHR(instance: VkInstance, surface: VkSurface) {
        vkDestroySurfaceKHR(instance.native, surface.native, null)
    }
}
