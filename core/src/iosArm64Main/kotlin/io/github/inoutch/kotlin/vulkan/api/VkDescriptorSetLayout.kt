package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.set

actual class VkDescriptorSetLayout {
    lateinit var native: vulkan_ios.VkDescriptorSetLayout
        private set

    fun init(nativeDescriptorSetLayout: vulkan_ios.VkDescriptorSetLayout) {
        this.native = nativeDescriptorSetLayout
    }
}

fun List<VkDescriptorSetLayout>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_ios.VkDescriptorSetLayoutVar>(size)
                .also { forEachIndexed { index, x -> it[index] = x.native } }
