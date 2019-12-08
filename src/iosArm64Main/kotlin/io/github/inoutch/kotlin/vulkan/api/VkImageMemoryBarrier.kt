package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import vulkan_ios.VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER

@ExperimentalUnsignedTypes
fun VkImageMemoryBarrier.copyToNative(native: vulkan_ios.VkImageMemoryBarrier) {
    native.sType = VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER
    native.pNext = null
    native.srcAccessMask = srcAccessMask.sumBy { it.bit }.toUInt()
    native.dstAccessMask = dstAccessMask.sumBy { it.bit }.toUInt()
    native.oldLayout = oldLayout.value.toUInt()
    native.newLayout = newLayout.value.toUInt()
    native.srcQueueFamilyIndex = srcQueueFamilyIndex.toUInt()
    native.dstQueueFamilyIndex = dstQueueFamilyIndex.toUInt()
    native.image = image.native
    subresourceRange.copyToNative(native.subresourceRange)
}

@ExperimentalUnsignedTypes
fun List<VkImageMemoryBarrier>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_ios.VkImageMemoryBarrier>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
