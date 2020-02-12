package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope
import java.lang.IllegalArgumentException

fun VkClearValue.copyToNative(
    native: org.lwjgl.vulkan.VkClearValue,
    scope: MemScope
) {
    when {
        color != null -> native.color(color.toNative(scope))
        depthStencil != null -> native.depthStencil(depthStencil.toNative(scope))
        else -> throw IllegalArgumentException("color and depthStencil")
    }
}

fun VkClearValue.toNative(scope: MemScope): org.lwjgl.vulkan.VkClearValue =
        scope.add(org.lwjgl.vulkan.VkClearValue.calloc()
                .also { copyToNative(it, scope) })

fun List<VkClearValue>.toNative(scope: MemScope): org.lwjgl.vulkan.VkClearValue.Buffer =
        scope.add(org.lwjgl.vulkan.VkClearValue.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index], scope) } })
