package io.github.inoutch.kotlin.vulkan.api

actual class VkDevice {
    lateinit var native: org.lwjgl.vulkan.VkDevice
        private set

    fun init(nativeDevice: org.lwjgl.vulkan.VkDevice) {
        this.native = nativeDevice
    }
}
