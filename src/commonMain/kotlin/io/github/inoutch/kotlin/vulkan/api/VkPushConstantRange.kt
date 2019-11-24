package io.github.inoutch.kotlin.vulkan.api

class VkPushConstantRange(
    val stageFlags: List<VkShaderStageFlagBits>,
    val offset: Int,
    val size: Int
)
