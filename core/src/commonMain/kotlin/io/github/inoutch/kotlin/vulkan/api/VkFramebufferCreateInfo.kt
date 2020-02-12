package io.github.inoutch.kotlin.vulkan.api

class VkFramebufferCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO,
    val flags: List<VkFramebufferCreateFlagBits>,
    val renderPass: VkRenderPass,
    val attachments: List<VkImageView>,
    val width: Int,
    val height: Int,
    val layers: Int
)
