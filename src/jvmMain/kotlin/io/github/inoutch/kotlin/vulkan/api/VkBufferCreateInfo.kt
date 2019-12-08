package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import io.github.inoutch.kotlin.vulkan.utility.MemScope
import org.lwjgl.vulkan.VK10

fun VkBufferCreateInfo.copyToNative(
    native: org.lwjgl.vulkan.VkBufferCreateInfo,
    scope: MemScope
) {
    native.sType(VK10.VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO)
            .pNext(VK10.VK_NULL_HANDLE)
            .flags(flags.sumBy { it.bit })
            .size(size)
            .usage(usage.sumBy { it.bit })
            .sharingMode(sharingMode.value)
            .pQueueFamilyIndices(queueFamilyIndices?.toIntArray()?.toNative(scope))
}

fun VkBufferCreateInfo.toNative(scope: MemScope): org.lwjgl.vulkan.VkBufferCreateInfo =
        scope.add(org.lwjgl.vulkan.VkBufferCreateInfo.calloc()
                .also { copyToNative(it, scope) })
