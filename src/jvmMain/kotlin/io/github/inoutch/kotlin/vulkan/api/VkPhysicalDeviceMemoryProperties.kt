package io.github.inoutch.kotlin.vulkan.api

fun org.lwjgl.vulkan.VkPhysicalDeviceMemoryProperties.toOrigin(): VkPhysicalDeviceMemoryProperties {
    return VkPhysicalDeviceMemoryProperties(
            memoryTypes().map { memoryType -> VkMemoryType(VkMemoryPropertyFlagBits.values().filter { it.bit and memoryType.propertyFlags() == it.bit }, memoryType.heapIndex()) },
            memoryHeaps().map { VkMemoryHeap(it.size(), VkMemoryHeapFlagBits.values().filter { flag -> flag.bit and it.flags() == flag.bit }) })
}
