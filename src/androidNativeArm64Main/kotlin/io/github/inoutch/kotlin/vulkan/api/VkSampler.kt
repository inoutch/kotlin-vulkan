package io.github.inoutch.kotlin.vulkan.api

actual class VkSampler {
    lateinit var native: vulkan_android.VkSampler
        private set

    fun init(nativeSampler: vulkan_android.VkSampler) {
        this.native = nativeSampler
    }
}
