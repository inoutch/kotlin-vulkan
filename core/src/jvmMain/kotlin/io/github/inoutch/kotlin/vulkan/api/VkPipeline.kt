package io.github.inoutch.kotlin.vulkan.api

actual class VkPipeline {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativePipeline: Long, device: VkDevice) {
        this.native = nativePipeline
        this.device = device
    }
}
