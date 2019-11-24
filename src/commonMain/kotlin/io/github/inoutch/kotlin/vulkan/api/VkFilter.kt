package io.github.inoutch.kotlin.vulkan.api

enum class VkFilter(val value: Int) {
    VK_FILTER_NEAREST(0),
    VK_FILTER_LINEAR(1),
    VK_FILTER_CUBIC_IMG(1000015000),
    VK_FILTER_CUBIC_EXT(VK_FILTER_CUBIC_IMG.value),
    VK_FILTER_MAX_ENUM(0x7FFFFFFF),
}
