package io.github.inoutch.kotlin.vulkan.api

class VkPipelineInputAssemblyStateCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_INPUT_ASSEMBLY_STATE_CREATE_INFO,
    val flags: Int, // future use
    val topology: VkPrimitiveTopology,
    val primitiveRestartEnable: Boolean
)
