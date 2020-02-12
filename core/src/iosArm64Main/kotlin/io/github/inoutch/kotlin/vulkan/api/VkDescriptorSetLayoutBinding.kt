package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkDescriptorSetLayoutBinding.copyToNative(
    native: vulkan_ios.VkDescriptorSetLayoutBinding,
    scope: MemScope
) {
    native.binding = binding.toUInt()
    native.descriptorType = descriptorType.value.toUInt()
    native.descriptorCount = descriptorCount.toUInt()
    native.stageFlags = stageFlags.sumBy { it.bit }.toUInt()
    native.pImmutableSamplers = immutableSamplers?.let { listOf(it.native) }?.toNative(scope)
}

@ExperimentalUnsignedTypes
fun List<VkDescriptorSetLayoutBinding>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_ios.VkDescriptorSetLayoutBinding>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index], scope) } }
