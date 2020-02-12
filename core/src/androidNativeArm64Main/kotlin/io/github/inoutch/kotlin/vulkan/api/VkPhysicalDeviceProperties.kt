package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.get
import kotlinx.cinterop.toKString

@ExperimentalUnsignedTypes
fun vulkan_android.VkPhysicalDeviceProperties.toOrigin(): VkPhysicalDeviceProperties {
    return VkPhysicalDeviceProperties(
            apiVersion.toInt(),
            driverVersion.toInt(),
            vendorID.toInt(),
            deviceID.toInt(),
            VkPhysicalDeviceType.values().find { it.value.toUInt() == deviceType }
                    ?: throw IllegalStateException("deviceType is null"),
            deviceName.toKString(),
            List(16) { pipelineCacheUUID[it].toInt() },
            limits.toOrigin(),
            sparseProperties.toOrigin())
}
