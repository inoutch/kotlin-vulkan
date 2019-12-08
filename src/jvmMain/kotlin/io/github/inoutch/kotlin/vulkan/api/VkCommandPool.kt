package io.github.inoutch.kotlin.vulkan.api

actual class VkCommandPool {
    var native: Long = 0
        private set

    fun init(nativeCommandPool: Long) {
        this.native = nativeCommandPool
    }
}
