package io.github.inoutch.kotlin.vulkan.api

actual class VkInstance {
    lateinit var native: vulkan_android.VkInstance
        private set

    fun init(nativeInstance: vulkan_android.VkInstance) {
        this.native = nativeInstance
    }
}
