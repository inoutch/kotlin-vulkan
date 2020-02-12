package io.github.inoutch.kotlin.vulkan.api

actual class VkFramebuffer {
    lateinit var native: vulkan_android.VkFramebuffer
        private set

    fun init(nativeFramebuffer: vulkan_android.VkFramebuffer) {
        this.native = nativeFramebuffer
    }
}
