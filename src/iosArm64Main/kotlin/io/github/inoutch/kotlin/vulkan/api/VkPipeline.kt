package io.github.inoutch.kotlin.vulkan.api

actual class VkPipeline {
    lateinit var native: vulkan_ios.VkPipeline
        private set

    fun init(nativePipeline: vulkan_ios.VkPipeline) {
        this.native = nativePipeline
    }
}
