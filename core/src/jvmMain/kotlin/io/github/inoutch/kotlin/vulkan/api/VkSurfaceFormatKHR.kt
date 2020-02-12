package io.github.inoutch.kotlin.vulkan.api

fun org.lwjgl.vulkan.VkSurfaceFormatKHR.toOrigin() =
        VkSurfaceFormatKHR(
                VkFormat.values().find { it.value == format() } ?: throw IllegalStateException("surface.format"),
                VkColorSpaceKHR.values().find { it.value == colorSpace() }
                        ?: throw IllegalStateException("surface.colorSpace"))
