package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import kotlinx.cinterop.set
import vulkan_android.VK_STRUCTURE_TYPE_PIPELINE_COLOR_BLEND_STATE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineColorBlendStateCreateInfo.copyToNative(
    native: vulkan_android.VkPipelineColorBlendStateCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_COLOR_BLEND_STATE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.logicOpEnable = logicOpEnable.toVkBool32()
    native.logicOp = logicOp.value.toUInt()
    native.attachmentCount = attachments.size.toUInt()
    native.pAttachments = attachments.toNative(scope)
    native.blendConstants[0] = blendConstants[0]
    native.blendConstants[1] = blendConstants[1]
    native.blendConstants[2] = blendConstants[2]
    native.blendConstants[3] = blendConstants[3]
}

@ExperimentalUnsignedTypes
fun VkPipelineColorBlendStateCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPipelineColorBlendStateCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
