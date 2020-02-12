package io.github.inoutch.kotlin.vulkan.api

actual class VkRenderPass {
    var native: Long = 0
        private set

    fun init(nativeRenderPass: Long) {
        this.native = nativeRenderPass
    }
}
