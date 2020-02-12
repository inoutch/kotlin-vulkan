package io.github.inoutch.kotlin.vulkan.api

class VkPhysicalDeviceProperties(
    val apiVersion: Int,
    val driverVersion: Int,
    val vendorID: Int,
    val deviceID: Int,
    val deviceType: VkPhysicalDeviceType,
    val deviceName: String,
    val pipelineCacheUUID: List<Int>,
    val limits: VkPhysicalDeviceLimits,
    val sparseProperties: VkPhysicalDeviceSparseProperties
)
