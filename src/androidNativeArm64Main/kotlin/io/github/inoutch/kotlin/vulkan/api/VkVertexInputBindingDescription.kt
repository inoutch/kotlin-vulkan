package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkVertexInputBindingDescription.copyToNative(native: vulkan_android.VkVertexInputBindingDescription) {
    native.binding = binding.toUInt()
    native.stride = stride.toUInt()
    native.inputRate = inputRate.value.toUInt()
}

@ExperimentalUnsignedTypes
fun List<VkVertexInputBindingDescription>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_android.VkVertexInputBindingDescription>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
