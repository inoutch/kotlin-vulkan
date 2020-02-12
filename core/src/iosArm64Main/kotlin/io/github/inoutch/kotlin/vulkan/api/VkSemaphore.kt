package io.github.inoutch.kotlin.vulkan.api

actual class VkSemaphore {
    lateinit var native: vulkan_ios.VkSemaphore
        private set

    fun init(nativeSemaphore: vulkan_ios.VkSemaphore) {
        this.native = nativeSemaphore
    }
}
