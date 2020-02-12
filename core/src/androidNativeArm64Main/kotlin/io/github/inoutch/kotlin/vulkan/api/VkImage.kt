package io.github.inoutch.kotlin.vulkan.api

actual class VkImage {
    lateinit var native: vulkan_android.VkImage
        private set

    fun init(nativeImage: vulkan_android.VkImage) {
        this.native = nativeImage
    }
}
