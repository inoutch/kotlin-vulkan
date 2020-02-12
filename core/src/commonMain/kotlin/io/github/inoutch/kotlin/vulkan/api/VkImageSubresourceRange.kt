package io.github.inoutch.kotlin.vulkan.api

class VkImageSubresourceRange(
    val aspectMask: List<VkImageAspectFlagBits>,
    val baseMipLevel: Int,
    val levelCount: Int,
    val baseArrayLayer: Int,
    val layerCount: Int
)
