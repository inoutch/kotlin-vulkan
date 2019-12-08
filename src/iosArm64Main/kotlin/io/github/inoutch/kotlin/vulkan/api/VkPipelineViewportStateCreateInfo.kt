package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineViewportStateCreateInfo.copyToNative(
    native: vulkan_ios.VkPipelineViewportStateCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.viewportCount = viewports.size.toUInt()
    native.pViewports = viewports.toNative(scope)
    native.scissorCount = scissors.size.toUInt()
    native.pScissors = scissors.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkPipelineViewportStateCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkPipelineViewportStateCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
