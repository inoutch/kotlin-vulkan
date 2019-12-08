package io.github.inoutch.kotlin.vulkan.api

actual class VkSwapchainKHR {
    lateinit var native: vulkan_ios.VkSwapchainKHR
        private set

    fun init(nativeSwapchain: vulkan_ios.VkSwapchainKHR) {
        this.native = nativeSwapchain
    }
}
