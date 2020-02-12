package io.github.inoutch.kotlin.vulkan.api

class VkDescriptorBufferInfo(
    val buffer: VkBuffer,
    val offset: VkDeviceSize,
    val range: VkDeviceSize
)
