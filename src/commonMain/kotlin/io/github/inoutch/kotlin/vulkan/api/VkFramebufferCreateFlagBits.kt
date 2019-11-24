package io.github.inoutch.kotlin.vulkan.api

enum class VkFramebufferCreateFlagBits(val bit: Int) {
    VK_FRAMEBUFFER_CREATE_IMAGELESS_BIT_KHR(0x00000001),
    VK_FRAMEBUFFER_CREATE_FLAG_BITS_MAX_ENUM(0x7FFFFFFF),
}
