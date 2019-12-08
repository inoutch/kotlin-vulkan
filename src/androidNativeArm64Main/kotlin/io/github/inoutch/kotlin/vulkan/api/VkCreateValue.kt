package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.set

@ExperimentalUnsignedTypes
fun VkClearValue.copyToNative(native: vulkan_android.VkClearValue) {
    when {
        color != null -> {
            native.color.float32[0] = color.array[0]
            native.color.float32[1] = color.array[1]
            native.color.float32[2] = color.array[2]
            native.color.float32[3] = color.array[3]
        }
        depthStencil != null -> {
            native.depthStencil.depth = depthStencil.depth
            native.depthStencil.stencil = depthStencil.stencil.toUInt()
        }
        else -> throw IllegalStateException("color and depthStencil")
    }
}

@ExperimentalUnsignedTypes
fun List<VkClearValue>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_android.VkClearValue>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
