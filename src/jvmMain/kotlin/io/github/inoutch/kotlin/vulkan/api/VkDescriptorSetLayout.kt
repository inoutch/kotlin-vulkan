package io.github.inoutch.kotlin.vulkan.api

actual class VkDescriptorSetLayout {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativeDescriptorSetLayout: Long, device: VkDevice) {
        this.native = nativeDescriptorSetLayout
        this.device = device
    }
}
