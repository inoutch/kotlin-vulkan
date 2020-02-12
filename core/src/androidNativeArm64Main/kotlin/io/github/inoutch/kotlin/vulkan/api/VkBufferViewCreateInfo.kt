package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_BUFFER_VIEW_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkBufferViewCreateInfo.copyToNative(native: vulkan_android.VkBufferViewCreateInfo) {
    native.sType = VK_STRUCTURE_TYPE_BUFFER_VIEW_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.value }.toUInt()
    native.buffer = buffer.native
    native.format = format.value.toUInt()
    native.offset = offset.toULong()
    native.range = range.toULong()
}

@ExperimentalUnsignedTypes
fun VkBufferViewCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkBufferViewCreateInfo>()
                .also { copyToNative(it) }.ptr
