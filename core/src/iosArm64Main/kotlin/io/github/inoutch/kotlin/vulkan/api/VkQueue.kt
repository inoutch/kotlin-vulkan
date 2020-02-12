package io.github.inoutch.kotlin.vulkan.api

actual class VkQueue {
    lateinit var native: vulkan_ios.VkQueue

    fun init(native: vulkan_ios.VkQueue) {
        this.native = native
    }
}
