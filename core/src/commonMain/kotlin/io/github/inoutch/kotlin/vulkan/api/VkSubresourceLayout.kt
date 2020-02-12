package io.github.inoutch.kotlin.vulkan.api

class VkSubresourceLayout(
    val offset: Long,
    val size: Long,
    val rowPitch: Long,
    val arrayPitch: Long,
    val depthPitch: Long
)
