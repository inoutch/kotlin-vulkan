package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope
import org.lwjgl.vulkan.VK10

fun VkPipelineViewportStateCreateInfo.copyToNative(
    native: org.lwjgl.vulkan.VkPipelineViewportStateCreateInfo,
    scope: MemScope
) {
    native.sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_VIEWPORT_STATE_CREATE_INFO)
            .pNext(VK10.VK_NULL_HANDLE)
            .flags(flags)
            .viewportCount(viewports.size)
            .pViewports(viewports.toNative(scope))
            .scissorCount(scissors.size)
            .pScissors(scissors.toNative(scope))
}

fun VkPipelineViewportStateCreateInfo.toNative(scope: MemScope): org.lwjgl.vulkan.VkPipelineViewportStateCreateInfo =
        scope.add(org.lwjgl.vulkan.VkPipelineViewportStateCreateInfo.calloc()
                .also { copyToNative(it, scope) })
