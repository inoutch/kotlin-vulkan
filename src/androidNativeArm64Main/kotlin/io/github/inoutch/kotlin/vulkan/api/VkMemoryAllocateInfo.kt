package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO

@ExperimentalUnsignedTypes
fun VkMemoryAllocateInfo.copyToNative(native: vulkan_android.VkMemoryAllocateInfo) {
    native.sType = VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO
    native.pNext = null
    native.allocationSize = allocationSize.toULong()
    native.memoryTypeIndex = memoryTypeIndex.toUInt()
}

@ExperimentalUnsignedTypes
fun VkMemoryAllocateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkMemoryAllocateInfo>()
                .also { copyToNative(it) }.ptr
