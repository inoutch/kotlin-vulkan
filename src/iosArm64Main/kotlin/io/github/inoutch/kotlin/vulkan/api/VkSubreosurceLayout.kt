package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_ios.VkSubresourceLayout.toOrigin(): VkSubresourceLayout {
    return VkSubresourceLayout(
            offset.toLong(),
            size.toLong(),
            rowPitch.toLong(),
            arrayPitch.toLong(),
            depthPitch.toLong())
}
