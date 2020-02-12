package io.github.inoutch.kotlin.vulkan.api

actual class VkImageView {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativeImageView: Long, device: VkDevice) {
        this.native = nativeImageView
        this.device = device
    }
}
