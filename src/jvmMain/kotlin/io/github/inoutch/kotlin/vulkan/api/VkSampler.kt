package io.github.inoutch.kotlin.vulkan.api

actual class VkSampler {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativeSampler: Long, device: VkDevice) {
        this.native = nativeSampler
        this.device = device
    }
}
