package io.github.inoutch.kotlin.vulkan.api

enum class VkPipelineBindPoint(val value: Int) {
    VK_PIPELINE_BIND_POINT_GRAPHICS(0),
    VK_PIPELINE_BIND_POINT_COMPUTE(1),
    VK_PIPELINE_BIND_POINT_RAY_TRACING_NV(1000165000),
    VK_PIPELINE_BIND_POINT_MAX_ENUM(0x7FFFFFFF),
}
