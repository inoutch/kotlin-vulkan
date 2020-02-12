package io.github.inoutch.kotlin.vulkan.api

actual class VkPipelineCache {
    lateinit var native: vulkan_ios.VkPipelineCache
        private set

    fun init(nativePipelineCache: vulkan_ios.VkPipelineCache) {
        this.native = nativePipelineCache
    }
}
