package io.github.inoutch.kotlin.vulkan.api

enum class VkImageUsageFlagBits(val bit: Int) {
    VK_IMAGE_USAGE_TRANSFER_SRC_BIT(0x00000001),
    VK_IMAGE_USAGE_TRANSFER_DST_BIT(0x00000002),
    VK_IMAGE_USAGE_SAMPLED_BIT(0x00000004),
    VK_IMAGE_USAGE_STORAGE_BIT(0x00000008),
    VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT(0x00000010),
    VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT(0x00000020),
    VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT(0x00000040),
    VK_IMAGE_USAGE_INPUT_ATTACHMENT_BIT(0x00000080),
    VK_IMAGE_USAGE_SHADING_RATE_IMAGE_BIT_NV(0x00000100),
    VK_IMAGE_USAGE_FRAGMENT_DENSITY_MAP_BIT_EXT(0x00000200),
    VK_IMAGE_USAGE_FLAG_BITS_MAX_ENUM(0x7FFFFFFF),
}
