package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope
import org.lwjgl.vulkan.VK10

fun VkFenceCreateInfo.copyToNative(native: org.lwjgl.vulkan.VkFenceCreateInfo) {
    native.sType(VK10.VK_STRUCTURE_TYPE_FENCE_CREATE_INFO)
            .pNext(VK10.VK_NULL_HANDLE)
            .flags(flags.sumBy { it.bit })
}

fun VkFenceCreateInfo.toNative(scope: MemScope): org.lwjgl.vulkan.VkFenceCreateInfo =
        scope.add(org.lwjgl.vulkan.VkFenceCreateInfo.calloc()
                .also { copyToNative(it) })
