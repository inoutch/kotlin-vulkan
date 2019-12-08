package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
actual class VkCommandBuffer {
    lateinit var native: vulkan_android.VkCommandBuffer
        private set

    fun init(nativeCommandBuffer: vulkan_android.VkCommandBuffer) {
        this.native = nativeCommandBuffer
    }
}
