package io.github.inoutch.kotlin.vulkan.api

class VkDescriptorSetLayoutBinding(
    val binding: Int,
    val descriptorType: VkDescriptorType,
    val descriptorCount: Int,
    val stageFlags: List<VkShaderStageFlagBits>,
    val immutableSamplers: VkSampler?
)
