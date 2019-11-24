package io.github.inoutch.kotlin.vulkan.api

class VkPipelineMultisampleStateCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_MULTISAMPLE_STATE_CREATE_INFO,
    val flags: Int, // future use
    val rasterizationSamples: List<VkSampleCountFlagBits>,
    val sampleShadingEnable: Boolean,
    val minSampleShading: Float,
    val sampleMask: Int?,
    val alphaToCoverageEnable: Boolean,
    val alphaToOneEnable: Boolean
)
