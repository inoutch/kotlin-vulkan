package io.github.inoutch.kotlin.vulkan.api

actual class VkFence {
    lateinit var native: vulkan_ios.VkFence
        private set

    fun init(nativeFence: vulkan_ios.VkFence) {
        this.native = nativeFence
    }
}
