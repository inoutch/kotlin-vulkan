package io.github.inoutch.kotlin.vulkan.api

class VkImageCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO,
    val flags: List<VkImageCreateFlagBits>,
    val imageType: VkImageType,
    val format: VkFormat,
    val extent: VkExtent3D,
    val mipLevels: Int,
    val arrayLayers: Int,
    val samples: VkSampleCountFlagBits,
    val tiling: VkImageTiling,
    val usage: List<VkImageUsageFlagBits>,
    val sharingMode: VkSharingMode,
    val queueFamilyIndices: List<Int>,
    val initialLayout: VkImageLayout
)
