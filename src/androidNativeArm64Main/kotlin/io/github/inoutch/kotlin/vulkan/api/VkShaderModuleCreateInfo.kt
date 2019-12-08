package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toCPointer
import vulkan_android.VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkShaderModuleCreateInfo.copyToNative(
    native: vulkan_android.VkShaderModuleCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO
    native.pNext = null
    native.flags = flags.toUInt()
    native.codeSize = code.size.toULong()
    native.pCode = code.refTo(0).getPointer(scope).rawValue.toLong().toCPointer()
}

@ExperimentalUnsignedTypes
fun VkShaderModuleCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkShaderModuleCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
