package io.github.inoutch.kotlin.vulkan.api

class VkCommandBufferAllocateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO,
    val commandPool: VkCommandPool,
    val level: VkCommandBufferLevel,
    val commandBufferCount: Int
)
