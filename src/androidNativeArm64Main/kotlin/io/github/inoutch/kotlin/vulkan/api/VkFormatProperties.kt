package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_android.VkFormatProperties.toOrigin(): VkFormatProperties {
    return VkFormatProperties(
            VkFormatFeatureFlagBits.values().filter { it.bit and linearTilingFeatures.toInt() == it.bit },
            VkFormatFeatureFlagBits.values().filter { it.bit and optimalTilingFeatures.toInt() == it.bit },
            VkFormatFeatureFlagBits.values().filter { it.bit and bufferFeatures.toInt() == it.bit })
}
