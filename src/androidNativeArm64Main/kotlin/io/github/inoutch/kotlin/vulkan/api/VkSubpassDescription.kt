package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkSubpassDescription.copyToNative(
    native: vulkan_android.VkSubpassDescription,
    scope: MemScope
) {
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.pipelineBindPoint = pipelineBindPoint.value.toUInt()
    native.inputAttachmentCount = inputAttachments.size.toUInt()
    native.pInputAttachments = inputAttachments.toNative(scope)
    native.colorAttachmentCount = colorAttachments.size.toUInt()
    native.pColorAttachments = colorAttachments.toNative(scope)
    native.pResolveAttachments = resolveAttachments.toNative(scope)
    native.pDepthStencilAttachment = depthStencilAttachment?.toNative(scope)
}

@ExperimentalUnsignedTypes
fun List<VkSubpassDescription>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_android.VkSubpassDescription>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index], scope) } }
