package io.github.inoutch.kotlin.vulkan.api

enum class VkImageType(val value: Int) {
    VK_IMAGE_TYPE_1D(0),
    VK_IMAGE_TYPE_2D(1),
    VK_IMAGE_TYPE_3D(2),
    VK_IMAGE_TYPE_MAX_ENUM(0x7FFFFFFF),
}
