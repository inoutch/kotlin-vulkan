package io.github.inoutch.kotlin.vulkan.api

actual class VkRenderPass {
    lateinit var native: vulkan_android.VkRenderPass

    fun init(nativeRenderPass: vulkan_android.VkRenderPass) {
        this.native = nativeRenderPass
    }
}
