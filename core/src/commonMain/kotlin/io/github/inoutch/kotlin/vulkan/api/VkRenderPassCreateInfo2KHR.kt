package io.github.inoutch.kotlin.vulkan.api

class VkRenderPassCreateInfo2KHR(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_RENDER_PASS_CREATE_INFO,
    val flags: Int, // future use
    val attachments: List<VkAttachmentDescription>,
    val subpasses: List<VkSubpassDescription>,
    val dependencies: List<VkSubpassDependency>,
    val correlatedViewMasks: List<Int>
)
