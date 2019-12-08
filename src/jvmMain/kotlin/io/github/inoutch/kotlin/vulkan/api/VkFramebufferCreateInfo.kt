package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import io.github.inoutch.kotlin.vulkan.utility.MemScope
import org.lwjgl.vulkan.VK10

fun VkFramebufferCreateInfo.copyToNative(
    native: org.lwjgl.vulkan.VkFramebufferCreateInfo,
    scope: MemScope
) {

    native.sType(VK10.VK_STRUCTURE_TYPE_FRAMEBUFFER_CREATE_INFO)
            .pNext(VK10.VK_NULL_HANDLE)
            .flags(flags.sumBy { it.bit })
            .renderPass(renderPass.native)
            .pAttachments(attachments.map { it.native }.toLongArray().toNative(scope))
            .width(width)
            .height(height)
            .layers(layers)
}

fun VkFramebufferCreateInfo.toNative(scope: MemScope): org.lwjgl.vulkan.VkFramebufferCreateInfo =
        scope.add(org.lwjgl.vulkan.VkFramebufferCreateInfo.calloc()
                .also { copyToNative(it, scope) })
