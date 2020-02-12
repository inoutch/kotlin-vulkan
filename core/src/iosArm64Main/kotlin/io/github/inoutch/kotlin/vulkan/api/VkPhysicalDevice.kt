package io.github.inoutch.kotlin.vulkan.api

actual class VkPhysicalDevice actual constructor() {
    lateinit var native: vulkan_ios.VkPhysicalDevice
        private set

    fun init(nativePhysicalDevice: vulkan_ios.VkPhysicalDevice) {
        this.native = nativePhysicalDevice
    }
}
