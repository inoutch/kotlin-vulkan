package io.github.inoutch.kotlin.vulkan.api

fun org.lwjgl.vulkan.VkQueueFamilyProperties.toOrigin(): VkQueueFamilyProperties {
    return VkQueueFamilyProperties(queueFlags(), queueCount(), timestampValidBits(),
            minImageTransferGranularity().let { VkExtent3D(it.width(), it.height(), it.depth()) })
}
