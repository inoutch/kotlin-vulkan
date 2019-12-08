package io.github.inoutch.kotlin.vulkan.api

actual class VkPipelineLayout {
    lateinit var native: vulkan_ios.VkPipelineLayout
        private set

    fun init(nativePipelineLayout: vulkan_ios.VkPipelineLayout) {
        this.native = nativePipelineLayout
    }
}
