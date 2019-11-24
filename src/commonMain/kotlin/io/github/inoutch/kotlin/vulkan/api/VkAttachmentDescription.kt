package io.github.inoutch.kotlin.vulkan.api

class VkAttachmentDescription(
    val flags: List<VkAttachmentDescriptionFlagBits>,
    val format: VkFormat,
    val samples: List<VkSampleCountFlagBits>,
    val loadOp: VkAttachmentLoadOp,
    val storeOp: VkAttachmentStoreOp,
    val stencilLoadOp: VkAttachmentLoadOp,
    val stencilStoreOp: VkAttachmentStoreOp,
    val initialLayout: VkImageLayout,
    val finalLayout: VkImageLayout
)
