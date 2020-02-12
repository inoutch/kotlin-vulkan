package io.github.inoutch.kotlin.vulkan.api

actual class VkShaderModule {
    lateinit var native: vulkan_android.VkShaderModule
        private set

    fun init(nativeShaderModule: vulkan_android.VkShaderModule) {
        this.native = nativeShaderModule
    }
}
