package io.github.inoutch.kotlin.vulkan.api

enum class VkDeviceCreateFlagBits(val bit: Int) {
    VK_DEVICE_CREATE_VALIDATION_BIT(0x00000001),
    VK_DEVICE_CREATE_MULTI_DEVICE_IQ_MATCH_BIT(0x00000002),
}
