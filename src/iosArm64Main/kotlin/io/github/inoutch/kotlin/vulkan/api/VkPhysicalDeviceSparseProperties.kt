package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun vulkan_ios.VkPhysicalDeviceSparseProperties.toOrigin(): VkPhysicalDeviceSparseProperties {
    return VkPhysicalDeviceSparseProperties(
            residencyStandard2DBlockShape.toBoolean(),
            residencyStandard2DBlockShape.toBoolean(),
            residencyStandard3DBlockShape.toBoolean(),
            residencyAlignedMipSize.toBoolean(),
            residencyNonResidentStrict.toBoolean())
}
