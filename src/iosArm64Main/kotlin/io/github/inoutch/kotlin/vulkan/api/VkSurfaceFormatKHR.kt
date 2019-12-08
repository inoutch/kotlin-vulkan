package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_ios.VkSurfaceFormatKHR.toOrigin(): VkSurfaceFormatKHR {
    return VkSurfaceFormatKHR(VkFormat.values().find { it.value.toUInt() == format }
            ?: throw IllegalStateException("format is null"),
            VkColorSpaceKHR.values().find { it.value.toUInt() == colorSpace }
                    ?: throw IllegalStateException("colorSpace is null"))
}
