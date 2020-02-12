package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkDescriptorBufferInfo.copyToNative(native: org.lwjgl.vulkan.VkDescriptorBufferInfo) {
    native.buffer(buffer.native)
            .offset(offset)
            .range(range)
}

fun List<VkDescriptorBufferInfo>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.add(org.lwjgl.vulkan.VkDescriptorBufferInfo.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } })
