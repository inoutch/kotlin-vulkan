package io.github.inoutch.kotlin.vulkan.api

actual class VkPipeline {
    lateinit var native: vulkan_android.VkPipeline
        private set

    fun init(nativePipeline: vulkan_android.VkPipeline) {
        this.native = nativePipeline
    }
}
