package io.github.inoutch.kotlin.vulkan.api

enum class VkIndexType(val type: Int) {
    VK_INDEX_TYPE_UINT16(0),
    VK_INDEX_TYPE_UINT32(1),
    VK_INDEX_TYPE_NONE_NV(1000165000),
    VK_INDEX_TYPE_UINT8_EXT(1000265000),
    VK_INDEX_TYPE_MAX_ENUM(0x7FFFFFFF)
}
