package io.github.inoutch.kotlin.vulkan.api

class VkPipelineVertexInputStateCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_PIPELINE_VERTEX_INPUT_STATE_CREATE_INFO,
    val flags: Int, // future use
    val vertexBindingDescriptions: List<VkVertexInputBindingDescription>,
    val vertexAttributeDescriptions: List<VkVertexInputAttributeDescription>
)
