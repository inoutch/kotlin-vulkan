package io.github.inoutch.kotlin.vulkan.api

enum class VkPresentModeKHR(val value: Int) {
    VK_PRESENT_MODE_IMMEDIATE_KHR(0),
    VK_PRESENT_MODE_MAILBOX_KHR(1),
    VK_PRESENT_MODE_FIFO_KHR(2),
    VK_PRESENT_MODE_FIFO_RELAXED_KHR(3),
    VK_PRESENT_MODE_SHARED_DEMAND_REFRESH_KHR(1000111000),
    VK_PRESENT_MODE_SHARED_CONTINUOUS_REFRESH_KHR(1000111001),
    VK_PRESENT_MODE_MAX_ENUM_KHR(0x7FFFFFFF);

    companion object {
        private val map = VkPresentModeKHR.values()
                .map { it.value to it }
                .toMap()

        fun getValue(value: Int) = map.getValue(value)
    }
}
