package io.github.inoutch.kotlin.vulkan.api

fun org.lwjgl.vulkan.VkMemoryRequirements.toOrigin() =
        VkMemoryRequirements(size(), alignment(), memoryTypeBits())
