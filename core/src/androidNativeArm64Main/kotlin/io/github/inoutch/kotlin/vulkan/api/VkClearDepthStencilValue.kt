package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr

@ExperimentalUnsignedTypes
fun VkClearDepthStencilValue.copyToNative(native: vulkan_android.VkClearDepthStencilValue) {
    native.depth = depth
    native.stencil = stencil.toUInt()
}

@ExperimentalUnsignedTypes
fun VkClearDepthStencilValue.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkClearDepthStencilValue>()
                .also { copyToNative(it) }.ptr
