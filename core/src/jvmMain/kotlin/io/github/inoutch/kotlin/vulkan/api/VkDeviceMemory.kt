package io.github.inoutch.kotlin.vulkan.api

actual class VkDeviceMemory {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativeMemory: Long, device: VkDevice) {
        this.native = nativeMemory
        this.device = device
    }
}
