package io.github.inoutch.kotlin.vulkan.api

class VkBufferViewCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_BUFFER_VIEW_CREATE_INFO,
    val flags: List<VkBufferViewCreateFlagBits>,
    val buffer: VkBuffer,
    val format: VkFormat,
    val offset: VkDeviceSize,
    val range: VkDeviceSize
)
