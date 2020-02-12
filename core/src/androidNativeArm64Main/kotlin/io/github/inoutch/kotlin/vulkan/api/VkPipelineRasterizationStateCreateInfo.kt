package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_PIPELINE_RASTERIZATION_STATE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineRasterizationStateCreateInfo.copyToNative(native: vulkan_android.VkPipelineRasterizationStateCreateInfo) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_RASTERIZATION_STATE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.polygonMode = polygonMode.value.toUInt()
    native.cullMode = cullMode.bit.toUInt()
    native.frontFace = frontFace.value.toUInt()
    native.depthClampEnable = depthClampEnable.toVkBool32()
    native.rasterizerDiscardEnable = rasterizerDiscardEnable.toVkBool32()
    native.depthBiasEnable = depthBiasEnable.toVkBool32()
    native.depthBiasConstantFactor = depthBiasConstantFactor
    native.depthBiasClamp = depthBiasClamp
    native.depthBiasSlopeFactor = depthBiasSlopeFactor
    native.lineWidth = lineWidth
}

@ExperimentalUnsignedTypes
fun VkPipelineRasterizationStateCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPipelineRasterizationStateCreateInfo>()
                .also { copyToNative(it) }.ptr
