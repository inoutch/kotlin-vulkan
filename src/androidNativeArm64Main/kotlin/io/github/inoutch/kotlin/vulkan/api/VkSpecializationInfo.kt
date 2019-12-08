package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toCPointer

@ExperimentalUnsignedTypes
fun VkSpecializationInfo.copyToNative(
    native: vulkan_android.VkSpecializationInfo,
    scope: MemScope
) {
    native.mapEntryCount = mapEntities.size.toUInt()
    native.pMapEntries = mapEntities.toNative(scope)
    native.dataSize = data.size.toULong()
    native.pData = data.refTo(0).getPointer(scope).rawValue.toLong().toCPointer()
}

@ExperimentalUnsignedTypes
fun VkSpecializationInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkSpecializationInfo>().also { copyToNative(it, scope) }.ptr
