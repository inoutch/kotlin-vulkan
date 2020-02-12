package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkPushConstantRange.copyToNative(native: vulkan_ios.VkPushConstantRange) {
    native.stageFlags = stageFlags.sumBy { it.bit }.toUInt()
    native.offset = offset.toUInt()
    native.size = offset.toUInt()
}

@ExperimentalUnsignedTypes
fun List<VkPushConstantRange>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_ios.VkPushConstantRange>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
