package io.github.inoutch.kotlin.vulkan.api

actual class VkShaderModule {
    lateinit var native: vulkan_ios.VkShaderModule
        private set

    fun init(nativeShaderModule: vulkan_ios.VkShaderModule) {
        this.native = nativeShaderModule
    }
}
