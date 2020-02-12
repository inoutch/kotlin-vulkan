package io.github.inoutch.kotlin.vulkan.api

enum class VkColorComponentFlagBits(val bit: Int) {
    VK_COLOR_COMPONENT_R_BIT(0x1),
    VK_COLOR_COMPONENT_G_BIT(0x2),
    VK_COLOR_COMPONENT_B_BIT(0x4),
    VK_COLOR_COMPONENT_A_BIT(0x8),
    VK_COLOR_COMPONENT_FLAG_BITS_MAX_ENUM(0x7FFFFFFF),
}
