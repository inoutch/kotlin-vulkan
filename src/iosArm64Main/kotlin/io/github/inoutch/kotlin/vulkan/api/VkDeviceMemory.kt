package io.github.inoutch.kotlin.vulkan.api

actual class VkDeviceMemory {
    lateinit var native: vulkan_ios.VkDeviceMemory
        private set

    fun init(nativeDeviceMemory: vulkan_ios.VkDeviceMemory) {
        this.native = nativeDeviceMemory
    }
}
