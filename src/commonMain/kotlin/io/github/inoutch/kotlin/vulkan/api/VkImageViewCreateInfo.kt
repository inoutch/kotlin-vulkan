package io.github.inoutch.kotlin.vulkan.api

class VkImageViewCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO,
    val flags: List<VkImageViewCreateFlagBits>,
    val image: VkImage,
    val viewType: VkImageViewType,
    val format: VkFormat,
    val components: VkComponentMapping,
    val subresourceRange: VkImageSubresourceRange
)
