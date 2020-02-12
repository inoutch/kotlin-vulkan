package io.github.inoutch.kotlin.vulkan.api

class VkImageMemoryBarrier(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER,
    val srcAccessMask: List<VkAccessFlagBits>,
    val dstAccessMask: List<VkAccessFlagBits>,
    val oldLayout: VkImageLayout,
    val newLayout: VkImageLayout,
    val srcQueueFamilyIndex: Int,
    val dstQueueFamilyIndex: Int,
    val image: VkImage,
    val subresourceRange: VkImageSubresourceRange
)
