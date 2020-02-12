package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkCommandPoolCreateInfo.copyToNative(native: vulkan_ios.VkCommandPoolCreateInfo) {
    native.sType = VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.queueFamilyIndex = queueFamilyIndex.toUInt()
}

@ExperimentalUnsignedTypes
fun VkCommandPoolCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkCommandPoolCreateInfo>()
                .also { copyToNative(it) }.ptr
