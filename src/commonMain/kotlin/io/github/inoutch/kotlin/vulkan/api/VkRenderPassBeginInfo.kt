package io.github.inoutch.kotlin.vulkan.api

class VkRenderPassBeginInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_RENDER_PASS_BEGIN_INFO,
    val renderPass: VkRenderPass,
    val framebuffer: VkFramebuffer,
    val renderArea: VkRect2D,
    val clearValues: List<VkClearValue>
)
