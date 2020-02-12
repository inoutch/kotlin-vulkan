package io.github.inoutch.kotlin.vulkan.api

actual class VkDescriptorPool {
    var native: Long = 0
        private set

    fun init(nativeDescriptorPool: Long) {
        this.native = nativeDescriptorPool
    }
}
