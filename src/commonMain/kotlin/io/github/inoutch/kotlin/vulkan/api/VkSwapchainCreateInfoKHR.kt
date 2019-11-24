package io.github.inoutch.kotlin.vulkan.api

class VkSwapchainCreateInfoKHR(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_SWAPCHAIN_CREATE_INFO_KHR,
    val flags: List<VkSwapchainCreateFlagBitsKHR>,
    val surface: VkSurface,
    val minImageCount: Int,
    val imageFormat: VkFormat,
    val imageColorSpace: VkColorSpaceKHR,
    val imageExtent: VkExtent2D,
    val imageArrayLayers: Int,
    val imageUsage: List<VkImageUsageFlagBits>,
    val imageSharingMode: VkSharingMode,
    val queueFamilyIndices: List<Int>?,
    val preTransform: VkSurfaceTransformFlagBitsKHR,
    val compositeAlpha: VkCompositeAlphaFlagBitsKHR,
    val presentMode: VkPresentModeKHR,
    val clipped: Boolean,
    val oldSwapchain: VkSwapchainKHR?
)
