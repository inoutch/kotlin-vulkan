package io.github.inoutch.kotlin.vulkan.api

actual class VkPipelineLayout {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativePipelineLayout: Long, device: VkDevice) {
        this.native = nativePipelineLayout
        this.device = device
    }
}
