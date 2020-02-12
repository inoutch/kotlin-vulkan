package io.github.inoutch.kotlin.vulkan.api

class VkPipelineTessellationStateCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_TESSELLATION_STATE_CREATE_INFO,
    val flags: Int, // future use
    val patchControlPoints: Int
)
