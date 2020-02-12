package io.github.inoutch.kotlin.vulkan.api

class VkQueueFamilyProperties(
    val queueFlags: Int,
    val queueCount: Int,
    val timestampValidBits: Int,
    val minImageTransferGranularity: VkExtent3D
) {
    fun isGraphicsQueueFamily(): Boolean {
        return queueFlags and VkQueueFlagBits.VK_QUEUE_GRAPHICS_BIT.bit != 0
    }
}
