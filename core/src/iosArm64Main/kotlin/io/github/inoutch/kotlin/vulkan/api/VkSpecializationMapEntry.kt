package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkSpecializationMapEntry.copyToNative(native: vulkan_ios.VkSpecializationMapEntry) {
    native.constantID = constantID.toUInt()
    native.offset = offset.toUInt()
    native.size = size.toULong()
}

@ExperimentalUnsignedTypes
fun List<VkSpecializationMapEntry>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_ios.VkSpecializationMapEntry>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
