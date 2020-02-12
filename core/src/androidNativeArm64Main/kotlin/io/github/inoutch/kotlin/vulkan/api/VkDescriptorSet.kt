package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
actual class VkDescriptorSet {
    lateinit var native: vulkan_android.VkDescriptorSet
        private set

    fun init(nativeDescriptorSet: vulkan_android.VkDescriptorSet) {
        this.native = nativeDescriptorSet
    }
}
