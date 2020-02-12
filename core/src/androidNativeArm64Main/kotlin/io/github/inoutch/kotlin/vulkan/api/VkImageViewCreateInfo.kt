package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_android.VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkImageViewCreateInfo.copyToNative(
    native: vulkan_android.VkImageViewCreateInfo
) {
    native.sType = VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.image = image.native
    native.viewType = viewType.value.toUInt()
    native.format = format.value.toUInt()
    components.copyToNative(native.components)
    subresourceRange.copyToNative(native.subresourceRange)
}

@ExperimentalUnsignedTypes
fun VkImageViewCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkImageViewCreateInfo>()
                .also { copyToNative(it) }.ptr
