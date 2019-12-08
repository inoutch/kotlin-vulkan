package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import vulkan_android.VK_STRUCTURE_TYPE_BUFFER_MEMORY_BARRIER

@ExperimentalUnsignedTypes
fun VkBufferMemoryBarrier.copyToNative(native: vulkan_android.VkBufferMemoryBarrier) {
    native.sType = VK_STRUCTURE_TYPE_BUFFER_MEMORY_BARRIER
    native.pNext = null
    native.srcAccessMask = srcAccessMask.sumBy { it.bit }.toUInt()
    native.dstAccessMask = dstAccessMask.sumBy { it.bit }.toUInt()
    native.srcQueueFamilyIndex = srcQueueFamilyIndex.toUInt()
    native.dstQueueFamilyIndex = dstQueueFamilyIndex.toUInt()
    native.buffer = buffer.native
    native.offset = offset.toULong()
    native.size = size.toULong()
}

@ExperimentalUnsignedTypes
fun List<VkBufferMemoryBarrier>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_android.VkBufferMemoryBarrier>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
