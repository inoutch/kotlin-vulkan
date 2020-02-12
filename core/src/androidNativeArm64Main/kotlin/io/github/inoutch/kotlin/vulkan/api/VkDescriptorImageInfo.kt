package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkDescriptorImageInfo.copyToNative(native: vulkan_android.VkDescriptorImageInfo) {
    native.sampler = sampler.native
    native.imageView = imageView.native
    native.imageLayout = imageLayout.value.toUInt()
}

@ExperimentalUnsignedTypes
fun List<VkDescriptorImageInfo>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_android.VkDescriptorImageInfo>(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } }
