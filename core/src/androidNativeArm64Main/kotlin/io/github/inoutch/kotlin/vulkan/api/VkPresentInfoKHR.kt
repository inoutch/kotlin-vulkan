package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_PRESENT_INFO_KHR

@ExperimentalUnsignedTypes
fun VkPresentInfoKHR.copyToNative(
    native: vulkan_android.VkPresentInfoKHR,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_PRESENT_INFO_KHR
    native.pNext = null
    native.waitSemaphoreCount = waitSemaphores.size.toUInt()
    native.pWaitSemaphores = waitSemaphores.map { it.native }.toNative(scope)
    native.swapchainCount = swapchains.size.toUInt()
    native.pSwapchains = swapchains.map { it.native }.toNative(scope)
    native.pImageIndices = imageIndices.map { it.toUInt() }.toNative(scope)
    native.pResults = results?.map { it.value }?.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkPresentInfoKHR.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPresentInfoKHR>()
                .also { copyToNative(it, scope) }.ptr
