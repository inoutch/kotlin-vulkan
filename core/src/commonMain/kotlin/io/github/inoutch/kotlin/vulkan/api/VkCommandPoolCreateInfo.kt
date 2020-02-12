package io.github.inoutch.kotlin.vulkan.api

class VkCommandPoolCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO,
    val flags: List<VkCommandPoolCreateFlagBits>,
    val queueFamilyIndex: Int
)
