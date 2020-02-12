package io.github.inoutch.kotlin.vulkan.api

class VkPipelineViewportStateCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO,
    val flags: Int,
    val viewports: List<VkViewport>,
    val scissors: List<VkRect2D>
)
