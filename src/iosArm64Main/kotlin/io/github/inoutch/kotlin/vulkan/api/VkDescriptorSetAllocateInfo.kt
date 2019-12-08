package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_DESCRIPTOR_SET_ALLOCATE_INFO

@ExperimentalUnsignedTypes
fun VkDescriptorSetAllocateInfo.copyToNative(
    native: vulkan_ios.VkDescriptorSetAllocateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_DESCRIPTOR_SET_ALLOCATE_INFO
    native.pNext = null
    native.descriptorPool = descriptorPool.native
    native.descriptorSetCount = descriptorSetCount.toUInt()
    native.pSetLayouts = setLayouts.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkDescriptorSetAllocateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkDescriptorSetAllocateInfo>()
                .also { copyToNative(it, scope) }.ptr
