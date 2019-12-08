package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope
import org.lwjgl.vulkan.VK10

fun VkCommandBufferAllocateInfo.copyToNative(native: org.lwjgl.vulkan.VkCommandBufferAllocateInfo) {
    native.sType(VK10.VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO)
            .pNext(VK10.VK_NULL_HANDLE)
            .commandPool(commandPool.native)
            .level(level.value)
            .commandBufferCount(commandBufferCount)
}

fun VkCommandBufferAllocateInfo.toNative(memScope: MemScope): org.lwjgl.vulkan.VkCommandBufferAllocateInfo =
        memScope.add(org.lwjgl.vulkan.VkCommandBufferAllocateInfo.calloc()
                .also { copyToNative(it) })
