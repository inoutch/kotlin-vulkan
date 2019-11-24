package io.github.inoutch.kotlin.vulkan.api

actual class VkBuffer {
    lateinit var native: vulkan.VkBuffer
        private set

    private lateinit var device: VkDevice

    fun init(nativeBuffer: vulkan.VkBuffer, device: VkDevice) {
        this.native = nativeBuffer
        this.device = device
    }

    override fun dispose() {
        vulkan.vkDestroyBuffer(device.native, native, null)
    }
}
