package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkBufferCopy.copyToNative(native: vulkan_ios.VkBufferCopy) {
    native.srcOffset = srcOffset.toULong()
    native.dstOffset = dstOffset.toULong()
    native.size = size.toULong()
}

@ExperimentalUnsignedTypes
fun VkBufferCopy.toNative(scope: MemScope): vulkan_ios.VkBufferCopy =
        scope.alloc<vulkan_ios.VkBufferCopy>().also { copyToNative(it) }

@ExperimentalUnsignedTypes
fun List<VkBufferCopy>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_ios.VkBufferCopy>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
