package io.github.inoutch.kotlin.vulkan.api

enum class VkSamplerMipmapMode(val value: Int) {
    VK_SAMPLER_MIPMAP_MODE_NEAREST(0),
    VK_SAMPLER_MIPMAP_MODE_LINEAR(1),
    VK_SAMPLER_MIPMAP_MODE_MAX_ENUM(0x7FFFFFFF),
}
