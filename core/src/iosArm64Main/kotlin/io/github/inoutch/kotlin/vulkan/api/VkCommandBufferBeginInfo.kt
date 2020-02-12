package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO

@ExperimentalUnsignedTypes
fun VkCommandBufferBeginInfo.copyToNative(
    native: vulkan_ios.VkCommandBufferBeginInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.value }.toUInt()
    native.pInheritanceInfo = inheritanceInfo?.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkCommandBufferBeginInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkCommandBufferBeginInfo>().also { copyToNative(it, scope) }.ptr
