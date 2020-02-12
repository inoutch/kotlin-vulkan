package io.github.inoutch.kotlin.vulkan.api

actual class VkFence {
    lateinit var native: vulkan_android.VkFence
        private set

    fun init(nativeFence: vulkan_android.VkFence) {
        this.native = nativeFence
    }
}
