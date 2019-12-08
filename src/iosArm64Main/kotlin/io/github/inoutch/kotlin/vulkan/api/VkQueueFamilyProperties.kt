package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun VkQueueFamilyProperties.copyToNative(native: vulkan_ios.VkQueueFamilyProperties) {
    native.queueFlags = queueFlags.toUInt()
    native.queueCount = queueCount.toUInt()
    native.timestampValidBits = timestampValidBits.toUInt()
    minImageTransferGranularity.copyToNative(native.minImageTransferGranularity)
}

@ExperimentalUnsignedTypes
fun vulkan_ios.VkQueueFamilyProperties.toOrigin(): VkQueueFamilyProperties {
    return VkQueueFamilyProperties(
            queueFlags.toInt(),
            queueCount.toInt(),
            timestampValidBits.toInt(),
            minImageTransferGranularity.toOrigin())
}
