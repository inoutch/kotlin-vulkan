package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkDescriptorBufferInfo.copyToNative(native: vulkan_android.VkDescriptorBufferInfo) {
    native.buffer = buffer.native
    native.offset = offset.toULong()
    native.range = range.toULong()
}

@ExperimentalUnsignedTypes
fun List<VkDescriptorBufferInfo>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_android.VkDescriptorBufferInfo>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
