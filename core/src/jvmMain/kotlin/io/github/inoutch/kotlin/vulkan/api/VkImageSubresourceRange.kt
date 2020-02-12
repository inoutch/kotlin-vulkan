package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkImageSubresourceRange.copyToNative(native: org.lwjgl.vulkan.VkImageSubresourceRange) {
    native.aspectMask(aspectMask.sumBy { it.bit })
            .baseMipLevel(baseMipLevel)
            .levelCount(levelCount)
            .baseArrayLayer(baseArrayLayer)
            .layerCount(layerCount)
}

fun VkImageSubresourceRange.toNative(scope: MemScope): org.lwjgl.vulkan.VkImageSubresourceRange =
        scope.add(org.lwjgl.vulkan.VkImageSubresourceRange.calloc()
                .also { copyToNative(it) })

fun List<VkImageSubresourceRange>.toNative(scope: MemScope): org.lwjgl.vulkan.VkImageSubresourceRange.Buffer =
        scope.add(org.lwjgl.vulkan.VkImageSubresourceRange.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } })
