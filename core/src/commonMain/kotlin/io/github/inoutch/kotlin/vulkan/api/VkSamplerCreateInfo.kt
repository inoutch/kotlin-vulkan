package io.github.inoutch.kotlin.vulkan.api

class VkSamplerCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_SAMPLER_CREATE_INFO,
    val flags: Int,
    val magFilter: VkFilter,
    val minFilter: VkFilter,
    val mipmapMode: VkSamplerMipmapMode,
    val addressModeU: VkSamplerAddressMode,
    val addressModeV: VkSamplerAddressMode,
    val addressModeW: VkSamplerAddressMode,
    val mipLodBias: Float,
    val anisotropyEnable: Boolean,
    val maxAnisotropy: Float,
    val compareEnable: Boolean,
    val compareOp: VkCompareOp,
    val minLod: Float,
    val maxLod: Float,
    val borderColor: VkBorderColor,
    val unnormalizedCoordinates: Boolean
)
