package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_PIPELINE_DEPTH_STENCIL_STATE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineDepthStencilStateCreateInfo.copyToNative(native: vulkan_ios.VkPipelineDepthStencilStateCreateInfo) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_DEPTH_STENCIL_STATE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.depthTestEnable = depthTestEnable.toVkBool32()
    native.depthWriteEnable = depthWriteEnable.toVkBool32()
    native.depthCompareOp = depthCompareOp.value.toUInt()
    native.depthBoundsTestEnable = depthBoundsTestEnable.toVkBool32()
    native.stencilTestEnable = stencilTestEnable.toVkBool32()
    front.copyToNative(native.front)
    back.copyToNative(native.back)
    native.minDepthBounds = minDepthBounds
    native.maxDepthBounds = maxDepthBounds
}

@ExperimentalUnsignedTypes
fun VkPipelineDepthStencilStateCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkPipelineDepthStencilStateCreateInfo>()
                .also { copyToNative(it) }.ptr
