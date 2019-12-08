package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkImageSubresource.copyToNative(native: org.lwjgl.vulkan.VkImageSubresource) {
    native.set(aspectMask.sumBy { it.bit }, mipLevel, arrayLayer)
}

fun VkImageSubresource.toNative(scope: MemScope): org.lwjgl.vulkan.VkImageSubresource =
        scope.add(org.lwjgl.vulkan.VkImageSubresource.calloc()
                .also { copyToNative(it) })
