package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.ptr

fun VkOffset2D.copyToNative(native: vulkan_android.VkOffset2D) {
    native.x = x
    native.y = y
}

fun VkOffset2D.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkOffset2D>()
                .also { copyToNative(it) }.ptr

fun List<VkOffset2D>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_android.VkOffset2D>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
