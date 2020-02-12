package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr
import vulkan_ios.VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO

@ExperimentalUnsignedTypes
fun VkImageCreateInfo.copyToNative(
    native: vulkan_ios.VkImageCreateInfo,
    scope: MemScope
) {
    native.sType = VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO
    native.pNext = null
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.imageType = imageType.value.toUInt()
    extent.copyToNative(native.extent)
    native.format = format.value.toUInt()
    native.mipLevels = mipLevels.toUInt()
    native.arrayLayers = arrayLayers.toUInt()
    native.samples = samples.bit.toUInt()
    native.tiling = tiling.value.toUInt()
    native.usage = usage.sumBy { it.bit }.toUInt()
    native.sharingMode = sharingMode.value.toUInt()
    native.queueFamilyIndexCount = queueFamilyIndices.size.toUInt()
    native.pQueueFamilyIndices = queueFamilyIndices.map { it.toUInt() }.toNative(scope)
    native.initialLayout = initialLayout.value.toUInt()
}

@ExperimentalUnsignedTypes
fun VkImageCreateInfo.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkImageCreateInfo>()
                .also { copyToNative(it, scope) }.ptr
