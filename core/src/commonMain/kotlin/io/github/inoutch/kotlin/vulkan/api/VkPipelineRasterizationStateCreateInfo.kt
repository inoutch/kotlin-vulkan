package io.github.inoutch.kotlin.vulkan.api

class VkPipelineRasterizationStateCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_RASTERIZATION_STATE_CREATE_INFO,
    val flags: Int, // future use
    val polygonMode: VkPolygonMode,
    val cullMode: VkCullModeFlagBits,
    val frontFace: VkFrontFace,
    val depthClampEnable: Boolean,
    val rasterizerDiscardEnable: Boolean,
    val depthBiasEnable: Boolean,
    val depthBiasConstantFactor: Float,
    val depthBiasClamp: Float,
    val depthBiasSlopeFactor: Float,
    val lineWidth: Float
)
