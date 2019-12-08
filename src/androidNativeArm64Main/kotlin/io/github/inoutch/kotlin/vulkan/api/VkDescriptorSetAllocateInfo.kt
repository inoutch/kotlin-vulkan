package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_DESCRIPTOR_SET_ALLOCATE_INFO

@ExperimentalUnsignedTypes
fun VkDescriptorSetAllocateInfo.copyToNative(
    native: vulkan_android.VkDescriptorSetAllocateInfo,
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
        scope.alloc<vulkan_android.VkDescriptorSetAllocateInfo>()
                .also { copyToNative(it, scope) }.ptr
