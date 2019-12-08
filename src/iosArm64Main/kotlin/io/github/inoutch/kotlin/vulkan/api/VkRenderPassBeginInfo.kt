package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_RENDER_PASS_BEGIN_INFO

@ExperimentalUnsignedTypes
fun VkRenderPassBeginInfo.copyToNative(
    native: vulkan_ios.VkRenderPassBeginInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_RENDER_PASS_BEGIN_INFO
    native.pNext = null
    native.renderPass = renderPass.native
    native.framebuffer = framebuffer.native
    native.renderArea.offset.x = renderArea.offset.x
    native.renderArea.offset.y = renderArea.offset.y
    native.renderArea.extent.width = renderArea.extent.width.toUInt()
    native.renderArea.extent.height = renderArea.extent.height.toUInt()
    native.clearValueCount = clearValues.size.toUInt()
    native.pClearValues = clearValues.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkRenderPassBeginInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkRenderPassBeginInfo>().also { copyToNative(it, scope) }.ptr
