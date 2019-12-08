package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_SUBMIT_INFO

@ExperimentalUnsignedTypes
fun VkSubmitInfo.copyToNative(
    native: vulkan_ios.VkSubmitInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_SUBMIT_INFO
    native.waitSemaphoreCount = waitSemaphores.size.toUInt()
    native.pWaitSemaphores = waitSemaphores.map { it.native }.toNative(scope)
    native.pWaitDstStageMask = listOf(waitDstStageMask.sumBy { it.bit }.toUInt()).toNative(scope)
    native.commandBufferCount = commandBuffers.size.toUInt()
    native.pCommandBuffers = commandBuffers.map { it.native }.toNative(scope)
    native.signalSemaphoreCount = signalSemaphores.size.toUInt()
    native.pSignalSemaphores = signalSemaphores.map { it.native }.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkSubmitInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkSubmitInfo>()
                .also { copyToNative(it, scope) }.ptr

@ExperimentalUnsignedTypes
fun List<VkSubmitInfo>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_ios.VkSubmitInfo>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index], scope) } }
