package io.github.inoutch.kotlin.vulkan.api

class VkFormatProperties(
    val linearTilingFeatures: List<VkFormatFeatureFlagBits>,
    val optimalTilingFeatures: List<VkFormatFeatureFlagBits>,
    val bufferFeatures: List<VkFormatFeatureFlagBits>
)
