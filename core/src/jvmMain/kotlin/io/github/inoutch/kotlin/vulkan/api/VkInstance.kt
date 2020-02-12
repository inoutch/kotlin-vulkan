package io.github.inoutch.kotlin.vulkan.api

actual class VkInstance {

    lateinit var native: org.lwjgl.vulkan.VkInstance
        private set

    fun init(nativeInstance: org.lwjgl.vulkan.VkInstance) {
        this.native = nativeInstance
    }
}
