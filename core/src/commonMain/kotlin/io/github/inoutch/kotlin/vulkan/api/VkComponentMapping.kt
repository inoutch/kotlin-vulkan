package io.github.inoutch.kotlin.vulkan.api

data class VkComponentMapping(
        val r: VkComponentSwizzle = VkComponentSwizzle.VK_COMPONENT_SWIZZLE_R,
        val g: VkComponentSwizzle = VkComponentSwizzle.VK_COMPONENT_SWIZZLE_G,
        val b: VkComponentSwizzle = VkComponentSwizzle.VK_COMPONENT_SWIZZLE_B,
        val a: VkComponentSwizzle = VkComponentSwizzle.VK_COMPONENT_SWIZZLE_A
)
