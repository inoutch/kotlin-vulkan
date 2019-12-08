package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkBufferCreateInfo.copyToNative(
    native: vulkan_ios.VkBufferCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.size = size.toULong()
    native.usage = usage.sumBy { it.bit }.toUInt()
    native.sharingMode = sharingMode.value.toUInt()
    native.queueFamilyIndexCount = queueFamilyIndices?.size?.toUInt() ?: 0u
    native.pQueueFamilyIndices = queueFamilyIndices?.map { it.toUInt() }?.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkBufferCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkBufferCreateInfo>().also { copyToNative(it, scope) }.ptr
