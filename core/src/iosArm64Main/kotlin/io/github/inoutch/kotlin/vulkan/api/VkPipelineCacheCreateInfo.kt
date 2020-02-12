package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toCPointer
import vulkan_ios.VK_STRUCTURE_TYPE_PIPELINE_CACHE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineCacheCreateInfo.copyToNative(
    native: vulkan_ios.VkPipelineCacheCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_CACHE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.initialDataSize = initialDataSize.toULong()
    native.pInitialData = initialData.refTo(0).getPointer(scope).rawValue.toLong().toCPointer()
}

@ExperimentalUnsignedTypes
fun VkPipelineCacheCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkPipelineCacheCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
