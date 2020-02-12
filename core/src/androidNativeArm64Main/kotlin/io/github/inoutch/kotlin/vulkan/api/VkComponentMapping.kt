package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun VkComponentMapping.copyToNative(native: vulkan_android.VkComponentMapping) {
    native.r = r.value.toUInt()
    native.g = g.value.toUInt()
    native.b = b.value.toUInt()
    native.a = a.value.toUInt()
}
