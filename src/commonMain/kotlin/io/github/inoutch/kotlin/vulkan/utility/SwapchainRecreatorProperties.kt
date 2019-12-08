package io.github.inoutch.kotlin.vulkan.utility

import io.github.inoutch.kotlin.vulkan.api.VkCommandBuffer
import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.VkFramebuffer
import io.github.inoutch.kotlin.vulkan.api.VkImage
import io.github.inoutch.kotlin.vulkan.api.VkImageView
import io.github.inoutch.kotlin.vulkan.api.VkRenderPass
import io.github.inoutch.kotlin.vulkan.api.VkSwapchainKHR

interface SwapchainRecreatorProperties {
    val extent: VkExtent2D

    val swapchain: VkSwapchainKHR

    val swapchainImages: List<VkImage>

    val swapchainImageViews: List<VkImageView>

    val renderPass: VkRenderPass

    val depthResources: List<SwapchainRecreatorDepthResource>

    val framebuffers: List<VkFramebuffer>

    val commandBuffers: List<VkCommandBuffer>
}
