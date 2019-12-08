package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun FloatArray.copyToNative(native: org.lwjgl.vulkan.VkClearColorValue) {
    native.float32(0, this[0])
    native.float32(1, this[1])
    native.float32(2, this[2])
    native.float32(3, this[3])
}

fun FloatArray.toNative(scope: MemScope): org.lwjgl.vulkan.VkClearColorValue = scope
        .add(org.lwjgl.vulkan.VkClearColorValue.calloc().also { copyToNative(it) })

fun VkClearColorValue.toNative(scope: MemScope): org.lwjgl.vulkan.VkClearColorValue = array.toNative(scope)
