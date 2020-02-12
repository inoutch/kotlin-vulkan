package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkExtent2D.copyToNativeExtent(native: org.lwjgl.vulkan.VkExtent2D) {
    native.set(width, height)
}

fun VkExtent2D.toNativeExtent(scope: MemScope): org.lwjgl.vulkan.VkExtent2D =
        scope.add(org.lwjgl.vulkan.VkExtent2D.calloc().also { copyToNativeExtent(it) })

fun org.lwjgl.vulkan.VkExtent2D.toOrigin() =
        VkExtent2D(width(), height())
