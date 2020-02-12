package io.github.inoutch.kotlin.vulkan.api

enum class VkFenceCreateFlagBits(val bit: Int) {
    VK_FENCE_CREATE_SIGNALED_BIT(0x00000001),
    VK_FENCE_CREATE_FLAG_BITS_MAX_ENUM(0x7FFFFFFF),
}
