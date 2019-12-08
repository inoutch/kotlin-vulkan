package io.github.inoutch.kotlin.vulkan.api

actual class VkPipelineLayout {
    lateinit var native: vulkan_android.VkPipelineLayout
        private set

    fun init(nativePipelineLayout: vulkan_android.VkPipelineLayout) {
        this.native = nativePipelineLayout
    }
}
