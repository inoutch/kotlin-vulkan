package io.github.inoutch.kotlin.vulkan.api

actual class VkPipelineCache {
    lateinit var native: vulkan_android.VkPipelineCache
        private set

    fun init(nativePipelineCache: vulkan_android.VkPipelineCache) {
        this.native = nativePipelineCache
    }
}
