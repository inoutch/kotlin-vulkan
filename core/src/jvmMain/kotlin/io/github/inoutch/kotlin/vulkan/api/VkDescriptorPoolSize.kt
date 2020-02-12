package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkDescriptorPoolSize.copyToNative(native: org.lwjgl.vulkan.VkDescriptorPoolSize) {
    native.set(type.value, descriptorCount)
}

fun List<VkDescriptorPoolSize>.toNative(scope: MemScope): org.lwjgl.vulkan.VkDescriptorPoolSize.Buffer =
        scope.add(org.lwjgl.vulkan.VkDescriptorPoolSize.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } })
