package io.github.inoutch.kotlin.vulkan.api

enum class VkDeviceQueueCreateFlagBits(val bit: Int) {
    VK_DEVICE_QUEUE_CREATE_PROTECTED_BIT(0x00000001),
    VK_DEVICE_QUEUE_CREATE_FLAG_BITS_MAX_ENUM(0x7FFFFFFF),
}
