package io.github.inoutch.kotlin.vulkan.api

enum class VkSamplerAddressMode(val value: Int) {
    VK_SAMPLER_ADDRESS_MODE_REPEAT(0),
    VK_SAMPLER_ADDRESS_MODE_MIRRORED_REPEAT(1),
    VK_SAMPLER_ADDRESS_MODE_CLAMP_TO_EDGE(2),
    VK_SAMPLER_ADDRESS_MODE_CLAMP_TO_BORDER(3),
    VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE(4),
    VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE_KHR(VK_SAMPLER_ADDRESS_MODE_MIRROR_CLAMP_TO_EDGE.value),
    VK_SAMPLER_ADDRESS_MODE_MAX_ENUM(0x7FFFFFFF),
}
