package io.github.inoutch.kotlin.vulkan.api

actual class VkShaderModule {
    var native: Long = 0
        private set

    private lateinit var device: VkDevice

    fun init(nativeSurface: Long) {
        this.native = nativeSurface
    }
}
