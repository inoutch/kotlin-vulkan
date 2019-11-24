package io.github.inoutch.kotlin.vulkan.api

class VkCommandBufferInheritanceInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_INHERITANCE_INFO,
    val renderPass: VkRenderPass,
    val subpass: Int, // subpass index
    val framebuffer: VkFramebuffer,
    val occlusionQueryEnable: Boolean,
    val queryFlags: List<VkQueryControlFlagBits>,
    val pipelineStatistics: List<VkQueryPipelineStatisticFlagBits>
)
