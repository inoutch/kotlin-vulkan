package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_DESCRIPTOR_SET_LAYOUT_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkDescriptorSetLayoutCreateInfo.copyToNative(
    native: vulkan_ios.VkDescriptorSetLayoutCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_DESCRIPTOR_SET_LAYOUT_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.bindingCount = bindings.size.toUInt()
    native.pBindings = bindings.toNative(scope)
}

@ExperimentalUnsignedTypes
fun VkDescriptorSetLayoutCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkDescriptorSetLayoutCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
