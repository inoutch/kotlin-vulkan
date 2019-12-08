package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_ios.VkMemoryHeap.toOrigin(): VkMemoryHeap {
    return VkMemoryHeap(size.toLong(), VkMemoryHeapFlagBits.values().filter { it.bit and flags.toInt() == it.bit })
}
