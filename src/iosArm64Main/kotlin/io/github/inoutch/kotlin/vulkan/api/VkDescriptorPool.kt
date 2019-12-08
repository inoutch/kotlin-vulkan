package io.github.inoutch.kotlin.vulkan.api

actual class VkDescriptorPool {
    lateinit var native: vulkan_ios.VkDescriptorPool
        private set

    fun init(nativeDescriptorPool: vulkan_ios.VkDescriptorPool) {
        this.native = nativeDescriptorPool
    }
}
