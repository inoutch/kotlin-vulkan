package io.github.inoutch.kotlin.vulkan.api

actual class VkPipelineCache {
    var native: Long = 0

    private lateinit var device: VkDevice

    fun init(nativePipelineCache: Long, device: VkDevice) {
        this.native = nativePipelineCache
        this.device = device
    }
}
