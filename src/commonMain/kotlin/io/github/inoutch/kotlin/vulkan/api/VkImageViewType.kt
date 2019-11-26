package io.github.inoutch.kotlin.vulkan.api

enum class VkImageViewType(val value: Int) {
    VK_IMAGE_VIEW_TYPE_1D(0),
    VK_IMAGE_VIEW_TYPE_2D(1),
    VK_IMAGE_VIEW_TYPE_3D(2),
    VK_IMAGE_VIEW_TYPE_CUBE(3),
    VK_IMAGE_VIEW_TYPE_1D_ARRAY(4),
    VK_IMAGE_VIEW_TYPE_2D_ARRAY(5),
    VK_IMAGE_VIEW_TYPE_CUBE_ARRAY(6),
    VK_IMAGE_VIEW_TYPE_MAX_ENUM(0x7FFFFFFF),
}
