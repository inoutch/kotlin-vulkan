package io.github.inoutch.kotlin.vulkan.api

class VkCopyDescriptorSet(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_COPY_DESCRIPTOR_SET,
    val srcSet: VkDescriptorSet,
    val srcBinding: Int,
    val srcArrayElement: Int,
    val dstSet: VkDescriptorSet,
    val dstBinding: Int,
    val dstArrayElement: Int,
    val descriptorCount: Int
)
