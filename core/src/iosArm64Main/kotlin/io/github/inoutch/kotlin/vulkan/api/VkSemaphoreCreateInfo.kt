package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkSemaphoreCreateInfo.copyToNative(native: vulkan_ios.VkSemaphoreCreateInfo) {
    native.sType = VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
}

@ExperimentalUnsignedTypes
fun VkSemaphoreCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkSemaphoreCreateInfo>()
                .also { copyToNative(it) }.ptr
