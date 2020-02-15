package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.toKStringFromUtf8

@ExperimentalUnsignedTypes
fun vulkan_ios.VkLayerProperties.toOrigin(): VkLayerProperties {
    return VkLayerProperties(
            layerName.toKStringFromUtf8(),
            specVersion.toInt(),
            implementationVersion.toInt(),
            description.toKStringFromUtf8()
    )
}
