package io.github.inoutch.kotlin.vulkan.api

actual class VkFramebuffer {
    lateinit var native: vulkan_ios.VkFramebuffer
        private set

    fun init(nativeFramebuffer: vulkan_ios.VkFramebuffer) {
        this.native = nativeFramebuffer
    }
}
