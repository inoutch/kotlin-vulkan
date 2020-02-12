package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkOffset2D.copyToNativeOffset(native: org.lwjgl.vulkan.VkOffset2D) {
    native.set(x, y)
}

fun VkOffset2D.toNativeOffset(scope: MemScope): org.lwjgl.vulkan.VkOffset2D =
        scope.add(org.lwjgl.vulkan.VkOffset2D.calloc().also { copyToNativeOffset(it) })

fun org.lwjgl.vulkan.VkOffset2D.toOrigin() =
        VkOffset2D(x(), y())
