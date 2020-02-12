package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkViewport.copyToNative(native: org.lwjgl.vulkan.VkViewport) {
    native.x(x)
            .y(y)
            .width(width)
            .height(height)
            .maxDepth(maxDepth)
            .minDepth(minDepth)
}

fun List<VkViewport>.toNative(scope: MemScope): org.lwjgl.vulkan.VkViewport.Buffer? =
        if (isEmpty()) null else scope.add(org.lwjgl.vulkan.VkViewport.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } })
