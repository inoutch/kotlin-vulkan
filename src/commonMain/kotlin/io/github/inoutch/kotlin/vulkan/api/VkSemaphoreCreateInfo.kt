package io.github.inoutch.kotlin.vulkan.api

class VkSemaphoreCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_SEMAPHORE_CREATE_INFO,
    val flags: Int = 0
) // flags must be 0
