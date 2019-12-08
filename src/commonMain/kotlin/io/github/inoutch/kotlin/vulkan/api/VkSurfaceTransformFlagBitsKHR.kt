package io.github.inoutch.kotlin.vulkan.api

enum class VkSurfaceTransformFlagBitsKHR(val bit: Int) {
    VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR(0x00000001),
    VK_SURFACE_TRANSFORM_ROTATE_90_BIT_KHR(0x00000002),
    VK_SURFACE_TRANSFORM_ROTATE_180_BIT_KHR(0x00000004),
    VK_SURFACE_TRANSFORM_ROTATE_270_BIT_KHR(0x00000008),
    VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_BIT_KHR(0x00000010),
    VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_90_BIT_KHR(0x00000020),
    VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_180_BIT_KHR(0x00000040),
    VK_SURFACE_TRANSFORM_HORIZONTAL_MIRROR_ROTATE_270_BIT_KHR(0x00000080),
    VK_SURFACE_TRANSFORM_INHERIT_BIT_KHR(0x00000100),
    VK_SURFACE_TRANSFORM_FLAG_BITS_MAX_ENUM_KHR(0x7FFFFFFF),
}
