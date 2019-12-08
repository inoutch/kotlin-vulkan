package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_FENCE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkFenceCreateInfo.copyToNative(native: vulkan_ios.VkFenceCreateInfo) {
    native.sType = VK_STRUCTURE_TYPE_FENCE_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
}

@ExperimentalUnsignedTypes
fun VkFenceCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkFenceCreateInfo>()
                .also { copyToNative(it) }.ptr
