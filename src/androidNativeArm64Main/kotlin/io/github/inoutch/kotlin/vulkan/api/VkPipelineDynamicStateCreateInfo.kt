package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_PIPELINE_DYNAMIC_STATE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineDynamicStateCreateInfo.copyToNative(
    native: vulkan_android.VkPipelineDynamicStateCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_DYNAMIC_STATE_CREATE_INFO
    native.pNext = null
    native.dynamicStateCount = dynamicStates.size.toUInt()
    native.pDynamicStates = dynamicStates.map { it.value.toUInt() }.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkPipelineDynamicStateCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPipelineDynamicStateCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
