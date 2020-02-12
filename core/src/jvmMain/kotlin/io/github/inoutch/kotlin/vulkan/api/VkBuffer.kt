package io.github.inoutch.kotlin.vulkan.api

actual class VkBuffer {
    var native: Long = 0
        private set

    fun init(nativeBuffer: Long) {
        this.native = nativeBuffer
    }
}
