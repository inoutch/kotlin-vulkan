package io.github.inoutch.kotlin.vulkan.api

actual class VkDescriptorSet {
    var native: Long = 0
        private set

    fun init(nativeDescriptorSet: Long) {
        this.native = nativeDescriptorSet
    }
}
