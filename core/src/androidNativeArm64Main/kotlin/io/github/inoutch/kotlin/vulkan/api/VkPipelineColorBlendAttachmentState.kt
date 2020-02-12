package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.ptr

@ExperimentalUnsignedTypes
fun VkPipelineColorBlendAttachmentState.copyToNative(native: vulkan_android.VkPipelineColorBlendAttachmentState) {
    native.blendEnable = blendEnable.toVkBool32()
    native.srcColorBlendFactor = srcColorBlendFactor.value.toUInt()
    native.dstColorBlendFactor = dstColorBlendFactor.value.toUInt()
    native.colorBlendOp = colorBlendOp.value.toUInt()
    native.srcAlphaBlendFactor = srcAlphaBlendFactor.value.toUInt()
    native.dstAlphaBlendFactor = dstAlphaBlendFactor.value.toUInt()
    native.alphaBlendOp = alphaBlendOp.value.toUInt()
    native.colorWriteMask = colorWriteMask.sumBy { it.bit }.toUInt()
}

@ExperimentalUnsignedTypes
fun VkPipelineColorBlendAttachmentState.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPipelineColorBlendAttachmentState>()
                .also { copyToNative(it) }.ptr

@ExperimentalUnsignedTypes
fun List<VkPipelineColorBlendAttachmentState>.toNative(scope: MemScope) =
        scope.allocArray<vulkan_android.VkPipelineColorBlendAttachmentState>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
