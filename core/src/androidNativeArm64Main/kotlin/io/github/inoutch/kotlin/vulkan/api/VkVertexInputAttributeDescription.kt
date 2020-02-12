package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkVertexInputAttributeDescription.copyToNative(native: vulkan_android.VkVertexInputAttributeDescription) {
    native.binding = binding.toUInt()
    native.format = format.value.toUInt()
    native.location = location.toUInt()
    native.offset = offset.toUInt()
}

@ExperimentalUnsignedTypes
fun List<VkVertexInputAttributeDescription>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_android.VkVertexInputAttributeDescription>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
