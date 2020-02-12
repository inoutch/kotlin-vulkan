package io.github.inoutch.kotlin.vulkan.api

actual class VkSemaphore {
    lateinit var native: vulkan_android.VkSemaphore
        private set

    fun init(nativeSemaphore: vulkan_android.VkSemaphore) {
        this.native = nativeSemaphore
    }
}
