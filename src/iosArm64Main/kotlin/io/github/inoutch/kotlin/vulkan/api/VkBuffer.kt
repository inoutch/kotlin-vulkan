package io.github.inoutch.kotlin.vulkan.api

actual class VkBuffer {
    lateinit var native: vulkan_ios.VkBuffer
        private set

    fun init(nativeBuffer: vulkan_ios.VkBuffer) {
        this.native = nativeBuffer
    }
}
