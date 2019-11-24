package io.github.inoutch.kotlin.vulkan.api

class VkPipelineShaderStageCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO,
    val flags: List<VkPipelineShaderStageCreateFlagBits>,
    val stage: List<VkShaderStageFlagBits>,
    val module: VkShaderModule,
    val name: String,
    val specializationInfo: VkSpecializationInfo?
)
