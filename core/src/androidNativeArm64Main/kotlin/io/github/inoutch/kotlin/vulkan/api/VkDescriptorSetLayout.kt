package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.set

actual class VkDescriptorSetLayout {
    lateinit var native: vulkan_android.VkDescriptorSetLayout
        private set

    fun init(nativeDescriptorSetLayout: vulkan_android.VkDescriptorSetLayout) {
        this.native = nativeDescriptorSetLayout
    }
}

fun List<VkDescriptorSetLayout>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_android.VkDescriptorSetLayoutVar>(size)
                .also { forEachIndexed { index, x -> it[index] = x.native } }
