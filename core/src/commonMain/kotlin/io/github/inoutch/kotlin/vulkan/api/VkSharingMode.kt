package io.github.inoutch.kotlin.vulkan.api

enum class VkSharingMode(val value: Int) {
    VK_SHARING_MODE_EXCLUSIVE(0),
    VK_SHARING_MODE_CONCURRENT(1),
    VK_SHARING_MODE_MAX_ENUM(0x7FFFFFFF),
}
