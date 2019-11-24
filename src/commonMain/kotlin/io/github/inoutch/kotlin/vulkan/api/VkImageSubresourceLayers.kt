package io.github.inoutch.kotlin.vulkan.api

class VkImageSubresourceLayers(
    val aspectMask: List<VkImageAspectFlagBits>,
    val mipLevel: Int,
    val baseArrayLayer: Int,
    val layerCount: Int
)
