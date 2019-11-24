package io.github.inoutch.kotlin.vulkan.api

enum class VkSwapchainCreateFlagBitsKHR(val bit: Int) {
    VK_SWAPCHAIN_CREATE_SPLIT_INSTANCE_BIND_REGIONS_BIT_KHR(0x00000001),
    VK_SWAPCHAIN_CREATE_PROTECTED_BIT_KHR(0x00000002),
    VK_SWAPCHAIN_CREATE_MUTABLE_FORMAT_BIT_KHR(0x00000004),
    VK_SWAPCHAIN_CREATE_FLAG_BITS_MAX_ENUM_KHR(0x7FFFFFFF),
}
