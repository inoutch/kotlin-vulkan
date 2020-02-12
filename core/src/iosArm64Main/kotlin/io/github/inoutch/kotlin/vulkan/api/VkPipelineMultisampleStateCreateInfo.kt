package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_PIPELINE_MULTISAMPLE_STATE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineMultisampleStateCreateInfo.copyToNative(
    native: vulkan_ios.VkPipelineMultisampleStateCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_MULTISAMPLE_STATE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.rasterizationSamples = rasterizationSamples.sumBy { it.bit }.toUInt()
    native.sampleShadingEnable = sampleShadingEnable.toVkBool32()
    native.minSampleShading = minSampleShading
    native.pSampleMask = sampleMask?.let { listOf(it.toUInt()) }?.toNative(scope)
    native.alphaToCoverageEnable = alphaToCoverageEnable.toVkBool32()
    native.alphaToOneEnable = alphaToOneEnable.toVkBool32()
}

@ExperimentalUnsignedTypes
fun VkPipelineMultisampleStateCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkPipelineMultisampleStateCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
