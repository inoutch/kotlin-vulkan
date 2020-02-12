package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.set
import vulkan_ios.VkImageViewVar

actual class VkImageView {
    lateinit var native: vulkan_ios.VkImageView
        private set

    fun init(nativeImageView: vulkan_ios.VkImageView) {
        this.native = nativeImageView
    }
}

fun List<VkImageView>.toNative(scope: MemScope) =
        scope.allocArray<VkImageViewVar>(size)
                .also { forEachIndexed { index, x -> it[index] = x.native } }
