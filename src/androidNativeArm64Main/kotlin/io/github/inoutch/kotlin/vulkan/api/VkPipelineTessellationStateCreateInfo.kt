package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_PIPELINE_TESSELLATION_STATE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineTessellationStateCreateInfo.copyToNative(native: vulkan_android.VkPipelineTessellationStateCreateInfo) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_TESSELLATION_STATE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.patchControlPoints = patchControlPoints.toUInt()
}

@ExperimentalUnsignedTypes
fun VkPipelineTessellationStateCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPipelineTessellationStateCreateInfo>()
                .also { copyToNative(it) }.ptr
