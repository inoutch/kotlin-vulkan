package io.github.inoutch.kotlin.vulkan.api

actual class VkCommandBuffer {
    lateinit var native: org.lwjgl.vulkan.VkCommandBuffer
        private set

    fun init(nativeCommandBuffer: org.lwjgl.vulkan.VkCommandBuffer) {
        this.native = nativeCommandBuffer
    }
}
