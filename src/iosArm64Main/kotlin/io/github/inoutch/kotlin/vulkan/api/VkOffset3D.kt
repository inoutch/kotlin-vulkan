package io.github.inoutch.kotlin.vulkan.api

fun VkOffset3D.copyToNative(native: vulkan_ios.VkOffset3D) {
    native.x = x
    native.y = y
    native.z = z
}
