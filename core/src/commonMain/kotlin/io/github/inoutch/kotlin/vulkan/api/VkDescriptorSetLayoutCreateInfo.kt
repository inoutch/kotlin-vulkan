package io.github.inoutch.kotlin.vulkan.api

class VkDescriptorSetLayoutCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_DESCRIPTOR_SET_LAYOUT_CREATE_INFO,
    val flags: List<VkDescriptorSetLayoutCreateFlagBits>,
    val bindings: List<VkDescriptorSetLayoutBinding>
)
