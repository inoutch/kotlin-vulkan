package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkFramebufferCreateInfo.copyToNative(
    native: vulkan_android.VkFramebufferCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.renderPass = renderPass.native
    native.attachmentCount = attachments.size.toUInt()
    native.pAttachments = attachments.toNative(scope)
    native.width = width.toUInt()
    native.height = height.toUInt()
    native.layers = layers.toUInt()
}

@ExperimentalUnsignedTypes
fun VkFramebufferCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkFramebufferCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
