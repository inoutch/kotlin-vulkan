package io.github.inoutch.kotlin.vulkan.api

actual class VkSwapchainKHR {
    var native: Long = 0

    fun init(nativeSwapchain: Long) {
        this.native = nativeSwapchain
    }
}
