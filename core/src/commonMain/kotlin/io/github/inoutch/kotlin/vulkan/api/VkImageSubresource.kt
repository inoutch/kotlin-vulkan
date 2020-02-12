package io.github.inoutch.kotlin.vulkan.api

class VkImageSubresource(
    val aspectMask: List<VkImageAspectFlagBits>,
    val mipLevel: Int,
    val arrayLayer: Int
)
