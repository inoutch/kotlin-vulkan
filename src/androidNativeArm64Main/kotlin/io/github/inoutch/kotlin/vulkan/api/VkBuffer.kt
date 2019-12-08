package io.github.inoutch.kotlin.vulkan.api

actual class VkBuffer {
    lateinit var native: vulkan_android.VkBuffer
        private set

    fun init(nativeBuffer: vulkan_android.VkBuffer) {
        this.native = nativeBuffer
    }
}
