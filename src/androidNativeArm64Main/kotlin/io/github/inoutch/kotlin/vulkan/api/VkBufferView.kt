package io.github.inoutch.kotlin.vulkan.api

actual class VkBufferView {
    lateinit var native: vulkan_android.VkBufferView
        private set

    fun init(nativeBufferView: vulkan_android.VkBufferView) {
        this.native = nativeBufferView
    }
}
