package io.github.inoutch.kotlin.vulkan.api

class VkPresentInfoKHR(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PRESENT_INFO_KHR,
    val waitSemaphores: List<VkSemaphore>,
    val swapchains: List<VkSwapchainKHR>,
    val imageIndices: List<Int>,
    val results: List<VkResult>?
)
