package io.github.inoutch.kotlin.vulkan.api

class VkBufferImageCopy(
    val bufferOffset: VkDeviceSize,
    val bufferRowLength: Int,
    val bufferImageHeight: Int,
    val imageSubresource: VkImageSubresourceLayers,
    val imageOffset: VkOffset3D,
    val imageExtent: VkExtent3D
)
