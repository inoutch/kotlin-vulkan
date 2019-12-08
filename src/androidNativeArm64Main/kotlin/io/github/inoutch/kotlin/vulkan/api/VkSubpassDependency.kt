package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkSubpassDependency.copyToNative(native: vulkan_android.VkSubpassDependency) {
    native.srcSubpass = srcSubpassIndex.toUInt()
    native.dstSubpass = dstSubpassIndex.toUInt()
    native.srcStageMask = srcStageMask.sumBy { it.bit }.toUInt()
    native.dstStageMask = dstStageMask.sumBy { it.bit }.toUInt()
    native.srcAccessMask = srcAccessMask.sumBy { it.bit }.toUInt()
    native.dstAccessMask = dstAccessMask.sumBy { it.bit }.toUInt()
    native.dependencyFlags = dependencyFlags.sumBy { it.bit }.toUInt()
}

@ExperimentalUnsignedTypes
fun VkSubpassDependency.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkSubpassDependency>().also { copyToNative(it) }

@ExperimentalUnsignedTypes
fun List<VkSubpassDependency>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_android.VkSubpassDependency>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
