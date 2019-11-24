package io.github.inoutch.kotlin.vulkan.api

class VkPipelineColorBlendStateCreateInfo(
    val flags: Int,
    val logicOpEnable: Boolean,
    val logicOp: VkLogicOp,
    val attachments: List<VkPipelineColorBlendAttachmentState>,
    val blendConstants: FloatArray // [4]
)
