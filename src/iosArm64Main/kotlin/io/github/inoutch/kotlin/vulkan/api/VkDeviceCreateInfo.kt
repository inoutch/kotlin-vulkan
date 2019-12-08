package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCStringArray
import vulkan_ios.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkDeviceCreateInfo.copyToNative(
    native: vulkan_ios.VkDeviceCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.queueCreateInfoCount = queueCreateInfos.size.toUInt()
    native.pQueueCreateInfos = queueCreateInfos.toNative(scope)
    native.enabledLayerCount = enabledLayerNames.size.toUInt()
    native.ppEnabledLayerNames = enabledLayerNames.toCStringArray(scope)
    native.enabledExtensionCount = enabledExtensionNames.size.toUInt()
    native.ppEnabledExtensionNames = enabledExtensionNames.toCStringArray(scope)
    native.pEnabledFeatures = enabledFeatures?.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkDeviceCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkDeviceCreateInfo>().also { copyToNative(it, scope) }.ptr
