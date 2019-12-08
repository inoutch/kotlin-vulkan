package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkDeviceQueueCreateInfo.copyToNative(
    native: vulkan_android.VkDeviceQueueCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.queueFamilyIndex = queueFamilyIndex.toUInt()
    native.queueCount = queuePriorities.size.toUInt()
    native.pQueuePriorities = queuePriorities.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkDeviceQueueCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkDeviceQueueCreateInfo>().also { copyToNative(it, scope) }.ptr

@ExperimentalUnsignedTypes
fun List<VkDeviceQueueCreateInfo>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_android.VkDeviceQueueCreateInfo>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index], scope) } }
