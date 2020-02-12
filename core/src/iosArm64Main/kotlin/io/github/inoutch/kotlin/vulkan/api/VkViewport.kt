package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

fun VkViewport.copyToNative(native: vulkan_ios.VkViewport) {
    native.x = x
    native.y = y
    native.width = width
    native.height = height
    native.maxDepth = maxDepth
    native.minDepth = minDepth
}

fun List<VkViewport>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_ios.VkViewport>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
