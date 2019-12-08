package io.github.inoutch.kotlin.vulkan.api

actual class VkDescriptorPool {
    lateinit var native: vulkan_android.VkDescriptorPool
        private set

    fun init(nativeDescriptorPool: vulkan_android.VkDescriptorPool) {
        this.native = nativeDescriptorPool
    }
}
