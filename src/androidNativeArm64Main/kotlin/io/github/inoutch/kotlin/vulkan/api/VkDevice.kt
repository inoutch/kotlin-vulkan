package io.github.inoutch.kotlin.vulkan.api

actual class VkDevice {
    lateinit var native: vulkan_android.VkDevice
        private set

    fun init(nativeDevice: vulkan_android.VkDevice) {
        this.native = nativeDevice
    }
}
