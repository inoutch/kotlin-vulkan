package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkImageSubresourceLayers.copyToNative(native: org.lwjgl.vulkan.VkImageSubresourceLayers) {
    native.aspectMask(aspectMask.sumBy { it.bit })
            .mipLevel(mipLevel)
            .baseArrayLayer(baseArrayLayer)
            .layerCount(layerCount)
}

fun VkImageSubresourceLayers.toNative(scope: MemScope): org.lwjgl.vulkan.VkImageSubresourceLayers =
        scope.add(org.lwjgl.vulkan.VkImageSubresourceLayers.calloc()
                .also { copyToNative(it) })
