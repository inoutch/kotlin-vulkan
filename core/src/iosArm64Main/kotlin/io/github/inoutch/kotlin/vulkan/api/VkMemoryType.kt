package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_ios.VkMemoryType.toOrigin(): VkMemoryType {
    return VkMemoryType(VkMemoryPropertyFlagBits.values().filter { it.bit and propertyFlags.toInt() == it.bit }, heapIndex.toInt())
}
