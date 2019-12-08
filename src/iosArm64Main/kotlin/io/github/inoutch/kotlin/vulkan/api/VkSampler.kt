package io.github.inoutch.kotlin.vulkan.api

actual class VkSampler {
    lateinit var native: vulkan_ios.VkSampler
        private set

    fun init(nativeSampler: vulkan_ios.VkSampler) {
        this.native = nativeSampler
    }
}
