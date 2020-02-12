package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_ios.VkExtent2D.toOrigin(): VkExtent2D {
    return VkExtent2D(width.toInt(), height.toInt())
}

@ExperimentalUnsignedTypes
fun VkExtent2D.copyToNative(native: vulkan_ios.VkExtent2D) {
    native.width = width.toUInt()
    native.height = height.toUInt()
}
