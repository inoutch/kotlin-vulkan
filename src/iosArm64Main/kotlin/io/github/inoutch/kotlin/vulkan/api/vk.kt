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
import vulkan_ios.VK_TRUE
import vulkan_ios.VkBufferVar
import vulkan_ios.VkBufferViewVar
import vulkan_ios.VkCommandBufferVar
import vulkan_ios.VkCommandPoolVar
import vulkan_ios.VkDescriptorPoolVar
import vulkan_ios.VkDescriptorSetLayoutVar
import vulkan_ios.VkDescriptorSetVar
import vulkan_ios.VkDeviceMemoryVar
import vulkan_ios.VkDeviceVar
import vulkan_ios.VkFenceVar
import vulkan_ios.VkFramebufferVar
import vulkan_ios.VkIOSSurfaceCreateInfoMVK
import vulkan_ios.VkImageVar
import vulkan_ios.VkImageViewVar
import vulkan_ios.VkInstanceVar
import vulkan_ios.VkPhysicalDeviceVar
import vulkan_ios.VkPipelineCacheVar
import vulkan_ios.VkPipelineLayoutVar
import vulkan_ios.VkPipelineVar
import vulkan_ios.VkPresentModeKHRVar
import vulkan_ios.VkQueueVar
import vulkan_ios.VkRenderPassVar
import vulkan_ios.VkSamplerVar
import vulkan_ios.VkSemaphoreVar
import vulkan_ios.VkShaderModuleVar
import vulkan_ios.VkSurfaceKHRVar
import vulkan_ios.VkSwapchainKHRVar
import vulkan_ios.vkAcquireNextImageKHR
import vulkan_ios.vkAllocateCommandBuffers
import vulkan_ios.vkAllocateDescriptorSets
import vulkan_ios.vkAllocateMemory
import vulkan_ios.vkBeginCommandBuffer
import vulkan_ios.vkBindBufferMemory
import vulkan_ios.vkBindImageMemory
import vulkan_ios.vkCmdBeginRenderPass
import vulkan_ios.vkCmdBindDescriptorSets
import vulkan_ios.vkCmdBindPipeline
import vulkan_ios.vkCmdBindVertexBuffers
import vulkan_ios.vkCmdClearColorImage
import vulkan_ios.vkCmdClearDepthStencilImage
import vulkan_ios.vkCmdCopyBuffer
import vulkan_ios.vkCmdCopyImageToBuffer
import vulkan_ios.vkCmdDraw
import vulkan_ios.vkCmdEndRenderPass
import vulkan_ios.vkCmdPipelineBarrier
import vulkan_ios.vkCmdSetScissor
import vulkan_ios.vkCmdSetViewport
import vulkan_ios.vkCreateBuffer
import vulkan_ios.vkCreateBufferView
import vulkan_ios.vkCreateCommandPool
import vulkan_ios.vkCreateDescriptorPool
import vulkan_ios.vkCreateDescriptorSetLayout
import vulkan_ios.vkCreateDevice
import vulkan_ios.vkCreateFence
import vulkan_ios.vkCreateFramebuffer
import vulkan_ios.vkCreateGraphicsPipelines
import vulkan_ios.vkCreateIOSSurfaceMVK
import vulkan_ios.vkCreateImage
import vulkan_ios.vkCreateImageView
import vulkan_ios.vkCreateInstance
import vulkan_ios.vkCreatePipelineCache
import vulkan_ios.vkCreatePipelineLayout
import vulkan_ios.vkCreateRenderPass
import vulkan_ios.vkCreateSampler
import vulkan_ios.vkCreateSemaphore
import vulkan_ios.vkCreateShaderModule
import vulkan_ios.vkCreateSwapchainKHR
import vulkan_ios.vkDestroyBuffer
import vulkan_ios.vkDestroyBufferView
import vulkan_ios.vkDestroyCommandPool
import vulkan_ios.vkDestroyDescriptorPool
import vulkan_ios.vkDestroyDescriptorSetLayout
import vulkan_ios.vkDestroyDevice
import vulkan_ios.vkDestroyFence
import vulkan_ios.vkDestroyFramebuffer
import vulkan_ios.vkDestroyImage
import vulkan_ios.vkDestroyImageView
import vulkan_ios.vkDestroyInstance
import vulkan_ios.vkDestroyPipeline
import vulkan_ios.vkDestroyPipelineCache
import vulkan_ios.vkDestroyPipelineLayout
import vulkan_ios.vkDestroyRenderPass
import vulkan_ios.vkDestroySampler
import vulkan_ios.vkDestroySemaphore
import vulkan_ios.vkDestroyShaderModule
import vulkan_ios.vkDestroySurfaceKHR
import vulkan_ios.vkDestroySwapchainKHR
import vulkan_ios.vkDeviceWaitIdle
import vulkan_ios.vkEndCommandBuffer
import vulkan_ios.vkEnumeratePhysicalDevices
import vulkan_ios.vkFreeCommandBuffers
import vulkan_ios.vkFreeDescriptorSets
import vulkan_ios.vkFreeMemory
import vulkan_ios.vkGetBufferMemoryRequirements
import vulkan_ios.vkGetDeviceQueue
import vulkan_ios.vkGetImageMemoryRequirements
import vulkan_ios.vkGetImageSubresourceLayout
import vulkan_ios.vkGetPhysicalDeviceFormatProperties
import vulkan_ios.vkGetPhysicalDeviceMemoryProperties
import vulkan_ios.vkGetPhysicalDeviceProperties
import vulkan_ios.vkGetPhysicalDeviceQueueFamilyProperties
import vulkan_ios.vkGetPhysicalDeviceSurfaceCapabilitiesKHR
import vulkan_ios.vkGetPhysicalDeviceSurfaceFormatsKHR
import vulkan_ios.vkGetPhysicalDeviceSurfacePresentModesKHR
import vulkan_ios.vkGetPhysicalDeviceSurfaceSupportKHR
import vulkan_ios.vkGetSwapchainImagesKHR
import vulkan_ios.vkMapMemory
import vulkan_ios.vkQueuePresentKHR
import vulkan_ios.vkQueueSubmit
import vulkan_ios.vkQueueWaitIdle
import vulkan_ios.vkResetCommandBuffer
import vulkan_ios.vkResetFences
import vulkan_ios.vkUnmapMemory
import vulkan_ios.vkUpdateDescriptorSets
import vulkan_ios.vkWaitForFences

@ExperimentalUnsignedTypes
@Suppress("ClassName")
actual object vk {
    actual fun createBuffer(
        device: VkDevice,
        createInfo: VkBufferCreateInfo,
        buffer: MutableProperty<VkBuffer>
    ): VkResult = memScoped {
        val native = alloc<VkBufferVar>()
        val result = vkCreateBuffer(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            buffer set VkBuffer().apply {
                init(native.value ?: throw IllegalStateException("buffer is null"))
            }
        }
        result
    }

    actual fun destroyBuffer(device: VkDevice, buffer: VkBuffer) {
        vkDestroyBuffer(device.native, buffer.native, null)
    }

    actual fun createBufferView(
        device: VkDevice,
        createInfo: VkBufferViewCreateInfo,
        view: MutableProperty<VkBufferView>
    ): VkResult = memScoped {
        val native = alloc<VkBufferViewVar>()
        val result = vkCreateBufferView(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            view set VkBufferView().apply {
                init(native.value ?: throw IllegalStateException("bufferView is null"))
            }
        }
        result
    }

    actual fun destroyBufferView(device: VkDevice, bufferView: VkBufferView) {
        vkDestroyBufferView(device.native, bufferView.native, null)
    }

    actual fun allocateCommandBuffers(
        device: VkDevice,
        allocateInfo: VkCommandBufferAllocateInfo,
        commandBuffers: MutableList<VkCommandBuffer>
    ): VkResult = memScoped {
        val natives = allocArray<VkCommandBufferVar>(allocateInfo.commandBufferCount)
        val result = vkAllocateCommandBuffers(device.native, allocateInfo.toNative(this), natives).toVkResult()
        if (result.isSucceeded()) {
            commandBuffers.addAll(List(allocateInfo.commandBufferCount) {
                VkCommandBuffer().apply {
                    init(natives[it] ?: throw IllegalStateException("commandBuffer is null"))
                }
            })
        }
        result
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
        vkBeginCommandBuffer(commandBuffer.native, beginInfo.toNative(this)).toVkResult()
    }

    actual fun endCommandBuffer(commandBuffer: VkCommandBuffer): VkResult = memScoped {
        vkEndCommandBuffer(commandBuffer.native).toVkResult()
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
        val native = alloc<VkCommandPoolVar>()
        val result = vkCreateCommandPool(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            commandPool set VkCommandPool().apply {
                init(native.value ?: throw IllegalStateException("commandPool is null"))
            }
        }
        result
    }

    actual fun destroyCommandPool(device: VkDevice, commandPool: VkCommandPool) {
        vkDestroyCommandPool(device.native, commandPool.native, null)
    }

    actual fun createDescriptorPool(
        device: VkDevice,
        createInfo: VkDescriptorPoolCreateInfo,
        descriptorPool: MutableProperty<VkDescriptorPool>
    ): VkResult = memScoped {
        val native = alloc<VkDescriptorPoolVar>()
        val result = vkCreateDescriptorPool(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            descriptorPool set VkDescriptorPool().apply {
                init(native.value ?: throw IllegalStateException("descriptorPool is null"))
            }
        }
        result
    }

    actual fun destroyDescriptorPool(device: VkDevice, descriptorPool: VkDescriptorPool) {
        vkDestroyDescriptorPool(device.native, descriptorPool.native, null)
    }

    actual fun allocateDescriptorSets(
        device: VkDevice,
        allocateInfo: VkDescriptorSetAllocateInfo,
        descriptorSets: MutableList<VkDescriptorSet>
    ): VkResult = memScoped {
        val native = allocArray<VkDescriptorSetVar>(allocateInfo.descriptorSetCount)
        val result = vkAllocateDescriptorSets(device.native, allocateInfo.toNative(this), native).toVkResult()
        if (result.isSucceeded()) {
            allocateInfo.descriptorSetCount.forEachIndexes {
                VkDescriptorSet().apply {
                    init(native[it] ?: throw IllegalStateException("descriptorSet is null"))
                }
            }
        }
        result
    }

    actual fun freeDescriptorSets(device: VkDevice, descriptorPool: VkDescriptorPool, descriptorSets: List<VkDescriptorSet>): VkResult = memScoped {
        val natives = allocArray<VkDescriptorSetVar>(descriptorSets.size.toLong())
        descriptorSets.forEachIndexed { index, descriptorSet -> natives[index] = descriptorSet.native }
        vkFreeDescriptorSets(device.native, descriptorPool.native, descriptorSets.size.toUInt(), natives).toVkResult()
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
        val native = alloc<VkDescriptorSetLayoutVar>()
        val result = vkCreateDescriptorSetLayout(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            setLayout set VkDescriptorSetLayout().apply {
                init(native.value ?: throw IllegalStateException("descriptorSetLayout"))
            }
        }
        result
    }

    actual fun destroyDescriptorSetLayout(device: VkDevice, descriptorSetLayout: VkDescriptorSetLayout) {
        vkDestroyDescriptorSetLayout(device.native, descriptorSetLayout.native, null)
    }

    actual fun createDevice(
        physicalDevice: VkPhysicalDevice,
        createInfo: VkDeviceCreateInfo,
        device: MutableProperty<VkDevice>
    ): VkResult = memScoped {
        val native = alloc<VkDeviceVar>()
        val result = vkCreateDevice(physicalDevice.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            device set VkDevice().apply {
                init(native.value ?: throw IllegalStateException("device is null"))
            }
        }
        result
    }

    actual fun destroyDevice(device: VkDevice) {
        vkDestroyDevice(device.native, null)
    }

    actual fun deviceWaitIdle(device: VkDevice): VkResult {
        return vkDeviceWaitIdle(device.native).toVkResult()
    }

    actual fun allocateMemory(
        device: VkDevice,
        allocateInfo: VkMemoryAllocateInfo,
        memory: MutableProperty<VkDeviceMemory>
    ): VkResult = memScoped {
        val native = alloc<VkDeviceMemoryVar>()
        val result = vkAllocateMemory(device.native, allocateInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            memory set VkDeviceMemory().apply {
                init(native.value ?: throw IllegalStateException("deviceMemory is null"))
            }
        }
        result
    }

    actual fun bindBufferMemory(device: VkDevice, buffer: VkBuffer, memory: VkDeviceMemory, memoryOffset: Long): VkResult {
        return vkBindBufferMemory(device.native, buffer.native, memory.native, memoryOffset.toULong()).toVkResult()
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
        val result = vkMapMemory(
                device.native,
                memory.native,
                offset.toULong(),
                size.toULong(),
                flags.sumBy { it.bit }.toUInt(),
                native.ptr).toVkResult()
        if (result.isSucceeded()) {
            data set MappedMemory(size).apply {
                init(native.value ?: throw IllegalStateException("mappedMemory is null"), device, memory)
            }
        }
        result
    }

    actual fun unmapMemory(device: VkDevice, memory: VkDeviceMemory) {
        vkUnmapMemory(device.native, memory.native)
    }

    actual fun freeMemory(device: VkDevice, memory: VkDeviceMemory) {
        vkFreeMemory(device.native, memory.native, null)
    }

    actual fun createFence(device: VkDevice, createInfo: VkFenceCreateInfo, fence: MutableProperty<VkFence>): VkResult = memScoped {
        val native = alloc<VkFenceVar>()
        val result = vkCreateFence(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            fence set VkFence().apply {
                init(native.value ?: throw IllegalStateException("fence is null"))
            }
        }
        result
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
                timeout.toULong()).toVkResult()
    }

    actual fun resetFences(device: VkDevice, fences: List<VkFence>): VkResult = memScoped {
        vkResetFences(device.native, fences.size.toUInt(), fences.map { it.native }.toNative(this)).toVkResult()
    }

    actual fun getPhysicalDeviceFormatProperties(
        physicalDevice: VkPhysicalDevice,
        format: VkFormat,
        formatProperties: MutableProperty<VkFormatProperties>
    ) = memScoped {
        val native = alloc<vulkan_ios.VkFormatProperties>()
        vkGetPhysicalDeviceFormatProperties(physicalDevice.native, format.value.toUInt(), native.ptr)
        formatProperties set native.toOrigin()
    }

    actual fun createFramebuffer(
        device: VkDevice,
        createInfo: VkFramebufferCreateInfo,
        framebuffer: MutableProperty<VkFramebuffer>
    ): VkResult = memScoped {
        val native = alloc<VkFramebufferVar>()
        val result = vkCreateFramebuffer(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            framebuffer set VkFramebuffer().apply {
                init(native.value ?: throw IllegalStateException("framebuffer"))
            }
        }
        result
    }

    actual fun destroyFramebuffer(device: VkDevice, framebuffer: VkFramebuffer) {
        vkDestroyFramebuffer(device.native, framebuffer.native, null)
    }

    actual fun createImage(device: VkDevice, createInfo: VkImageCreateInfo, image: MutableProperty<VkImage>): VkResult = memScoped {
        val native = alloc<VkImageVar>()
        val result = vkCreateImage(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            image set VkImage().apply {
                init(native.value ?: throw IllegalStateException("image is null"))
            }
        }
        result
    }

    actual fun destroyImage(device: VkDevice, image: VkImage) {
        vkDestroyImage(device.native, image.native, null)
    }

    actual fun bindImageMemory(device: VkDevice, image: VkImage, memory: VkDeviceMemory, memoryOffset: Long): VkResult {
        return vkBindImageMemory(device.native, image.native, memory.native, memoryOffset.toULong()).toVkResult()
    }

    actual fun createImageView(
        device: VkDevice,
        createInfo: VkImageViewCreateInfo,
        imageView: MutableProperty<VkImageView>
    ): VkResult = memScoped {
        val native = alloc<VkImageViewVar>()
        val result = vkCreateImageView(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            imageView set VkImageView().apply {
                init(native.value ?: throw IllegalStateException("imageView"))
            }
        }
        result
    }

    actual fun destroyImageView(device: VkDevice, imageView: VkImageView) {
        vkDestroyImageView(device.native, imageView.native, null)
    }

    actual fun createInstance(createInfo: VkInstanceCreateInfo, instance: MutableProperty<VkInstance>): VkResult = memScoped {
        val native = alloc<VkInstanceVar>()
        val result = vkCreateInstance(createInfo.toNative(this), null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            instance set VkInstance().apply {
                init(native.value ?: throw IllegalStateException("instance"))
            }
        }
        result
    }

    actual fun destroyInstance(instance: VkInstance) {
        vkDestroyInstance(instance.native, null)
    }

    actual fun getBufferMemoryRequirements(
        device: VkDevice,
        buffer: VkBuffer,
        memoryRequirements: MutableProperty<VkMemoryRequirements>
    ) = memScoped {
        val native = alloc<vulkan_ios.VkMemoryRequirements>()
        vkGetBufferMemoryRequirements(device.native, buffer.native, native.ptr)
        memoryRequirements set native.toOrigin()
    }

    actual fun getImageMemoryRequirements(
        device: VkDevice,
        image: VkImage,
        memoryRequirements: MutableProperty<VkMemoryRequirements>
    ) = memScoped {
        val native = alloc<vulkan_ios.VkMemoryRequirements>()
        vkGetImageMemoryRequirements(device.native, image.native, native.ptr)
        memoryRequirements set native.toOrigin()
    }

    actual fun enumeratePhysicalDevices(instance: VkInstance, physicalDevices: MutableList<VkPhysicalDevice>): VkResult = memScoped {
        val count = alloc<UIntVar>()
        var result = vkEnumeratePhysicalDevices(instance.native, count.ptr, null).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        val natives = allocArray<VkPhysicalDeviceVar>(count.value.toInt())
        result = vkEnumeratePhysicalDevices(instance.native, count.ptr, natives).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        count.value.toInt().forEachIndexes {
            physicalDevices.add(VkPhysicalDevice().apply {
                init(natives[it] ?: throw IllegalStateException("physicalDevice is null"))
            })
        }
        result
    }

    actual fun getPhysicalDeviceMemoryProperties(
        physicalDevice: VkPhysicalDevice,
        memoryProperties: MutableProperty<VkPhysicalDeviceMemoryProperties>
    ) = memScoped {
        val native = alloc<vulkan_ios.VkPhysicalDeviceMemoryProperties>()
        vkGetPhysicalDeviceMemoryProperties(physicalDevice.native, native.ptr)
        memoryProperties set native.toOrigin()
    }

    actual fun getPhysicalDeviceProperties(
        physicalDevice: VkPhysicalDevice,
        properties: MutableProperty<VkPhysicalDeviceProperties>
    ) = memScoped {
        val native = alloc<vulkan_ios.VkPhysicalDeviceProperties>()
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
        result
    }

    actual fun destroyPipeline(device: VkDevice, pipeline: VkPipeline) {
        vkDestroyPipeline(device.native, pipeline.native, null)
    }

    actual fun createPipelineCache(
        device: VkDevice,
        createInfo: VkPipelineCacheCreateInfo,
        pipelineCache: MutableProperty<VkPipelineCache>
    ): VkResult = memScoped {
        val native = alloc<VkPipelineCacheVar>()
        val result = vkCreatePipelineCache(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        pipelineCache set VkPipelineCache().apply { init(native.value ?: throw IllegalStateException("pipelineCache")) }
        result
    }

    actual fun destroyPipelineCache(device: VkDevice, pipelineCache: VkPipelineCache) {
        vkDestroyPipelineCache(device.native, pipelineCache.native, null)
    }

    actual fun createPipelineLayout(
        device: VkDevice,
        createInfo: VkPipelineLayoutCreateInfo,
        pipelineLayout: MutableProperty<VkPipelineLayout>
    ): VkResult = memScoped {
        val native = alloc<VkPipelineLayoutVar>()
        val result = vkCreatePipelineLayout(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        pipelineLayout set VkPipelineLayout().apply {
            init(native.value ?: throw IllegalStateException("pipelineLayout"))
        }
        result
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
        val native = alloc<VkQueueVar>()
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

        val properties = allocArray<vulkan_ios.VkQueueFamilyProperties>(count.value.toInt())
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
        result
    }

    actual fun destroyRenderPass(device: VkDevice, renderPass: VkRenderPass) {
        vkDestroyRenderPass(device.native, renderPass.native, null)
    }

    actual fun createSampler(
        device: VkDevice,
        createInfo: VkSamplerCreateInfo,
        sampler: MutableProperty<VkSampler>
    ): VkResult = memScoped {
        val native = alloc<VkSamplerVar>()
        val result = vkCreateSampler(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        sampler set VkSampler().apply {
            init(native.value ?: throw IllegalStateException("sampler is null"))
        }
        result
    }

    actual fun destroySampler(device: VkDevice, sampler: VkSampler) {
        vkDestroySampler(device.native, sampler.native, null)
    }

    actual fun createSemaphore(
        device: VkDevice,
        createInfo: VkSemaphoreCreateInfo,
        semaphore: MutableProperty<VkSemaphore>
    ): VkResult = memScoped {
        val native = alloc<VkSemaphoreVar>()
        val result = vkCreateSemaphore(device.native, createInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        semaphore set VkSemaphore().apply {
            init(native.value ?: throw IllegalStateException("semaphore is null"))
        }
        result
    }

    actual fun destroySemaphore(device: VkDevice, semaphore: VkSemaphore) {
        vkDestroySemaphore(device.native, semaphore.native, null)
    }

    actual fun createShaderModule(
        device: VkDevice,
        shaderModuleCreateInfo: VkShaderModuleCreateInfo,
        shaderModule: MutableProperty<VkShaderModule>
    ): VkResult = memScoped {
        val native = alloc<VkShaderModuleVar>()
        val result = vkCreateShaderModule(device.native, shaderModuleCreateInfo.toNative(this), null, native.ptr).toVkResult()
        if (!result.isSucceeded()) {
            return@memScoped result
        }

        shaderModule set VkShaderModule().apply {
            init(native.value ?: throw IllegalStateException("shaderModule"))
        }
        result
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
        val native = alloc<vulkan_ios.VkSubresourceLayout>()
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
        result
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

        val natives = allocArray<VkPresentModeKHRVar>(count.value.toInt())
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
        val native = alloc<vulkan_ios.VkSurfaceCapabilitiesKHR>()
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

        val natives = allocArray<vulkan_ios.VkSurfaceFormatKHR>(count.value.toInt())
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
        val native = alloc<VkSwapchainKHRVar>()
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

        val natives = allocArray<VkImageVar>(count.value.toInt())
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

    fun createIOSSurfaceMVK(
        instance: VkInstance,
        createInfo: VkIOSSurfaceCreateInfoMVK,
        surface: MutableProperty<VkSurface>
    ): VkResult = memScoped {
        val native = alloc<VkSurfaceKHRVar>()
        val result = vkCreateIOSSurfaceMVK(instance.native, createInfo.ptr, null, native.ptr).toVkResult()
        if (result.isSucceeded()) {
            surface set VkSurface().apply { init(native.value ?: throw IllegalStateException("surface is null")) }
        }
        result
    }

    actual fun destroySurfaceKHR(instance: VkInstance, surface: VkSurface) {
        vkDestroySurfaceKHR(instance.native, surface.native, null)
    }
}
