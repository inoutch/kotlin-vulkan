package io.github.inoutch.kotlin.vulkan.api

class VkDescriptorPoolCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_DESCRIPTOR_POOL_CREATE_INFO,
    val flags: List<VkDescriptorPoolCreateFlagBits>,
    val maxSets: Int,
    val poolSizes: List<VkDescriptorPoolSize>
)
