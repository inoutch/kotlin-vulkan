package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkClearDepthStencilValue.copyToNative(native: org.lwjgl.vulkan.VkClearDepthStencilValue) {
    native.depth(depth)
            .stencil(stencil)
}

fun VkClearDepthStencilValue.toNative(scope: MemScope): org.lwjgl.vulkan.VkClearDepthStencilValue =
        scope.add(org.lwjgl.vulkan.VkClearDepthStencilValue.calloc()
                .also { copyToNative(it) })
