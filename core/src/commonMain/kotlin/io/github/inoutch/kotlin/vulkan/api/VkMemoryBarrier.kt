package io.github.inoutch.kotlin.vulkan.api

class VkMemoryBarrier(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_MEMORY_BARRIER,
    val srcAccessFlags: List<VkAccessFlagBits>,
    val dstAccessFlags: List<VkAccessFlagBits>
)
