package io.github.inoutch.kotlin.vulkan.api

class VkSubpassDescription(
    val flags: List<VkSubpassDescriptionFlagBits>,
    val pipelineBindPoint: VkPipelineBindPoint,
    val inputAttachments: List<VkAttachmentReference>,
    val colorAttachments: List<VkAttachmentReference>,
    val resolveAttachments: List<VkAttachmentReference>,
    val depthStencilAttachment: VkAttachmentReference?,
    val preserveAttachments: List<Int>
)
