package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkRect2D.copyToNative(native: org.lwjgl.vulkan.VkRect2D, scope: MemScope) {
    native.offset(offset.toNativeOffset(scope))
            .extent(extent.toNativeExtent(scope))
}

fun VkRect2D.toNative(scope: MemScope): org.lwjgl.vulkan.VkRect2D =
        scope.add(org.lwjgl.vulkan.VkRect2D.calloc()
                .also { copyToNative(it, scope) })

fun List<VkRect2D>.toNative(scope: MemScope): org.lwjgl.vulkan.VkRect2D.Buffer? =
        if (isEmpty()) null else scope.add(org.lwjgl.vulkan.VkRect2D.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index], scope) } })
