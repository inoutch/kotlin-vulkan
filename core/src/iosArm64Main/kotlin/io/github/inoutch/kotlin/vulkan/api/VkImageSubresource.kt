package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr

@ExperimentalUnsignedTypes
fun VkImageSubresource.copyToNative(native: vulkan_ios.VkImageSubresource) {
    native.aspectMask = aspectMask.sumBy { it.bit }.toUInt()
    native.arrayLayer = arrayLayer.toUInt()
    native.mipLevel = mipLevel.toUInt()
}

@ExperimentalUnsignedTypes
fun VkImageSubresource.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkImageSubresource>()
                .also { copyToNative(it) }.ptr
