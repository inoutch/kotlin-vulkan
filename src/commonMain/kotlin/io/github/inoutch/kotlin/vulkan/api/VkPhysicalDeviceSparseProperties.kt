package io.github.inoutch.kotlin.vulkan.api

class VkPhysicalDeviceSparseProperties(
    val residencyStandard2DBlockShape: Boolean,
    val residencyStandard2DMultisampleBlockShape: Boolean,
    val residencyStandard3DBlockShape: Boolean,
    val residencyAlignedMipSize: Boolean,
    val residencyNonResidentStrict: Boolean
)
