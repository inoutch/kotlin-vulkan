package io.github.inoutch.kotlin.vulkan.api

class VkBufferCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO,
    val flags: List<VkBufferCreateFlagBits>,
    val size: VkDeviceSize,
    val usage: List<VkBufferUsageFlagBits>,
    val sharingMode: VkSharingMode,
    val queueFamilyIndices: List<Int>?
)
