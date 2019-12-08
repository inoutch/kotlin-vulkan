package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun VkExtent3D.copyToNative(native: vulkan_ios.VkExtent3D) {
    native.width = width.toUInt()
    native.height = height.toUInt()
    native.depth = depth.toUInt()
}

@ExperimentalUnsignedTypes
fun vulkan_ios.VkExtent3D.toOrigin(): VkExtent3D {
    return VkExtent3D(width.toInt(), height.toInt(), depth.toInt())
}
