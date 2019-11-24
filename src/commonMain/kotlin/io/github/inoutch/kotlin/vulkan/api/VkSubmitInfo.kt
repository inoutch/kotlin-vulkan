package io.github.inoutch.kotlin.vulkan.api

class VkSubmitInfo(
        val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_SUBMIT_INFO,
    val waitSemaphores: List<VkSemaphore>,
    val waitDstStageMask: List<VkPipelineStageFlagBits>,
    val commandBuffers: List<VkCommandBuffer>,
    val signalSemaphores: List<VkSemaphore>
)
