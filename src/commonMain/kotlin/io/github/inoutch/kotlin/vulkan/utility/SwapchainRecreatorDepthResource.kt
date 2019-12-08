package io.github.inoutch.kotlin.vulkan.utility

import io.github.inoutch.kotlin.vulkan.api.VkDeviceMemory
import io.github.inoutch.kotlin.vulkan.api.VkImage
import io.github.inoutch.kotlin.vulkan.api.VkImageView

class SwapchainRecreatorDepthResource(
    val depthImage: VkImage,
    val depthImageView: VkImageView,
    val imageMemory: VkDeviceMemory
)
