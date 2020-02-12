package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_SWAPCHAIN_CREATE_INFO_KHR

@ExperimentalUnsignedTypes
fun VkSwapchainCreateInfoKHR.copyToNative(
    native: vulkan_android.VkSwapchainCreateInfoKHR,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_SWAPCHAIN_CREATE_INFO_KHR
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.surface = surface.native
    native.minImageCount = minImageCount.toUInt()
    native.imageFormat = imageFormat.value.toUInt()
    native.imageColorSpace = imageColorSpace.value.toUInt()
    imageExtent.copyToNative(native.imageExtent)
    native.imageArrayLayers = imageArrayLayers.toUInt()
    native.imageUsage = imageUsage.sumBy { it.bit }.toUInt()
    native.imageSharingMode = imageSharingMode.value.toUInt()
    native.queueFamilyIndexCount = queueFamilyIndices?.size?.toUInt() ?: 0u
    native.pQueueFamilyIndices = queueFamilyIndices?.map { it.toUInt() }?.toNative(scope)
    native.preTransform = preTransform.bit.toUInt()
    native.compositeAlpha = compositeAlpha.bit.toUInt()
    native.presentMode = presentMode.value.toUInt()
    native.clipped = clipped.toVkBool32()
    native.oldSwapchain = oldSwapchain?.native
}

@ExperimentalUnsignedTypes
fun VkSwapchainCreateInfoKHR.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkSwapchainCreateInfoKHR>()
                .also { copyToNative(it, scope) }.ptr
