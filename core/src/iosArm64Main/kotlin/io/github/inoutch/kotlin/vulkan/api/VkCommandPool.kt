package io.github.inoutch.kotlin.vulkan.api

actual class VkCommandPool {
    lateinit var native: vulkan_ios.VkCommandPool
        private set

    fun init(nativeCommandPool: vulkan_ios.VkCommandPool) {
        this.native = nativeCommandPool
    }
}
