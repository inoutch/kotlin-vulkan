package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_PIPELINE_VERTEX_INPUT_STATE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineVertexInputStateCreateInfo.copyToNative(
    native: vulkan_android.VkPipelineVertexInputStateCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_VERTEX_INPUT_STATE_CREATE_INFO
    native.flags = flags.toUInt()
    native.vertexBindingDescriptionCount = vertexBindingDescriptions.size.toUInt()
    native.pVertexBindingDescriptions = vertexBindingDescriptions.toNative(scope)
    native.vertexAttributeDescriptionCount = vertexAttributeDescriptions.size.toUInt()
    native.pVertexAttributeDescriptions = vertexAttributeDescriptions.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkPipelineVertexInputStateCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPipelineVertexInputStateCreateInfo>().also { copyToNative(it, scope) }.ptr
