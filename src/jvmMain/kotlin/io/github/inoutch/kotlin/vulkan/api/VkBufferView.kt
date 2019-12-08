package io.github.inoutch.kotlin.vulkan.api

import org.lwjgl.vulkan.VK10

actual class VkBufferView {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativeBufferView: Long, device: VkDevice) {
        this.native = nativeBufferView
        this.device = device
    }

    fun dispose() {
        VK10.vkDestroyBufferView(device.native, native, null)
    }
}
