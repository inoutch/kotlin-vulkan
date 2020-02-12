package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkDescriptorImageInfo.copyToNative(native: org.lwjgl.vulkan.VkDescriptorImageInfo) {
    native.sampler(sampler.native)
            .imageView(imageView.native)
            .imageLayout(imageLayout.value)
}

fun List<VkDescriptorImageInfo>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.add(org.lwjgl.vulkan.VkDescriptorImageInfo.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } })
