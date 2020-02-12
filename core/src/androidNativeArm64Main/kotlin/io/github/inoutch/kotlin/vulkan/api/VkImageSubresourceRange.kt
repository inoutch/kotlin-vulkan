package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkImageSubresourceRange.copyToNative(native: vulkan_android.VkImageSubresourceRange) {
    native.aspectMask = aspectMask.sumBy { it.bit }.toUInt()
    native.baseMipLevel = baseMipLevel.toUInt()
    native.levelCount = levelCount.toUInt()
    native.baseArrayLayer = baseArrayLayer.toUInt()
    native.layerCount = layerCount.toUInt()
}

@ExperimentalUnsignedTypes
fun List<VkImageSubresourceRange>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_android.VkImageSubresourceRange>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
