package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO

@ExperimentalUnsignedTypes
fun VkMemoryAllocateInfo.copyToNative(native: vulkan_ios.VkMemoryAllocateInfo) {
    native.sType = VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO
    native.pNext = null
    native.allocationSize = allocationSize.toULong()
    native.memoryTypeIndex = memoryTypeIndex.toUInt()
}

@ExperimentalUnsignedTypes
fun VkMemoryAllocateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkMemoryAllocateInfo>()
                .also { copyToNative(it) }.ptr
