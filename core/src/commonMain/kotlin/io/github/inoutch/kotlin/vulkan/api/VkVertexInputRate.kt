package io.github.inoutch.kotlin.vulkan.api

enum class VkVertexInputRate(val value: Int) {
    VK_VERTEX_INPUT_RATE_VERTEX(0),
    VK_VERTEX_INPUT_RATE_INSTANCE(1),
    VK_VERTEX_INPUT_RATE_MAX_ENUM(0x7FFFFFFF),
}
