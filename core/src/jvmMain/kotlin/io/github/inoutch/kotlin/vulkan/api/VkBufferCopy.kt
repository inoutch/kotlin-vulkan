package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkBufferCopy.copyToNative(native: org.lwjgl.vulkan.VkBufferCopy) {
    native.srcOffset(srcOffset)
    native.dstOffset(dstOffset)
    native.size(size)
}

fun VkBufferCopy.toNative(scope: MemScope): org.lwjgl.vulkan.VkBufferCopy =
        scope.add(org.lwjgl.vulkan.VkBufferCopy.calloc())
                .also { copyToNative(it) }

fun List<VkBufferCopy>.toNative(scope: MemScope): org.lwjgl.vulkan.VkBufferCopy.Buffer =
        scope.add(org.lwjgl.vulkan.VkBufferCopy.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } })
