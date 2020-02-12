package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import vulkan_ios.VK_STRUCTURE_TYPE_MEMORY_BARRIER

@ExperimentalUnsignedTypes
fun VkMemoryBarrier.copyToNative(native: vulkan_ios.VkMemoryBarrier) {
    native.sType = VK_STRUCTURE_TYPE_MEMORY_BARRIER
    native.pNext = null
    native.srcAccessMask = srcAccessFlags.sumBy { it.bit }.toUInt()
    native.dstAccessMask = dstAccessFlags.sumBy { it.bit }.toUInt()
}

@ExperimentalUnsignedTypes
fun VkMemoryBarrier.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkMemoryBarrier>().also { copyToNative(it) }

@ExperimentalUnsignedTypes
fun List<VkMemoryBarrier>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_ios.VkMemoryBarrier>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
