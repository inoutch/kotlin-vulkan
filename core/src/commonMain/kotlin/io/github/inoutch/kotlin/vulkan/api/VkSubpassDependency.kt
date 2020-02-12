package io.github.inoutch.kotlin.vulkan.api

class VkSubpassDependency(
    val srcSubpassIndex: Int,
    val dstSubpassIndex: Int,
    val srcStageMask: List<VkPipelineStageFlagBits>,
    val dstStageMask: List<VkPipelineStageFlagBits>,
    val srcAccessMask: List<VkAccessFlagBits>,
    val dstAccessMask: List<VkAccessFlagBits>,
    val dependencyFlags: List<VkDependencyFlagBits>
)
