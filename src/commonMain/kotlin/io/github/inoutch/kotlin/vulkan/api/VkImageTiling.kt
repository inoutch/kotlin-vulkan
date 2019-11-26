package io.github.inoutch.kotlin.vulkan.api

enum class VkImageTiling(val value: Int) {
    VK_IMAGE_TILING_OPTIMAL(0),
    VK_IMAGE_TILING_LINEAR(1),
    VK_IMAGE_TILING_DRM_FORMAT_MODIFIER_EXT(1000158000),
    VK_IMAGE_TILING_MAX_ENUM(0x7FFFFFFF),
}
