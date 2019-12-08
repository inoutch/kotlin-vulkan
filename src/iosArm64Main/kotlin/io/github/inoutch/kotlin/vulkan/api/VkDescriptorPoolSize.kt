package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkDescriptorPoolSize.copyToNative(native: vulkan_ios.VkDescriptorPoolSize) {
    native.type = type.value.toUInt()
    native.descriptorCount = descriptorCount.toUInt()
}

@ExperimentalUnsignedTypes
fun List<VkDescriptorPoolSize>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_ios.VkDescriptorPoolSize>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
