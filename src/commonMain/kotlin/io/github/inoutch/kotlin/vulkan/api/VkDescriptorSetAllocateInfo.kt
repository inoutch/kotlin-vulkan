package io.github.inoutch.kotlin.vulkan.api

class VkDescriptorSetAllocateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_DESCRIPTOR_SET_ALLOCATE_INFO,
    val descriptorPool: VkDescriptorPool,
    val descriptorSetCount: Int,
    val setLayouts: List<VkDescriptorSetLayout>
)
