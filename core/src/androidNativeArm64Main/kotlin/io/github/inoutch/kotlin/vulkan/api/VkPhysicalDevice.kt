package io.github.inoutch.kotlin.vulkan.api

actual class VkPhysicalDevice actual constructor() {
    lateinit var native: vulkan_android.VkPhysicalDevice
        private set

    fun init(nativePhysicalDevice: vulkan_android.VkPhysicalDevice) {
        this.native = nativePhysicalDevice
    }
}
