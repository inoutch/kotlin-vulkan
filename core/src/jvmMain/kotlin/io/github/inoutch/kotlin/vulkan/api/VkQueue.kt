package io.github.inoutch.kotlin.vulkan.api

actual class VkQueue {
    lateinit var native: org.lwjgl.vulkan.VkQueue
        private set

    fun init(nativeQueue: org.lwjgl.vulkan.VkQueue) {
        native = nativeQueue
    }
}
