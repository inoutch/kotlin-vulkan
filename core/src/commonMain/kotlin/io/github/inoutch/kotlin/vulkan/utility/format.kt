package io.github.inoutch.kotlin.vulkan.utility

import io.github.inoutch.kotlin.vulkan.api.VkFormat
import io.github.inoutch.kotlin.vulkan.api.VkFormatFeatureFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkFormatProperties
import io.github.inoutch.kotlin.vulkan.api.VkImageTiling
import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDevice
import io.github.inoutch.kotlin.vulkan.api.vk

fun findSupportedFormat(
    physicalDevice: VkPhysicalDevice,
    supportedFormatCandidates: List<VkFormat>,
    tiling: VkImageTiling,
    featureFlagBits: List<VkFormatFeatureFlagBits>
): VkFormat? {
    return supportedFormatCandidates.find { format ->
        val formatPropertiesRef = MutableProperty<VkFormatProperties>()
        vk.getPhysicalDeviceFormatProperties(physicalDevice, format, formatPropertiesRef)

        val formatProperties = formatPropertiesRef.value
        formatProperties?.isSupported(tiling, featureFlagBits) ?: false
    }
}

fun VkFormat.hasStencilComponent(): Boolean {
    return this == VkFormat.VK_FORMAT_D32_SFLOAT_S8_UINT || this == VkFormat.VK_FORMAT_D24_UNORM_S8_UINT
}
