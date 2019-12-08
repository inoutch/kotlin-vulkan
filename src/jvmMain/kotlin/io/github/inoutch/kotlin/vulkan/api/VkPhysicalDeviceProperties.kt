package io.github.inoutch.kotlin.vulkan.api

import java.lang.IllegalStateException

fun org.lwjgl.vulkan.VkPhysicalDeviceProperties.toOrigin(): VkPhysicalDeviceProperties {
    return VkPhysicalDeviceProperties(
            apiVersion(),
            driverVersion(),
            vendorID(),
            deviceID(),
            VkPhysicalDeviceType.values().find { it.value == deviceType() }
                    ?: throw IllegalStateException("deviceType"),
            deviceName().toString(),
            pipelineCacheUUID().asIntBuffer().array().asList(),
            limits().toOrigin(),
            VkPhysicalDeviceSparseProperties(
                    sparseProperties().residencyStandard2DBlockShape(),
                    sparseProperties().residencyStandard2DMultisampleBlockShape(),
                    sparseProperties().residencyStandard3DBlockShape(),
                    sparseProperties().residencyAlignedMipSize(),
                    sparseProperties().residencyNonResidentStrict()))
}
