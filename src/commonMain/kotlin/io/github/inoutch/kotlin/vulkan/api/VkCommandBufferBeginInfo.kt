package io.github.inoutch.kotlin.vulkan.api

class VkCommandBufferBeginInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO,
    val flags: List<VkCommandBufferUsageFlagBits>,
    val inheritanceInfo: VkCommandBufferInheritanceInfo?
)
