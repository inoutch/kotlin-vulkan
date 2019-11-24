package io.github.inoutch.kotlin.vulkan.api

class VkPipelineLayoutCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_LAYOUT_CREATE_INFO,
    val flags: Int, // future use
    val setLayouts: List<VkDescriptorSetLayout>,
    val pushConstantRanges: List<VkPushConstantRange>
)
