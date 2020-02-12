package io.github.inoutch.kotlin.vulkan.api

actual class VkImage {
    var native: Long = 0
        private set

    private var device: VkDevice? = null

    fun init(nativeImage: Long, device: VkDevice? = null) {
        this.native = nativeImage
        this.device = device
    }
}
