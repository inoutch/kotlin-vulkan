package io.github.inoutch.kotlin.vulkan.api

actual class VkInstance {
    lateinit var native: vulkan_ios.VkInstance
        private set

    fun init(nativeInstance: vulkan_ios.VkInstance) {
        this.native = nativeInstance
    }
}
