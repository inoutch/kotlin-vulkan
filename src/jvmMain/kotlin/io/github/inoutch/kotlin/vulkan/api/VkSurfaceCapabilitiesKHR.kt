package io.github.inoutch.kotlin.vulkan.api

fun org.lwjgl.vulkan.VkSurfaceCapabilitiesKHR.toOrigin() =
        VkSurfaceCapabilitiesKHR(
                minImageCount(),
                maxImageCount(),
                currentExtent().toOrigin(),
                minImageExtent().toOrigin(),
                maxImageExtent().toOrigin(),
                maxImageArrayLayers(),
                VkSurfaceTransformFlagBitsKHR.values().filter { it.bit and supportedTransforms() != 0 },
                VkSurfaceTransformFlagBitsKHR.values().find { it.bit == currentTransform() }
                        ?: throw IllegalStateException("surfaceCapabilities.currentTransform"),
                VkCompositeAlphaFlagBitsKHR.values().filter { it.bit and supportedCompositeAlpha() != 0 },
                VkImageUsageFlagBits.values().filter { it.bit and supportedUsageFlags() != 0 })
