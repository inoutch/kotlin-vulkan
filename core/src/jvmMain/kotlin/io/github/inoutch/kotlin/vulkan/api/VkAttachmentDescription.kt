package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkAttachmentDescription.copyToNative(native: org.lwjgl.vulkan.VkAttachmentDescription) {
    native.flags(flags.sumBy { it.bit })
            .format(format.value)
            .samples(samples.sumBy { it.bit })
            .loadOp(loadOp.value)
            .storeOp(storeOp.value)
            .stencilLoadOp(stencilLoadOp.value)
            .stencilStoreOp(stencilStoreOp.value)
            .initialLayout(initialLayout.value)
            .finalLayout(finalLayout.value)
}

fun VkAttachmentDescription.toNative(memScope: MemScope): org.lwjgl.vulkan.VkAttachmentDescription =
        memScope.add(org.lwjgl.vulkan.VkAttachmentDescription.calloc()
                .also { copyToNative(it) })

fun List<VkAttachmentDescription>.toNative(memScope: MemScope): org.lwjgl.vulkan.VkAttachmentDescription.Buffer =
        memScope.add(org.lwjgl.vulkan.VkAttachmentDescription.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } })
