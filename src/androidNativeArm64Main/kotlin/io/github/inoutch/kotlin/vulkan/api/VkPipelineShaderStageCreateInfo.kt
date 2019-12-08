package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.cstr
import kotlinx.cinterop.get
import vulkan_android.VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkPipelineShaderStageCreateInfo.copyToNative(
    native: vulkan_android.VkPipelineShaderStageCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_PIPELINE_SHADER_STAGE_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.stage = stage.sumBy { it.bit }.toUInt()
    native.module = module.native
    native.pName = name.cstr.getPointer(scope)
    native.pSpecializationInfo = specializationInfo?.toNative(scope)
}

@ExperimentalUnsignedTypes
fun List<VkPipelineShaderStageCreateInfo>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_android.VkPipelineShaderStageCreateInfo>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index], scope) } }
