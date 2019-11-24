package io.github.inoutch.kotlin.vulkan.api

class VkShaderModuleCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO,
    val flags: Int, // future use
    val code: ByteArray
)
