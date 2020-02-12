package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.forEachIndexes
import io.github.inoutch.kotlin.vulkan.extension.toNative
import io.github.inoutch.kotlin.vulkan.utility.MappedMemory
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.UIntVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.set
import kotlinx.cinterop.value
import vulkan_android.VK_TRUE
import vulkan_android.VkCommandBufferVar
import vulkan_android.VkImageViewVar
import vulkan_android.VkInstanceVar
import vulkan_android.VkPipelineVar
import vulkan_android.VkRenderPassVar
import vulkan_android.vkAcquireNextImageKHR
import vulkan_android.vkAllocateCommandBuffers
import vulkan_android.vkAllocateDescriptorSets
import vulkan_android.vkAllocateMemory
import vulkan_android.vkBeginCommandBuffer
import vulkan_android.vkBindBufferMemory
import vulkan_android.vkBindImageMemory
import vulkan_android.vkCmdBeginRenderPass
import vulkan_android.vkCmdBindDescriptorSets
import vulkan_android.vkCmdBindPipeline
import vulkan_android.vkCmdBindVertexBuffers
import vulkan_android.vkCmdClearColorImage
import vulkan_android.vkCmdClearDepthStencilImage
import vulkan_android.vkCmdCopyBuffer
import vulkan_android.vkCmdCopyImageToBuffer
import vulkan_android.vkCmdDraw
import vulkan_android.vkCmdEndRenderPass
import vulkan_android.vkCmdPipelineBarrier
import vulkan_android.vkCmdSetScissor
import vulkan_android.vkCmdSetViewport
import vulkan_android.vkCreateBuffer
import vulkan_android.vkCreateBufferView
import vulkan_android.vkCreateCommandPool
import vulkan_android.vkCreateDescriptorPool
import vulkan_android.vkCreateDescriptorSetLayout
import vulkan_android.vkCreateDevice
import vulkan_android.vkCreateFence
import vulkan_android.vkCreateFramebuffer
import vulkan_android.vkCreateGraphicsPipelines
import vulkan_android.vkCreateImage
import vulkan_android.vkCreateImageView
import vulkan_android.vkCreateInstance
import vulkan_android.vkCreatePipelineCache
import vulkan_android.vkCreatePipelineLayout
import vulkan_android.vkCreateRenderPass
import vulkan_android.vkCreateSampler
import vulkan_android.vkCreateSemaphore
import vulkan_android.vkCreateShaderModule
import vulkan_android.vkCreateSwapchainKHR
import vulkan_android.vkDestroyBuffer
import vulkan_android.vkDestroyBufferView
import vulkan_android.vkDestroyCommandPool
import vulkan_android.vkDestroyDescriptorPool
import vulkan_android.vkDestroyDescriptorSetLayout
import vulkan_android.vkDestroyDevice
import vulkan_android.vkDestroyFence
import vulkan_android.vkDestroyFramebuffer
import vulkan_android.vkDestroyImage
import vulkan_android.vkDestroyImageView
import vulkan_android.vkDestroyInstance
import vulkan_android.vkDestroyPipeline
import vulkan_android.vkDestroyPipelineCache
import vulkan_android.vkDestroyPipelineLayout
import vulkan_android.vkDestroyRenderPass
import vulkan_android.vkDestroySampler
import vulkan_android.vkDestroySemaphore
import vulkan_android.vkDestroyShaderModule
import vulkan_android.vkDestroySurfaceKHR
import vulkan_android.vkDestroySwapchainKHR
import vulkan_android.vkDeviceWaitIdle
import vulkan_android.vkEndCommandBuffer
import vulkan_android.vkEnumeratePhysicalDevices
import vulkan_android.vkFreeCommandBuffers
import vulkan_android.vkFreeDescriptorSets
import vulkan_android.vkFreeMemory
import vulkan_android.vkGetBufferMemoryRequirements
import vulkan_android.vkGetDeviceQueue
import vulkan_android.vkGetImageMemoryRequirements
import vulkan_android.vkGetImageSubresourceLayout
import vulkan_android.vkGetPhysicalDeviceFormatProperties
import vulkan_android.vkGetPhysicalDeviceMemoryProperties
import vulkan_android.vkGetPhysicalDeviceProperties
import vulkan_android.vkGetPhysicalDeviceQueueFamilyProperties
import vulkan_android.vkGetPhysicalDeviceSurfaceCapabilitiesKHR
import vulkan_android.vkGetPhysicalDeviceSurfaceFormatsKHR
import vulkan_android.vkGetPhysicalDeviceSurfacePresentModesKHR
import vulkan_android.vkGetPhysicalDeviceSurfaceSupportKHR
import vulkan_android.vkGetSwapchainImagesKHR
import vulkan_android.vkMapMemory
import vulkan_android.vkQueuePresentKHR
import vulkan_android.vkQueueSubmit
import vulkan_android.vkQueueWaitIdle
import vulkan_android.vkResetCommandBuffer
import vulkan_android.vkResetFences
import vulkan_android.vkUnmapMemory
import vulkan_android.vkUpdateDescriptorSets
import vulkan_android.vkWaitForFences

@ExperimentalUnsignedTypes
@Suppress("ClassName")
actual object vk {
    actual fun createBuffer(
        device: VkDevice,
        createInfo: VkBufferCreateInfo,
        buffer: MutableProperty<VkBuffer>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkBufferVar>()
        vkCreateBuffer(device.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    buffer set VkBuffer().apply {
                        init(native.value ?: throw IllegalStateException("buffer is null"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyBuffer(device: VkDevice, buffer: VkBuffer) {
        vkDestroyBuffer(device.native, buffer.native, null)
    }

    actual fun createBufferView(
        device: VkDevice,
        createInfo: VkBufferViewCreateInfo,
        view: MutableProperty<VkBufferView>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkBufferViewVar>()

        vkCreateBufferView(device.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    view set VkBufferView().apply {
                        init(native.value ?: throw IllegalStateException("bufferView is null"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyBufferView(device: VkDevice, bufferView: VkBufferView) {
        vkDestroyBufferView(device.native, bufferView.native, null)
    }

    actual fun allocateCommandBuffers(
        device: VkDevice,
        allocateInfo: VkCommandBufferAllocateInfo,
        commandBuffers: MutableList<VkCommandBuffer>
    ): VkResult = memScoped {
        val natives = allocArray<vulkan_android.VkCommandBufferVar>(allocateInfo.commandBufferCount)

        vkAllocateCommandBuffers(device.native, allocateInfo.toNative(this), natives)
                .also {
                    commandBuffers.addAll(List(allocateInfo.commandBufferCount) {
                        VkCommandBuffer().apply {
                            init(natives[it] ?: throw IllegalStateException("commandBuffer is null"))
                        }
                    })
                }
                .let { VkResult.getValue(it) }
    }

    actual fun freeCommandBuffers(
        device: VkDevice,
        commandPool: VkCommandPool,
        commandBuffers: List<VkCommandBuffer>
    ) = memScoped {
        val native = allocArray<VkCommandBufferVar>(commandBuffers.size)
        commandBuffers.forEachIndexed { index, commandBuffer -> native[index] = commandBuffer.native }
        vkFreeCommandBuffers(device.native, commandPool.native, commandBuffers.size.toUInt(), native)
    }

    actual fun beginCommandBuffer(
        commandBuffer: VkCommandBuffer,
        beginInfo: VkCommandBufferBeginInfo
    ): VkResult = memScoped {
        vkBeginCommandBuffer(commandBuffer.native, beginInfo.toNative(this))
                .let { VkResult.getValue(it) }
    }

    actual fun endCommandBuffer(commandBuffer: VkCommandBuffer): VkResult = memScoped {
        vkEndCommandBuffer(commandBuffer.native)
                .let { VkResult.getValue(it) }
    }

    actual fun cmdBeginRenderPass(
        commandBuffer: VkCommandBuffer,
        beginInfo: VkRenderPassBeginInfo,
        contents: VkSubpassContents
    ) = memScoped {
        vkCmdBeginRenderPass(commandBuffer.native, beginInfo.toNative(this), contents.value.toUInt())
    }

    actual fun cmdEndRenderPass(commandBuffer: VkCommandBuffer) {
        vkCmdEndRenderPass(commandBuffer.native)
    }

    actual fun cmdSetViewport(
        commandBuffer: VkCommandBuffer,
        firstViewport: Int,
        viewports: List<VkViewport>
    ) = memScoped {
        vkCmdSetViewport(
                commandBuffer.native,
                firstViewport.toUInt(),
                viewports.size.toUInt(),
                viewports.toNative(this))
    }

    actual fun cmdSetScissor(commandBuffer: VkCommandBuffer, firstScissor: Int, scissors: List<VkRect2D>) = memScoped {
        vkCmdSetScissor(commandBuffer.native, firstScissor.toUInt(), scissors.size.toUInt(), scissors.toNative(this))
    }

    actual fun cmdBindPipeline(
        commandBuffer: VkCommandBuffer,
        pipelineBindPoint: VkPipelineBindPoint,
        pipeline: VkPipeline
    ) = memScoped {
        vkCmdBindPipeline(commandBuffer.native, pipelineBindPoint.value.toUInt(), pipeline.native)
    }

    actual fun cmdBindVertexBuffers(
        commandBuffer: VkCommandBuffer,
        firstBinding: Int,
        buffers: List<VkBuffer>,
        offsets: List<Long>
    ) = memScoped {
        vkCmdBindVertexBuffers(
                commandBuffer.native,
                firstBinding.toUInt(),
                buffers.size.toUInt(),
                buffers.map { it.native }.toNative(this),
                offsets.map { it.toULong() }.toNative(this))
    }

    actual fun cmdCopyBuffer(
        commandBuffer: VkCommandBuffer,
        srcBuffer: VkBuffer,
        dstBuffer: VkBuffer,
        regions: List<VkBufferCopy>
    ) = memScoped {
        vkCmdCopyBuffer(commandBuffer.native, srcBuffer.native, dstBuffer.native, regions.size.toUInt(), regions.toNative(this))
    }

    actual fun cmdDraw(
        commandBuffer: VkCommandBuffer,
        vertexCount: Int,
        instanceCount: Int,
        firstVertex: Int,
        firstInstance: Int
    ) {
        vkCmdDraw(
                commandBuffer.native,
                vertexCount.toUInt(),
                instanceCount.toUInt(),
                firstVertex.toUInt(),
                firstInstance.toUInt())
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
        vkCmdPipelineBarrier(
                commandBuffer.native,
                srcStageMask.sumBy { it.bit }.toUInt(),
                dstStageMask.sumBy { it.bit }.toUInt(),
                dependencyFlags.sumBy { it.bit }.toUInt(),
                memoryBarriers.size.toUInt(),
                memoryBarriers.toNative(this),
                bufferMemoryBarriers.size.toUInt(),
                bufferMemoryBarriers.toNative(this),
                imageMemoryBarriers.size.toUInt(),
                imageMemoryBarriers.toNative(this))
    }

    actual fun cmdClearColorImage(
        commandBuffer: VkCommandBuffer,
        image: VkImage,
        imageLayout: VkImageLayout,
        clearColor: VkClearColorValue,
        ranges: List<VkImageSubresourceRange>
    ) = memScoped {
        vkCmdClearColorImage(
                commandBuffer.native,
                image.native,
                imageLayout.value.toUInt(),
                clearColor.toNative(this),
                ranges.size.toUInt(),
                ranges.toNative(this))
    }

    actual fun cmdClearDepthStencilImage(
        commandBuffer: VkCommandBuffer,
        image: VkImage,
        imageLayout: VkImageLayout,
        depthStencilValue: VkClearDepthStencilValue,
        ranges: List<VkImageSubresourceRange>
    ) = memScoped {
        vkCmdClearDepthStencilImage(
                commandBuffer.native,
                image.native,
                imageLayout.value.toUInt(),
                depthStencilValue.toNative(this),
                ranges.size.toUInt(),
                ranges.toNative(this))
    }

    actual fun cmdCopyBufferToImage(
        commandBuffer: VkCommandBuffer,
        srcBuffer: VkBuffer,
        srcImage: VkImage,
        dstImageLayout: VkImageLayout,
        regions: List<VkBufferImageCopy>
    ) = memScoped {
        vkCmdCopyImageToBuffer(
                commandBuffer.native,
                srcImage.native,
                dstImageLayout.value.toUInt(),
                srcBuffer.native,
                regions.size.toUInt(),
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
        vkCmdBindDescriptorSets(
                commandBuffer.native,
                pipelineBindPoint.value.toUInt(),
                layout.native,
                firstSet.toUInt(),
                descriptorSets.size.toUInt(),
                descriptorSets.map { it.native }.toNative(this),
                dynamicOffsets.size.toUInt(),
                dynamicOffsets.map { it.toUInt() }.toNative(this))
    }

    actual fun resetCommandBuffer(commandBuffer: VkCommandBuffer, flags: List<VkCommandBufferResetFlagBits>) {
        vkResetCommandBuffer(commandBuffer.native, flags.sumBy { it.value }.toUInt())
    }

    actual fun createCommandPool(
        device: VkDevice,
        createInfo: VkCommandPoolCreateInfo,
        commandPool: MutableProperty<VkCommandPool>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkCommandPoolVar>()

        vkCreateCommandPool(device.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    commandPool set VkCommandPool().apply {
                        init(native.value ?: throw IllegalStateException("commandPool is null"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyCommandPool(device: VkDevice, commandPool: VkCommandPool) {
        vkDestroyCommandPool(device.native, commandPool.native, null)
    }

    actual fun createDescriptorPool(
        device: VkDevice,
        createInfo: VkDescriptorPoolCreateInfo,
        descriptorPool: MutableProperty<VkDescriptorPool>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkDescriptorPoolVar>()

        vkCreateDescriptorPool(device.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    descriptorPool set VkDescriptorPool().apply {
                        init(native.value ?: throw IllegalStateException("descriptorPool is null"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyDescriptorPool(device: VkDevice, descriptorPool: VkDescriptorPool) {
        vkDestroyDescriptorPool(device.native, descriptorPool.native, null)
    }

    actual fun allocateDescriptorSets(
        device: VkDevice,
        allocateInfo: VkDescriptorSetAllocateInfo,
        descriptorSets: MutableList<VkDescriptorSet>
    ): VkResult = memScoped {
        val native = allocArray<vulkan_android.VkDescriptorSetVar>(allocateInfo.descriptorSetCount)

        vkAllocateDescriptorSets(device.native, allocateInfo.toNative(this), native)
                .also {
                    descriptorSets.addAll(List(allocateInfo.descriptorSetCount) {
                        VkDescriptorSet().apply {
                            init(native[it] ?: throw IllegalStateException("descriptorSet is null"))
                        }
                    })
                }
                .let { VkResult.getValue(it) }
    }

    actual fun freeDescriptorSets(device: VkDevice, descriptorPool: VkDescriptorPool, descriptorSets: List<VkDescriptorSet>): VkResult = memScoped {
        val natives = allocArray<vulkan_android.VkDescriptorSetVar>(descriptorSets.size.toLong())
        descriptorSets.forEachIndexed { index, descriptorSet -> natives[index] = descriptorSet.native }
        vkFreeDescriptorSets(device.native, descriptorPool.native, descriptorSets.size.toUInt(), natives)
                .let { VkResult.getValue(it) }
    }

    actual fun updateDescriptorSets(
        device: VkDevice,
        descriptorWrites: List<VkWriteDescriptorSet>,
        descriptorCopies: List<VkCopyDescriptorSet>
    ) = memScoped {
        vkUpdateDescriptorSets(
                device.native,
                descriptorWrites.size.toUInt(),
                descriptorWrites.toNative(this),
                descriptorCopies.size.toUInt(),
                descriptorCopies.toNative(this))
    }

    actual fun createDescriptorSetLayout(
        device: VkDevice,
        createInfo: VkDescriptorSetLayoutCreateInfo,
        setLayout: MutableProperty<VkDescriptorSetLayout>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkDescriptorSetLayoutVar>()
        vkCreateDescriptorSetLayout(device.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    setLayout set VkDescriptorSetLayout().apply {
                        init(native.value ?: throw IllegalStateException("descriptorSetLayout"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyDescriptorSetLayout(device: VkDevice, descriptorSetLayout: VkDescriptorSetLayout) {
        vkDestroyDescriptorSetLayout(device.native, descriptorSetLayout.native, null)
    }

    actual fun createDevice(
        physicalDevice: VkPhysicalDevice,
        createInfo: VkDeviceCreateInfo,
        device: MutableProperty<VkDevice>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkDeviceVar>()
        vkCreateDevice(physicalDevice.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    device set VkDevice().apply {
                        init(native.value ?: throw IllegalStateException("device is null"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyDevice(device: VkDevice) {
        vkDestroyDevice(device.native, null)
    }

    actual fun deviceWaitIdle(device: VkDevice): VkResult {
        return VkResult.getValue(vkDeviceWaitIdle(device.native))
    }

    actual fun allocateMemory(
        device: VkDevice,
        allocateInfo: VkMemoryAllocateInfo,
        memory: MutableProperty<VkDeviceMemory>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkDeviceMemoryVar>()

        vkAllocateMemory(device.native, allocateInfo.toNative(this), null, native.ptr)
                .also {
                    memory set VkDeviceMemory().apply {
                        init(native.value ?: throw IllegalStateException("deviceMemory is null"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun bindBufferMemory(device: VkDevice, buffer: VkBuffer, memory: VkDeviceMemory, memoryOffset: Long): VkResult {
        return vkBindBufferMemory(device.native, buffer.native, memory.native, memoryOffset.toULong())
                .let { VkResult.getValue(it) }
    }

    actual fun mapMemory(
        device: VkDevice,
        memory: VkDeviceMemory,
        offset: Long,
        size: Long,
        flags: List<VkPipelineStageFlagBits>,
        data: MutableProperty<MappedMemory>
    ): VkResult = memScoped {
        val native = alloc<COpaquePointerVar>()

        vkMapMemory(
                device.native,
                memory.native,
                offset.toULong(),
                size.toULong(),
                flags.sumBy { it.bit }.toUInt(),
                native.ptr)
                .also {
                    data set MappedMemory(size).apply {
                        init(native.value ?: throw IllegalStateException("mappedMemory is null"), device, memory)
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun unmapMemory(device: VkDevice, memory: VkDeviceMemory) {
        vkUnmapMemory(device.native, memory.native)
    }

    actual fun freeMemory(device: VkDevice, memory: VkDeviceMemory) {
        vkFreeMemory(device.native, memory.native, null)
    }

    actual fun createFence(device: VkDevice, createInfo: VkFenceCreateInfo, fence: MutableProperty<VkFence>): VkResult = memScoped {
        val native = alloc<vulkan_android.VkFenceVar>()

        vkCreateFence(device.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    fence set VkFence().apply {
                        init(native.value ?: throw IllegalStateException("fence is null"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyFence(device: VkDevice, fence: VkFence) {
        vkDestroyFence(device.native, fence.native, null)
    }

    actual fun waitForFences(device: VkDevice, fences: List<VkFence>, waitAll: Boolean, timeout: Long): VkResult = memScoped {
        vkWaitForFences(
                device.native,
                fences.size.toUInt(),
                fences.map { it.native }.toNative(this),
                waitAll.toVkBool32(),
                timeout.toULong())
                .let { VkResult.getValue(it) }
    }

    actual fun resetFences(device: VkDevice, fences: List<VkFence>): VkResult = memScoped {
        vkResetFences(device.native, fences.size.toUInt(), fences.map { it.native }.toNative(this))
                .let { VkResult.getValue(it) }
    }

    actual fun getPhysicalDeviceFormatProperties(
        physicalDevice: VkPhysicalDevice,
        format: VkFormat,
        formatProperties: MutableProperty<VkFormatProperties>
    ) = memScoped {
        val native = alloc<vulkan_android.VkFormatProperties>()
        vkGetPhysicalDeviceFormatProperties(physicalDevice.native, format.value.toUInt(), native.ptr)
                .also { formatProperties set native.toOrigin() }
    }

    actual fun createFramebuffer(
        device: VkDevice,
        createInfo: VkFramebufferCreateInfo,
        framebuffer: MutableProperty<VkFramebuffer>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkFramebufferVar>()

        vkCreateFramebuffer(device.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    framebuffer set VkFramebuffer().apply {
                        init(native.value ?: throw IllegalStateException("framebuffer"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyFramebuffer(device: VkDevice, framebuffer: VkFramebuffer) {
        vkDestroyFramebuffer(device.native, framebuffer.native, null)
    }

    actual fun createImage(device: VkDevice, createInfo: VkImageCreateInfo, image: MutableProperty<VkImage>): VkResult = memScoped {
        val native = alloc<vulkan_android.VkImageVar>()

        vkCreateImage(device.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    image set VkImage().apply {
                        init(native.value ?: throw IllegalStateException("image is null"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyImage(device: VkDevice, image: VkImage) {
        vkDestroyImage(device.native, image.native, null)
    }

    actual fun bindImageMemory(device: VkDevice, image: VkImage, memory: VkDeviceMemory, memoryOffset: Long): VkResult {
        return vkBindImageMemory(device.native, image.native, memory.native, memoryOffset.toULong())
                .let { VkResult.getValue(it) }
    }

    actual fun createImageView(
        device: VkDevice,
        createInfo: VkImageViewCreateInfo,
        imageView: MutableProperty<VkImageView>
    ): VkResult = memScoped {
        val native = alloc<VkImageViewVar>()
        vkCreateImageView(device.native, createInfo.toNative(this), null, native.ptr)
                .also {
                    imageView set VkImageView().apply {
                        init(native.value ?: throw IllegalStateException("imageView"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyImageView(device: VkDevice, imageView: VkImageView) {
        vkDestroyImageView(device.native, imageView.native, null)
    }

    actual fun createInstance(createInfo: VkInstanceCreateInfo, instance: MutableProperty<VkInstance>): VkResult = memScoped {
        val native = alloc<VkInstanceVar>()
        vkCreateInstance(createInfo.toNative(this), null, native.ptr)
                .also {
                    instance set VkInstance().apply {
                        init(native.value ?: throw IllegalStateException("instance"))
                    }
                }
                .let { VkResult.getValue(it) }
    }

    actual fun destroyInstance(instance: VkInstance) {
        vkDestroyInstance(instance.native, null)
    }

    actual fun getBufferMemoryRequirements(
        device: VkDevice,
        buffer: VkBuffer,
        memoryRequirements: MutableProperty<VkMemoryRequirements>
    ) = memScoped {
        val native = alloc<vulkan_android.VkMemoryRequirements>()

        vkGetBufferMemoryRequirements(device.native, buffer.native, native.ptr)
                .also { memoryRequirements set native.toOrigin() }
    }

    actual fun getImageMemoryRequirements(
        device: VkDevice,
        image: VkImage,
        memoryRequirements: MutableProperty<VkMemoryRequirements>
    ) = memScoped {
        val native = alloc<vulkan_android.VkMemoryRequirements>()
        vkGetImageMemoryRequirements(device.native, image.native, native.ptr)
                .also { memoryRequirements set native.toOrigin() }
    }

    actual fun enumeratePhysicalDevices(instance: VkInstance, physicalDevices: MutableList<VkPhysicalDevice>): VkResult = memScoped {
        val count = alloc<UIntVar>()
        var result = vkEnumeratePhysicalDevices(instance.native, count.ptr, null).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        val natives = allocArray<vulkan_android.VkPhysicalDeviceVar>(count.value.toInt())
        result = vkEnumeratePhysicalDevices(instance.native, count.ptr, natives).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        count.value.toInt().forEachIndexes {
            physicalDevices.add(VkPhysicalDevice().apply {
                init(natives[it] ?: throw IllegalStateException("physicalDevice is null"))
            })
        }
        return@memScoped result
    }

    actual fun getPhysicalDeviceMemoryProperties(
        physicalDevice: VkPhysicalDevice,
        memoryProperties: MutableProperty<VkPhysicalDeviceMemoryProperties>
    ) = memScoped {
        val native = alloc<vulkan_android.VkPhysicalDeviceMemoryProperties>()
        vkGetPhysicalDeviceMemoryProperties(physicalDevice.native, native.ptr)
        memoryProperties set native.toOrigin()
    }

    actual fun getPhysicalDeviceProperties(
        physicalDevice: VkPhysicalDevice,
        properties: MutableProperty<VkPhysicalDeviceProperties>
    ) = memScoped {
        val native = alloc<vulkan_android.VkPhysicalDeviceProperties>()
        vkGetPhysicalDeviceProperties(physicalDevice.native, native.ptr)
        properties set native.toOrigin()
    }

    actual fun createGraphicsPipelines(
        device: VkDevice,
        pipelineCache: VkPipelineCache?,
        createInfos: List<VkGraphicsPipelineCreateInfo>,
        pipeline: MutableProperty<VkPipeline>
    ): VkResult = memScoped {
        val native = alloc<VkPipelineVar>()
        val result = vkCreateGraphicsPipelines(
                device.native,
                pipelineCache?.native,
                createInfos.size.toUInt(),
                createInfos.toNative(this),
                null,
                native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        pipeline set VkPipeline().apply { init(native.value ?: throw IllegalStateException("pipeline")) }
        return@memScoped result
    }

    actual fun destroyPipeline(device: VkDevice, pipeline: VkPipeline) {
        vkDestroyPipeline(device.native, pipeline.native, null)
    }

    actual fun createPipelineCache(
        device: VkDevice,
        createInfo: VkPipelineCacheCreateInfo,
        pipelineCache: MutableProperty<VkPipelineCache>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkPipelineCacheVar>()
        val result = vkCreatePipelineCache(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        pipelineCache set VkPipelineCache().apply { init(native.value ?: throw IllegalStateException("pipelineCache")) }
        return@memScoped result
    }

    actual fun destroyPipelineCache(device: VkDevice, pipelineCache: VkPipelineCache) {
        vkDestroyPipelineCache(device.native, pipelineCache.native, null)
    }

    actual fun createPipelineLayout(
        device: VkDevice,
        createInfo: VkPipelineLayoutCreateInfo,
        pipelineLayout: MutableProperty<VkPipelineLayout>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkPipelineLayoutVar>()
        val result = vkCreatePipelineLayout(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        pipelineLayout set VkPipelineLayout().apply {
            init(native.value ?: throw IllegalStateException("pipelineLayout"))
        }
        return@memScoped result
    }

    actual fun destroyPipelineLayout(device: VkDevice, pipelineLayout: VkPipelineLayout) {
        vkDestroyPipelineLayout(device.native, pipelineLayout.native, null)
    }

    actual fun getDeviceQueue(
        device: VkDevice,
        queueFamilyIndex: Int,
        queueIndex: Int,
        queue: MutableProperty<VkQueue>
    ) = memScoped {
        val native = alloc<vulkan_android.VkQueueVar>()
        vkGetDeviceQueue(device.native, queueFamilyIndex.toUInt(), queueIndex.toUInt(), native.ptr)
        queue set VkQueue().apply { init(native.value ?: throw IllegalStateException("queue is null")) }
    }

    actual fun queueSubmit(queue: VkQueue, submitInfos: List<VkSubmitInfo>, fence: VkFence?): VkResult = memScoped {
        vkQueueSubmit(queue.native, submitInfos.size.toUInt(), submitInfos.toNative(this), fence?.native).toVkResult()
    }

    actual fun queuePresentKHR(queue: VkQueue, presentInfo: VkPresentInfoKHR): VkResult = memScoped {
        vkQueuePresentKHR(queue.native, presentInfo.toNative(this)).toVkResult()
    }

    actual fun queueWaitIdle(queue: VkQueue): VkResult {
        return vkQueueWaitIdle(queue.native).toVkResult()
    }

    actual fun getPhysicalDeviceQueueFamilyProperties(
        physicalDevice: VkPhysicalDevice,
        queueFamilyProperties: MutableList<VkQueueFamilyProperties>
    ) = memScoped {
        val count = alloc<UIntVar>()
        vkGetPhysicalDeviceQueueFamilyProperties(physicalDevice.native, count.ptr, null)

        val properties = allocArray<vulkan_android.VkQueueFamilyProperties>(count.value.toInt())
        vkGetPhysicalDeviceQueueFamilyProperties(physicalDevice.native, count.ptr, properties)

        count.value.toInt().forEachIndexes {
            queueFamilyProperties.add(properties[it].toOrigin())
        }
    }

    actual fun createRenderPass(
        device: VkDevice,
        createInfo: VkRenderPassCreateInfo,
        renderPass: MutableProperty<VkRenderPass>
    ): VkResult = memScoped {
        val native = alloc<VkRenderPassVar>()
        val result = vkCreateRenderPass(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        renderPass set VkRenderPass().apply {
            init(native.value ?: throw IllegalStateException("renderPass"))
        }
        return@memScoped result
    }

    actual fun destroyRenderPass(device: VkDevice, renderPass: VkRenderPass) {
        vkDestroyRenderPass(device.native, renderPass.native, null)
    }

    actual fun createSampler(
        device: VkDevice,
        createInfo: VkSamplerCreateInfo,
        sampler: MutableProperty<VkSampler>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkSamplerVar>()
        val result = vkCreateSampler(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        sampler set VkSampler().apply {
            init(native.value ?: throw IllegalStateException("sampler is null"))
        }
        return@memScoped result
    }

    actual fun destroySampler(device: VkDevice, sampler: VkSampler) {
        vkDestroySampler(device.native, sampler.native, null)
    }

    actual fun createSemaphore(
        device: VkDevice,
        createInfo: VkSemaphoreCreateInfo,
        semaphore: MutableProperty<VkSemaphore>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkSemaphoreVar>()
        val result = vkCreateSemaphore(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        semaphore set VkSemaphore().apply {
            init(native.value ?: throw IllegalStateException("semaphore is null"))
        }
        return@memScoped result
    }

    actual fun destroySemaphore(device: VkDevice, semaphore: VkSemaphore) {
        vkDestroySemaphore(device.native, semaphore.native, null)
    }

    actual fun createShaderModule(
        device: VkDevice,
        shaderModuleCreateInfo: VkShaderModuleCreateInfo,
        shaderModule: MutableProperty<VkShaderModule>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkShaderModuleVar>()
        val result = vkCreateShaderModule(device.native, shaderModuleCreateInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        shaderModule set VkShaderModule().apply {
            init(native.value ?: throw IllegalStateException("shaderModule"))
        }
        return@memScoped result
    }

    actual fun destroyShaderModule(device: VkDevice, shaderModule: VkShaderModule) {
        vkDestroyShaderModule(device.native, shaderModule.native, null)
    }

    actual fun getImageSubresourceLayout(
        device: VkDevice,
        image: VkImage,
        subresource: VkImageSubresource,
        layout: MutableProperty<VkSubresourceLayout>
    ) = memScoped {
        val native = alloc<vulkan_android.VkSubresourceLayout>()
        vkGetImageSubresourceLayout(device.native, image.native, subresource.toNative(this), native.ptr)
        layout set native.toOrigin()
    }

    actual fun getPhysicalDeviceSurfaceSupportKHR(
        physicalDevice: VkPhysicalDevice,
        queueFamilyIndex: Int,
        surface: VkSurface,
        supported: MutableProperty<Boolean>
    ): VkResult = memScoped {
        val supportedPresentation = alloc<UIntVar>()
        val result = vkGetPhysicalDeviceSurfaceSupportKHR(
                physicalDevice.native,
                queueFamilyIndex.toUInt(),
                surface.native,
                supportedPresentation.ptr).toVkResult()
        if (result.isSucceeded()) {
            supported set (supportedPresentation.value == VK_TRUE.toUInt())
        }
        return@memScoped result
    }

    actual fun getPhysicalDeviceSurfacePresentModesKHR(
        physicalDevice: VkPhysicalDevice,
        surface: VkSurface,
        presentModes: MutableList<VkPresentModeKHR>
    ): VkResult = memScoped {
        val count = alloc<UIntVar>()
        var result = vkGetPhysicalDeviceSurfacePresentModesKHR(physicalDevice.native, surface.native, count.ptr, null).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        val natives = allocArray<vulkan_android.VkPresentModeKHRVar>(count.value.toInt())
        result = vkGetPhysicalDeviceSurfacePresentModesKHR(physicalDevice.native, surface.native, count.ptr, null).toVkResult()
        if (result.isSucceeded()) {
            count.value.toInt().forEachIndexes {
                presentModes.add(VkPresentModeKHR.getValue(natives[it].toInt()))
            }
        }
        result
    }

    actual fun getPhysicalDeviceSurfaceCapabilitiesKHR(
        physicalDevice: VkPhysicalDevice,
        surface: VkSurface,
        surfaceCapabilities: MutableProperty<VkSurfaceCapabilitiesKHR>
    ): VkResult = memScoped {
        val native = alloc<vulkan_android.VkSurfaceCapabilitiesKHR>()
        val result = vkGetPhysicalDeviceSurfaceCapabilitiesKHR(physicalDevice.native, surface.native, native.ptr).toVkResult()
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
        val count = alloc<UIntVar>()
        var result = vkGetPhysicalDeviceSurfaceFormatsKHR(
                physicalDevice.native,
                surface.native,
                count.ptr,
                null).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        val natives = allocArray<vulkan_android.VkSurfaceFormatKHR>(count.value.toInt())
        result = vkGetPhysicalDeviceSurfaceFormatsKHR(
                physicalDevice.native,
                surface.native,
                count.ptr,
                natives).toVkResult()
        if (result.isSucceeded()) {
            count.value.toInt().forEachIndexes {
                surfaceFormats.add(natives[it].toOrigin())
            }
        }
        result
    }

    actual fun createSwapchainKHR(device: VkDevice, createInfo: VkSwapchainCreateInfoKHR, swapchain: MutableProperty<VkSwapchainKHR>): VkResult = memScoped {
        val native = alloc<vulkan_android.VkSwapchainKHRVar>()
        val result = vkCreateSwapchainKHR(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            swapchain set VkSwapchainKHR().apply { init(native.value ?: throw IllegalStateException("swapchain")) }
        }
        result
    }

    actual fun destroySwapchainKHR(device: VkDevice, swapchain: VkSwapchainKHR) {
        vkDestroySwapchainKHR(device.native, swapchain.native, null)
    }

    actual fun getSwapchainImagesKHR(device: VkDevice, swapchain: VkSwapchainKHR, swapchainImages: MutableList<VkImage>): VkResult = memScoped {
        val count = alloc<UIntVar>()
        var result = vkGetSwapchainImagesKHR(device.native, swapchain.native, count.ptr, null).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        val natives = allocArray<vulkan_android.VkImageVar>(count.value.toInt())
        result = vkGetSwapchainImagesKHR(device.native, swapchain.native, count.ptr, natives).toVkResult()
        if (result.isSucceeded()) {
            count.value.toInt().forEachIndexes {
                swapchainImages.add(VkImage().apply {
                    init(natives[it] ?: throw IllegalStateException("swapchainImage is null"))
                })
            }
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
        val imageCount = alloc<UIntVar>()
        val result = vkAcquireNextImageKHR(
                device.native,
                swapchain.native,
                timeout.toULong(),
                semaphore?.native,
                fence?.native,
                imageCount.ptr).toVkResult()
        if (result.isSucceeded()) {
            index set imageCount.value.toInt()
        }
        result
    }

    actual fun destroySurfaceKHR(instance: VkInstance, surface: VkSurface) {
        vkDestroySurfaceKHR(instance.native, surface.native, null)
    }
}
