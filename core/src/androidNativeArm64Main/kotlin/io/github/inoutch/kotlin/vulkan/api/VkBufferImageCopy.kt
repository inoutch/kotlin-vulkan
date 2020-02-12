package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.ptr

@ExperimentalUnsignedTypes
fun VkBufferImageCopy.copyToNative(native: vulkan_android.VkBufferImageCopy) {
    native.bufferOffset = bufferOffset.toULong()
    native.bufferImageHeight = bufferImageHeight.toUInt()
    native.bufferRowLength = bufferRowLength.toUInt()
    imageExtent.copyToNative(native.imageExtent)
    imageOffset.copyToNative(native.imageOffset)
    imageSubresource.copyToNative(native.imageSubresource)
}

@ExperimentalUnsignedTypes
fun VkBufferImageCopy.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkBufferImageCopy>()
                .also { copyToNative(it) }.ptr

@ExperimentalUnsignedTypes
fun List<VkBufferImageCopy>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_android.VkBufferImageCopy>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
