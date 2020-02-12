package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_DESCRIPTOR_POOL_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkDescriptorPoolCreateInfo.copyToNative(
    native: vulkan_android.VkDescriptorPoolCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_DESCRIPTOR_POOL_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.maxSets = maxSets.toUInt()
    native.poolSizeCount = poolSizes.size.toUInt()
    native.pPoolSizes = poolSizes.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkDescriptorPoolCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkDescriptorPoolCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
