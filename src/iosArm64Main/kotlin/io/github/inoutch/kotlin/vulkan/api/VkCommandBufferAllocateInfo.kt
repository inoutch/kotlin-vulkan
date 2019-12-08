package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO

@ExperimentalUnsignedTypes
fun VkCommandBufferAllocateInfo.copyToNative(native: vulkan_ios.VkCommandBufferAllocateInfo) {
    native.sType = VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO
    native.pNext = null
    native.commandPool = commandPool.native
    native.commandBufferCount = commandBufferCount.toUInt()
    native.level = level.value.toUInt()
}

@ExperimentalUnsignedTypes
fun VkCommandBufferAllocateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkCommandBufferAllocateInfo>().also { copyToNative(it) }.ptr
