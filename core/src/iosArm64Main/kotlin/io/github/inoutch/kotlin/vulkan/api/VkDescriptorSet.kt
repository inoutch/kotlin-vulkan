package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
actual class VkDescriptorSet {
    lateinit var native: vulkan_ios.VkDescriptorSet
        private set

    fun init(nativeDescriptorSet: vulkan_ios.VkDescriptorSet) {
        this.native = nativeDescriptorSet
    }
}
