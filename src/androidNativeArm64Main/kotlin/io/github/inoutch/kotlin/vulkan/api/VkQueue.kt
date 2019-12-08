package io.github.inoutch.kotlin.vulkan.api

actual class VkQueue {
    lateinit var native: vulkan_android.VkQueue

    fun init(native: vulkan_android.VkQueue) {
        this.native = native
    }
}
