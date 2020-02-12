package io.github.inoutch.kotlin.vulkan.api

class VkFormatProperties(
    val linearTilingFeatures: List<VkFormatFeatureFlagBits>,
    val optimalTilingFeatures: List<VkFormatFeatureFlagBits>,
    val bufferFeatures: List<VkFormatFeatureFlagBits>
) {
    fun isSupported(
        tiling: VkImageTiling,
        featureFlagBits: List<VkFormatFeatureFlagBits>
    ): Boolean {
        val bits = featureFlagBits.sumBy { it.bit }
        if (tiling == VkImageTiling.VK_IMAGE_TILING_LINEAR) {
            return (linearTilingFeatures.sumBy { it.bit } and bits) == bits
        }
        if (tiling == VkImageTiling.VK_IMAGE_TILING_OPTIMAL) {
            return (optimalTilingFeatures.sumBy { it.bit } and bits) == bits
        }
        return false
    }
}
