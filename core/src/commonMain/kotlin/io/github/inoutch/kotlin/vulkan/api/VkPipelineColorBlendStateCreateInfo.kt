package io.github.inoutch.kotlin.vulkan.api

class VkPipelineColorBlendStateCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_COLOR_BLEND_STATE_CREATE_INFO,
    val flags: Int,
    val logicOpEnable: Boolean,
    val logicOp: VkLogicOp,
    val attachments: List<VkPipelineColorBlendAttachmentState>,
    val blendConstants: FloatArray // [4]
)
