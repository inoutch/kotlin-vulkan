package io.github.inoutch.kotlin.vulkan.api

fun org.lwjgl.vulkan.VkFormatProperties.toOrigin(): VkFormatProperties {
    return VkFormatProperties(
            VkFormatFeatureFlagBits.values().filter { (it.bit and linearTilingFeatures()) == it.bit },
            VkFormatFeatureFlagBits.values().filter { (it.bit and optimalTilingFeatures()) == it.bit },
            VkFormatFeatureFlagBits.values().filter { (it.bit and bufferFeatures()) == it.bit })
}
