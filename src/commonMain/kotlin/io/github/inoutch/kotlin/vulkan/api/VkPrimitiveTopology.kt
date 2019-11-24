package io.github.inoutch.kotlin.vulkan.api

enum class VkPrimitiveTopology(val value: Int) {
    VK_PRIMITIVE_TOPOLOGY_POINT_LIST(0),
    VK_PRIMITIVE_TOPOLOGY_LINE_LIST(1),
    VK_PRIMITIVE_TOPOLOGY_LINE_STRIP(2),
    VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST(3),
    VK_PRIMITIVE_TOPOLOGY_TRIANGLE_STRIP(4),
    VK_PRIMITIVE_TOPOLOGY_TRIANGLE_FAN(5),
    VK_PRIMITIVE_TOPOLOGY_LINE_LIST_WITH_ADJACENCY(6),
    VK_PRIMITIVE_TOPOLOGY_LINE_STRIP_WITH_ADJACENCY(7),
    VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST_WITH_ADJACENCY(8),
    VK_PRIMITIVE_TOPOLOGY_TRIANGLE_STRIP_WITH_ADJACENCY(9),
    VK_PRIMITIVE_TOPOLOGY_PATCH_LIST(10),
    VK_PRIMITIVE_TOPOLOGY_MAX_ENUM(0x7FFFFFFF)
}
