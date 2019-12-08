package io.github.inoutch.kotlin.vulkan.api

fun org.lwjgl.vulkan.VkSubresourceLayout.toOrigin(): VkSubresourceLayout {
    return VkSubresourceLayout(offset(), size(), rowPitch(), arrayPitch(), depthPitch())
}
