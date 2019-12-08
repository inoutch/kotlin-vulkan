package io.github.inoutch.kotlin.vulkan.utility

import io.github.inoutch.kotlin.vulkan.api.VkCommandBuffer
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferAllocateInfo
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferBeginInfo
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferLevel
import io.github.inoutch.kotlin.vulkan.api.VkCommandBufferUsageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkCommandPool
import io.github.inoutch.kotlin.vulkan.api.VkDevice
import io.github.inoutch.kotlin.vulkan.api.VkQueue
import io.github.inoutch.kotlin.vulkan.api.VkStructureType
import io.github.inoutch.kotlin.vulkan.api.VkSubmitInfo
import io.github.inoutch.kotlin.vulkan.api.vk

class SingleCommandBuffer(
    private val device: VkDevice,
    private val commandPool: VkCommandPool,
    private val queue: VkQueue
) {
    private val commandBuffer: VkCommandBuffer

    init {
        val allocateInfo = VkCommandBufferAllocateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO,
                commandPool,
                VkCommandBufferLevel.VK_COMMAND_BUFFER_LEVEL_PRIMARY,
                1
        )
        val commandBufferRef = mutableListOf<VkCommandBuffer>()
        vk.allocateCommandBuffers(device, allocateInfo, commandBufferRef)
        commandBuffer = commandBufferRef.first()
    }

    fun submit(scope: (commandBuffer: VkCommandBuffer) -> Unit) {
        val beginInfo = VkCommandBufferBeginInfo(
                VkStructureType.VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO,
                listOf(VkCommandBufferUsageFlagBits.VK_COMMAND_BUFFER_USAGE_ONE_TIME_SUBMIT_BIT),
                null
        )
        vk.beginCommandBuffer(commandBuffer, beginInfo)

        try {
            scope(commandBuffer)
        } catch (e: Error) {
            vk.endCommandBuffer(commandBuffer)
            throw e
        }

        vk.endCommandBuffer(commandBuffer)

        val submitInfo = VkSubmitInfo(
                VkStructureType.VK_STRUCTURE_TYPE_SUBMIT_INFO,
                listOf(),
                listOf(),
                listOf(commandBuffer),
                listOf()
        )
        vk.queueSubmit(queue, listOf(submitInfo), null)
        vk.queueWaitIdle(queue)
    }

    fun destroy() {
        vk.freeCommandBuffers(device, commandPool, listOf(commandBuffer))
    }
}
