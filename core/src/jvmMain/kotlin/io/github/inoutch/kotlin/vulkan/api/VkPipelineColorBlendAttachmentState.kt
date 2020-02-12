package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkPipelineColorBlendAttachmentState.copyToNative(native: org.lwjgl.vulkan.VkPipelineColorBlendAttachmentState) {
    native.blendEnable(blendEnable)
            .srcColorBlendFactor(srcColorBlendFactor.value)
            .dstColorBlendFactor(dstColorBlendFactor.value)
            .colorBlendOp(colorBlendOp.value)
            .srcAlphaBlendFactor(srcAlphaBlendFactor.value)
            .dstAlphaBlendFactor(dstAlphaBlendFactor.value)
            .alphaBlendOp(alphaBlendOp.value)
            .colorWriteMask(colorWriteMask.sumBy { it.bit })
}

fun VkPipelineColorBlendAttachmentState.toNative(memScope: MemScope): org.lwjgl.vulkan.VkPipelineColorBlendAttachmentState =
        memScope.add(org.lwjgl.vulkan.VkPipelineColorBlendAttachmentState.calloc()
                .also { copyToNative(it) })

fun List<VkPipelineColorBlendAttachmentState>.toNative(memScope: MemScope) =
        memScope.add(org.lwjgl.vulkan.VkPipelineColorBlendAttachmentState.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } })
