package io.github.inoutch.kotlin.vulkan.api

enum class VkPipelineShaderStageCreateFlagBits(val bit: Int) {
    VK_PIPELINE_SHADER_STAGE_CREATE_ALLOW_VARYING_SUBGROUP_SIZE_BIT_EXT(0x00000001),
    VK_PIPELINE_SHADER_STAGE_CREATE_REQUIRE_FULL_SUBGROUPS_BIT_EXT(0x00000002),
    VK_PIPELINE_SHADER_STAGE_CREATE_FLAG_BITS_MAX_ENUM(0x7FFFFFFF),
}
