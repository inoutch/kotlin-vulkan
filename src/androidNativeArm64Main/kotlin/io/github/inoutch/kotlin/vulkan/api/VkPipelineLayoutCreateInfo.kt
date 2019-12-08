package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_PIPELINE_LAYOUT_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineLayoutCreateInfo.copyToNative(
    native: vulkan_android.VkPipelineLayoutCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_LAYOUT_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.setLayoutCount = setLayouts.size.toUInt()
    native.pSetLayouts = setLayouts.toNative(scope)
    native.pushConstantRangeCount = pushConstantRanges.size.toUInt()
    native.pPushConstantRanges = pushConstantRanges.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkPipelineLayoutCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPipelineLayoutCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
