package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
actual class VkCommandBuffer {
    lateinit var native: vulkan_ios.VkCommandBuffer
        private set

    fun init(nativeCommandBuffer: vulkan_ios.VkCommandBuffer) {
        this.native = nativeCommandBuffer
    }
}
