package io.github.inoutch.kotlin.vulkan.api

class VkGraphicsPipelineCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_GRAPHICS_PIPELINE_CREATE_INFO,
    val flags: List<VkPipelineCreateFlagBits>,
    val stages: List<VkPipelineShaderStageCreateInfo>,
    val vertexInputState: VkPipelineVertexInputStateCreateInfo,
    val inputAssemblyState: VkPipelineInputAssemblyStateCreateInfo,
    val tessellationState: VkPipelineTessellationStateCreateInfo?,
    val viewportState: VkPipelineViewportStateCreateInfo,
    val rasterizationState: VkPipelineRasterizationStateCreateInfo,
    val multisampleState: VkPipelineMultisampleStateCreateInfo,
    val depthStencilState: VkPipelineDepthStencilStateCreateInfo,
    val colorBlendState: VkPipelineColorBlendStateCreateInfo,
    val dynamicState: VkPipelineDynamicStateCreateInfo?,
    val layout: VkPipelineLayout,
    val renderPass: VkRenderPass,
    val subpass: Int, // the index of the subpass in the render pass where this pipeline will be used
    val basePipelineHandle: VkPipeline?,
    val basePipelineIndex: Int
)
