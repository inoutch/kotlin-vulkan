package io.github.inoutch.kotlin.vulkan.api

actual class VkRenderPass {
    lateinit var native: vulkan_ios.VkRenderPass

    fun init(nativeRenderPass: vulkan_ios.VkRenderPass) {
        this.native = nativeRenderPass
    }
}
