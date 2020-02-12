package io.github.inoutch.kotlin.vulkan.api

class VkPipelineDynamicStateCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_DYNAMIC_STATE_CREATE_INFO,
    val flags: Int,
    val dynamicStates: List<VkDynamicState>
)
