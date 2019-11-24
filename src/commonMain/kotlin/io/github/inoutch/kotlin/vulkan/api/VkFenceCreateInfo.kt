package io.github.inoutch.kotlin.vulkan.api

class VkFenceCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_FENCE_CREATE_INFO,
    val flags: List<VkFenceCreateFlagBits>
)
