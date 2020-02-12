package io.github.inoutch.kotlin.vulkan.api

class VkPipelineDepthStencilStateCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_DEPTH_STENCIL_STATE_CREATE_INFO,
    val flags: Int, // future use
    val depthTestEnable: Boolean,
    val depthWriteEnable: Boolean,
    val depthCompareOp: VkCompareOp,
    val depthBoundsTestEnable: Boolean,
    val stencilTestEnable: Boolean,
    val front: VkStencilOpState,
    val back: VkStencilOpState,
    val minDepthBounds: Float,
    val maxDepthBounds: Float
)
