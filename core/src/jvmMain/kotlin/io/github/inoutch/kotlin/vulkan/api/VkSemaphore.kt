package io.github.inoutch.kotlin.vulkan.api

actual class VkSemaphore {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativeSemaphore: Long) {
        this.native = nativeSemaphore
    }
}
