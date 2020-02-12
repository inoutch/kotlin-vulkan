package io.github.inoutch.kotlin.vulkan.api

actual class VkFramebuffer {
    var native: Long = 0
        private set

    fun init(nativeFramebuffer: Long) {
        this.native = nativeFramebuffer
    }
}
