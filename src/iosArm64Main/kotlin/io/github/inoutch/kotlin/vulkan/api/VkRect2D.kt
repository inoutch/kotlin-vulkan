package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkRect2D.copyToNative(native: vulkan_ios.VkRect2D) {
    native.offset.x = offset.x
    native.offset.y = offset.y
    native.extent.width = extent.width.toUInt()
    native.extent.height = extent.height.toUInt()
}

@ExperimentalUnsignedTypes
fun List<VkRect2D>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_ios.VkRect2D>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
