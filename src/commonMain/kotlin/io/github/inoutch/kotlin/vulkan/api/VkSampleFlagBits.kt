package io.github.inoutch.kotlin.vulkan.api

enum class VkSampleFlagBits(val value: Int) {
    VK_SAMPLE_COUNT_1_BIT(0x1),
    VK_SAMPLE_COUNT_2_BIT(0x2),
    VK_SAMPLE_COUNT_4_BIT(0x4),
    VK_SAMPLE_COUNT_8_BIT(0x8),
    VK_SAMPLE_COUNT_16_BIT(0x10),
    VK_SAMPLE_COUNT_32_BIT(0x20),
    VK_SAMPLE_COUNT_64_BIT(0x40),
}
