package io.github.inoutch.kotlin.vulkan.api

class VkPhysicalDeviceMemoryProperties(
    val memoryTypes: List<VkMemoryType>,
    val memoryHeaps: List<VkMemoryHeap>
)
