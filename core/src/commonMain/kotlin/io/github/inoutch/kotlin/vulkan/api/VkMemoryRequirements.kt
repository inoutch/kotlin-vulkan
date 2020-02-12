package io.github.inoutch.kotlin.vulkan.api

class VkMemoryRequirements(
    val size: VkDeviceSize,
    val alignment: VkDeviceSize,
    val memoryTypeBits: Int
)
