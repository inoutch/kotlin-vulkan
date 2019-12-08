package io.github.inoutch.kotlin.vulkan.api

actual class VkDeviceMemory {
    lateinit var native: vulkan_android.VkDeviceMemory
        private set

    fun init(nativeDeviceMemory: vulkan_android.VkDeviceMemory) {
        this.native = nativeDeviceMemory
    }
}
