package io.github.inoutch.kotlin.vulkan.api

class VkQueueFamilyProperties(
    val queueFlags: Int,
    val queueCount: Int,
    val timestampValidBits: Int,
    val minImageTransferGranularity: VkExtent3D
)
