package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkBufferCopy.copyToNative(native: vulkan_android.VkBufferCopy) {
    native.srcOffset = srcOffset.toULong()
    native.dstOffset = dstOffset.toULong()
    native.size = size.toULong()
}

@ExperimentalUnsignedTypes
fun VkBufferCopy.toNative(scope: MemScope): vulkan_android.VkBufferCopy =
        scope.alloc<vulkan_android.VkBufferCopy>().also { copyToNative(it) }

@ExperimentalUnsignedTypes
fun List<VkBufferCopy>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_android.VkBufferCopy>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
