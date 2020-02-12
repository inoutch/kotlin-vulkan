package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkSubpassDescription.copyToNative(native: org.lwjgl.vulkan.VkSubpassDescription, scope: MemScope) {
    native.flags(flags.sumBy { it.bit })
            .pipelineBindPoint(pipelineBindPoint.value)
            .pInputAttachments(inputAttachments.toNative(scope))
            .colorAttachmentCount(colorAttachments.size)
            .pColorAttachments(colorAttachments.toNative(scope))
            .pResolveAttachments(resolveAttachments.toNative(scope))
            .pDepthStencilAttachment(depthStencilAttachment?.toNative(scope))
            .pPreserveAttachments(preserveAttachments.toIntArray().toNative(scope))
}

fun List<VkSubpassDescription>.toNative(memScope: MemScope): org.lwjgl.vulkan.VkSubpassDescription.Buffer =
        memScope.add(org.lwjgl.vulkan.VkSubpassDescription.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index], memScope) } })
