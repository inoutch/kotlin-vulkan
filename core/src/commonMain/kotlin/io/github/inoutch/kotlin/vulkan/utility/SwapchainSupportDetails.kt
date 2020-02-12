package io.github.inoutch.kotlin.vulkan.utility

import io.github.inoutch.kotlin.vulkan.api.VkColorSpaceKHR
import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.VkFormat
import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDevice
import io.github.inoutch.kotlin.vulkan.api.VkPresentModeKHR
import io.github.inoutch.kotlin.vulkan.api.VkSurface
import io.github.inoutch.kotlin.vulkan.api.VkSurfaceCapabilitiesKHR
import io.github.inoutch.kotlin.vulkan.api.VkSurfaceFormatKHR
import io.github.inoutch.kotlin.vulkan.api.VkSurfaceTransformFlagBitsKHR
import io.github.inoutch.kotlin.vulkan.api.vk
import kotlin.math.max
import kotlin.math.min

class SwapchainSupportDetails private constructor(
    val capabilities: VkSurfaceCapabilitiesKHR,
    val formats: List<VkSurfaceFormatKHR>,
    val presentModes: List<VkPresentModeKHR>
) {
    companion object {
        fun querySwapchainSupport(physicalDevice: VkPhysicalDevice, surface: VkSurface): SwapchainSupportDetails? {
            val capabilities = MutableProperty<VkSurfaceCapabilitiesKHR>()
            val formats = mutableListOf<VkSurfaceFormatKHR>()
            val presentModes = mutableListOf<VkPresentModeKHR>()

            vk.getPhysicalDeviceSurfaceCapabilitiesKHR(physicalDevice, surface, capabilities)
            vk.getPhysicalDeviceSurfaceFormatsKHR(physicalDevice, surface, formats)
            vk.getPhysicalDeviceSurfacePresentModesKHR(physicalDevice, surface, presentModes)

            return SwapchainSupportDetails(capabilities.value ?: return null, formats, presentModes)
        }
    }

    fun chooseSwapSurfaceFormat(): VkSurfaceFormatKHR {
        if (formats.size == 1 && formats.first().format == VkFormat.VK_FORMAT_UNDEFINED) {
            return VkSurfaceFormatKHR(VkFormat.VK_FORMAT_B8G8R8A8_UNORM, VkColorSpaceKHR.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR)
        }

        return formats.find { it.format == VkFormat.VK_FORMAT_B8G8R8A8_UNORM && it.colorSpace == VkColorSpaceKHR.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR }
                ?: formats.first()
    }

    fun chooseSwapPresentMode(): VkPresentModeKHR {
        var swapchainPresentMode = VkPresentModeKHR.VK_PRESENT_MODE_FIFO_KHR
        for (presentMode in presentModes) {
            if (presentMode == VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR) {
                return VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR
            }
            if (presentMode == VkPresentModeKHR.VK_PRESENT_MODE_IMMEDIATE_KHR) {
                swapchainPresentMode = VkPresentModeKHR.VK_PRESENT_MODE_IMMEDIATE_KHR
            }
        }
        return swapchainPresentMode
    }

    fun chooseSwapExtent(actualWindowSize: VkExtent2D): VkExtent2D {
        if (capabilities.currentExtent.width != -1) {
            return capabilities.currentExtent
        }

        return VkExtent2D(
                max(capabilities.minImageExtent.width, min(capabilities.maxImageExtent.width, actualWindowSize.width)),
                max(capabilities.minImageExtent.height, min(capabilities.maxImageExtent.height, actualWindowSize.height)))
    }

    fun chooseImageCount(): Int {
        var imageCount = capabilities.minImageCount + 1
        if (capabilities.maxImageCount in 1 until imageCount) {
            imageCount = capabilities.maxImageCount
        }
        return imageCount
    }

    fun chooseTransform(): VkSurfaceTransformFlagBitsKHR = if (capabilities.supportedTransforms.contains(VkSurfaceTransformFlagBitsKHR.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR)) {
        VkSurfaceTransformFlagBitsKHR.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR
    } else {
        capabilities.currentTransform
    }
}
