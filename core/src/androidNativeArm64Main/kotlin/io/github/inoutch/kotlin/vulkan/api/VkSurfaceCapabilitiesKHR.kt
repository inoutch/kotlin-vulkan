package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_android.VkSurfaceCapabilitiesKHR.toOrigin(): VkSurfaceCapabilitiesKHR {
    return VkSurfaceCapabilitiesKHR(
            minImageCount.toInt(),
            maxImageCount.toInt(),
            currentExtent.toOrigin(),
            minImageExtent.toOrigin(),
            maxImageExtent.toOrigin(),
            maxImageArrayLayers.toInt(),
            VkSurfaceTransformFlagBitsKHR.values()
                    .filter { it.bit and supportedTransforms.toInt() == it.bit },
            VkSurfaceTransformFlagBitsKHR.values()
                    .find { it.bit.toUInt() == currentTransform } ?: throw IllegalStateException("currentTransform is null"),
            VkCompositeAlphaFlagBitsKHR.values()
                    .filter { it.bit and supportedCompositeAlpha.toInt() == it.bit },
            VkImageUsageFlagBits.values()
                    .filter { it.bit and supportedUsageFlags.toInt() == it.bit })
}
