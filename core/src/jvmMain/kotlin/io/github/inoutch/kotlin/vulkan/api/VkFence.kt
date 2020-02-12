package io.github.inoutch.kotlin.vulkan.api

actual class VkFence {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativeFence: Long, device: VkDevice) {
        this.native = nativeFence
        this.device = device
    }
}
