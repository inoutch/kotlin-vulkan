package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import io.github.inoutch.kotlin.vulkan.utility.MemScope
import org.lwjgl.vulkan.VK10

fun VkPipelineCacheCreateInfo.copyToNative(
    native: org.lwjgl.vulkan.VkPipelineCacheCreateInfo,
    scope: MemScope
) {
    native.sType(VK10.VK_STRUCTURE_TYPE_PIPELINE_CACHE_CREATE_INFO)
            .pNext(VK10.VK_NULL_HANDLE)
            .flags(flags)
            .pInitialData(initialData.toNative(scope))
}

fun VkPipelineCacheCreateInfo.toNative(scope: MemScope): org.lwjgl.vulkan.VkPipelineCacheCreateInfo =
        scope.add(org.lwjgl.vulkan.VkPipelineCacheCreateInfo.calloc()
                .also { copyToNative(it, scope) })
