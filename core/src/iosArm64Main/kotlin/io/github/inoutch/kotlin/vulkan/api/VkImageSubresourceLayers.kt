package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun VkImageSubresourceLayers.copyToNative(native: vulkan_ios.VkImageSubresourceLayers) {
    native.aspectMask = aspectMask.sumBy { it.bit }.toUInt()
    native.mipLevel = mipLevel.toUInt()
    native.baseArrayLayer = baseArrayLayer.toUInt()
    native.layerCount = layerCount.toUInt()
}
