package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_COMMAND_BUFFER_INHERITANCE_INFO

@ExperimentalUnsignedTypes
fun VkCommandBufferInheritanceInfo.copyToNative(native: vulkan_android.VkCommandBufferInheritanceInfo) {
    native.sType = VK_STRUCTURE_TYPE_COMMAND_BUFFER_INHERITANCE_INFO
    native.pNext = null
    native.renderPass = renderPass.native
    native.subpass = subpass.toUInt()
    native.framebuffer = framebuffer.native
    native.occlusionQueryEnable = occlusionQueryEnable.toVkBool32()
    native.queryFlags = queryFlags.sumBy { it.bit }.toUInt()
    native.pipelineStatistics = pipelineStatistics.sumBy { it.value }.toUInt()
}

@ExperimentalUnsignedTypes
fun VkCommandBufferInheritanceInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkCommandBufferInheritanceInfo>().also { copyToNative(it) }.ptr
