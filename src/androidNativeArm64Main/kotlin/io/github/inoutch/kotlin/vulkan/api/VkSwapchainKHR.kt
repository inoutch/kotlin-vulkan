package io.github.inoutch.kotlin.vulkan.api

actual class VkSwapchainKHR {
    lateinit var native: vulkan_android.VkSwapchainKHR
        private set

    fun init(nativeSwapchain: vulkan_android.VkSwapchainKHR) {
        this.native = nativeSwapchain
    }
}
