package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_ios.VkSurfaceCapabilitiesKHR.toOrigin(): VkSurfaceCapabilitiesKHR {
    return VkSurfaceCapabilitiesKHR(
            minImageCount.toInt(),
            maxImageCount.toInt(),
            currentExtent.toOrigin(),
            minImageExtent.toOrigin(),
            maxImageExtent.toOrigin(),
            maxImageArrayLayers.toInt(),
            VkSurfaceTransformFlagBitsKHR.values()
                    .filter { it.bit.toUInt() and supportedTransforms != 0u },
            VkSurfaceTransformFlagBitsKHR.values()
                    .find { it.bit.toUInt() == currentTransform } ?: throw IllegalStateException("currentTransform is null"),
            VkCompositeAlphaFlagBitsKHR.values()
                    .filter { it.bit.toUInt() and supportedCompositeAlpha != 0u },
            VkImageUsageFlagBits.values()
                    .filter { it.bit.toUInt() and supportedUsageFlags != 0u })
}
