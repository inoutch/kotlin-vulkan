package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.ptr

@ExperimentalUnsignedTypes
fun VkAttachmentReference.copyToNative(native: vulkan_android.VkAttachmentReference) {
    native.attachment = attachment.toUInt()
    native.layout = layout.value.toUInt()
}

@ExperimentalUnsignedTypes
fun VkAttachmentReference.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkAttachmentReference>().also { copyToNative(it) }.ptr

@ExperimentalUnsignedTypes
fun List<VkAttachmentReference>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_android.VkAttachmentReference>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
