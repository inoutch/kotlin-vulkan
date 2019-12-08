package io.github.inoutch.kotlin.vulkan.api

fun VkOffset3D.copyToNative(native: vulkan_android.VkOffset3D) {
    native.x = x
    native.y = y
    native.z = z
}
