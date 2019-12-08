package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_ios.VkMemoryRequirements.toOrigin(): VkMemoryRequirements {
    return VkMemoryRequirements(size.toLong(), alignment.toLong(), memoryTypeBits.toInt())
}
