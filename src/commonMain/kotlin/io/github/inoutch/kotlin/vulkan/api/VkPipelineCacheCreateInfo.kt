package io.github.inoutch.kotlin.vulkan.api

class VkPipelineCacheCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_CACHE_CREATE_INFO,
    val flags: Int, // future use
    val initialDataSize: Long,
    val initialData: ByteArray
)
