package io.github.inoutch.kotlin.vulkan.api

actual class VkImage {
    lateinit var native: vulkan_ios.VkImage
        private set

    fun init(nativeImage: vulkan_ios.VkImage) {
        this.native = nativeImage
    }
}
