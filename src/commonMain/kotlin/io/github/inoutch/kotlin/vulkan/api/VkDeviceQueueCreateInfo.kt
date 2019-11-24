package io.github.inoutch.kotlin.vulkan.api

class VkDeviceQueueCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO,
    val flags: List<VkDeviceQueueCreateFlagBits>,
    val queueFamilyIndex: Int,
    val queuePriorities: List<Float> = listOf(1.0f)
)
