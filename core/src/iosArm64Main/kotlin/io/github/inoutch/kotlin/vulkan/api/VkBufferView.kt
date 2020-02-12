package io.github.inoutch.kotlin.vulkan.api

actual class VkBufferView {
    lateinit var native: vulkan_ios.VkBufferView
        private set

    fun init(nativeBufferView: vulkan_ios.VkBufferView) {
        this.native = nativeBufferView
    }
}
