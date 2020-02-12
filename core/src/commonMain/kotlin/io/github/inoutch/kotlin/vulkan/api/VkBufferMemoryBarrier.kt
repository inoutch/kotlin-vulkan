package io.github.inoutch.kotlin.vulkan.api

class VkBufferMemoryBarrier(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_BUFFER_MEMORY_BARRIER,
    val srcAccessMask: List<VkAccessFlagBits>,
    val dstAccessMask: List<VkAccessFlagBits>,
    val srcQueueFamilyIndex: Int,
    val dstQueueFamilyIndex: Int,
    val buffer: VkBuffer,
    val offset: VkDeviceSize,
    val size: VkDeviceSize
)
