package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import kotlinx.cinterop.ptr

@ExperimentalUnsignedTypes
fun VkApplicationInfo.copyToNative(native: vulkan_android.VkApplicationInfo, scope: MemScope) {
    native.sType = vulkan_android.VK_STRUCTURE_TYPE_APPLICATION_INFO
    native.pNext = null
    native.pApplicationName = applicationName.cstr.getPointer(scope)
    native.applicationVersion = applicationVersion.toNative()
    native.pEngineName = engineName.cstr.getPointer(scope)
    native.engineVersion = engineVersion.toNative()
    native.apiVersion = applicationVersion.toNative()
}

@ExperimentalUnsignedTypes
fun VkApplicationInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkApplicationInfo>().also { copyToNative(it, scope) }.ptr
