package io.github.inoutch.kotlin.vulkan.api

actual class VkCommandPool {
    lateinit var native: vulkan_android.VkCommandPool
        private set

    fun init(nativeCommandPool: vulkan_android.VkCommandPool) {
        this.native = nativeCommandPool
    }
}
