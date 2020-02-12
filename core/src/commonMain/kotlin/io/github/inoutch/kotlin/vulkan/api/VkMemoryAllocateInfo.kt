package io.github.inoutch.kotlin.vulkan.api

class VkMemoryAllocateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO,
    val allocationSize: VkDeviceSize,
    val memoryTypeIndex: Int
)
