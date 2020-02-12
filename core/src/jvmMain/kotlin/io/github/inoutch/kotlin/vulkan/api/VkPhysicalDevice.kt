package io.github.inoutch.kotlin.vulkan.api

actual class VkPhysicalDevice {
    lateinit var native: org.lwjgl.vulkan.VkPhysicalDevice
        private set

    fun init(nativePhysicalDevice: org.lwjgl.vulkan.VkPhysicalDevice) {
        this.native = nativePhysicalDevice
    }
}
