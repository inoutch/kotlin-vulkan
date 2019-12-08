package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toCStringArray

@ExperimentalUnsignedTypes
fun VkInstanceCreateInfo.copyToNative(
    native: vulkan_ios.VkInstanceCreateInfo,
    scope: MemScope
) {
    native.sType = vulkan_ios.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.enabledExtensionCount = enabledExtensionNames.size.toUInt()
    native.ppEnabledExtensionNames = enabledExtensionNames.toCStringArray(scope)
    native.enabledLayerCount = enabledLayerNames.size.toUInt()
    native.ppEnabledLayerNames = enabledLayerNames.toCStringArray(scope)
    native.pApplicationInfo = applicationInfo.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkInstanceCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkInstanceCreateInfo>().also { copyToNative(it, scope) }.ptr
