package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import kotlinx.cinterop.set

fun VkClearColorValue.copyToNative(native: vulkan_android.VkClearColorValue) {
    native.float32[0] = array[0]
    native.float32[1] = array[1]
    native.float32[2] = array[2]
    native.float32[3] = array[3]
}

fun VkClearColorValue.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkClearColorValue>()
                .also { copyToNative(it) }.ptr
