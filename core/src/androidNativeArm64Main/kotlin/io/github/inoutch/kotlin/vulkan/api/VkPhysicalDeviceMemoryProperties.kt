package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun vulkan_android.VkPhysicalDeviceMemoryProperties.toOrigin(): VkPhysicalDeviceMemoryProperties {
    return VkPhysicalDeviceMemoryProperties(
            List(memoryTypeCount.toInt()) { memoryTypes[it].toOrigin() },
            List(memoryHeapCount.toInt()) { memoryHeaps[it].toOrigin() })
}
