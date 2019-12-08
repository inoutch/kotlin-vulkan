package io.github.inoutch.kotlin.vulkan.api

actual class VkDevice {
    lateinit var native: vulkan_ios.VkDevice
        private set

    fun init(nativeDevice: vulkan_ios.VkDevice) {
        this.native = nativeDevice
    }
}
