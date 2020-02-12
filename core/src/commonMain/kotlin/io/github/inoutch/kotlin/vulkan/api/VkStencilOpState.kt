package io.github.inoutch.kotlin.vulkan.api

class VkStencilOpState(
    val failOp: VkStencilOp,
    val passOp: VkStencilOp,
    val depthFailOp: VkStencilOp,
    val compareOp: VkCompareOp,
    val compareMask: Int,
    val writeMask: Int,
    val reference: Int
)
