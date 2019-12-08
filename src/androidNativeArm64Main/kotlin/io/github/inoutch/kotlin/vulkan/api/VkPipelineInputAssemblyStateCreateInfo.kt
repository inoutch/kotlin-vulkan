package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_PIPELINE_INPUT_ASSEMBLY_STATE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineInputAssemblyStateCreateInfo.copyToNative(native: vulkan_android.VkPipelineInputAssemblyStateCreateInfo) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_INPUT_ASSEMBLY_STATE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.topology = topology.value.toUInt()
    native.primitiveRestartEnable = primitiveRestartEnable.toVkBool32()
}

@ExperimentalUnsignedTypes
fun VkPipelineInputAssemblyStateCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPipelineInputAssemblyStateCreateInfo>()
                .also { copyToNative(it) }.ptr
