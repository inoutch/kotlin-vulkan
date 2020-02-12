package io.github.inoutch.kotlin.vulkan.api

class VkSurfaceCapabilitiesKHR(
    val minImageCount: Int,
    val maxImageCount: Int,
    val currentExtent: VkExtent2D,
    val minImageExtent: VkExtent2D,
    val maxImageExtent: VkExtent2D,
    val maxImageArrayLayers: Int,
    val supportedTransforms: List<VkSurfaceTransformFlagBitsKHR>,
    val currentTransform: VkSurfaceTransformFlagBitsKHR,
    val supportedCompositeAlpha: List<VkCompositeAlphaFlagBitsKHR>,
    val supportedUsageFlagBits: List<VkImageUsageFlagBits>
)
