package io.github.inoutch.kotlin.vulkan.api

fun org.lwjgl.vulkan.VkLayerProperties.toOrigin(): VkLayerProperties {
    return VkLayerProperties(
            layerNameString(),
            specVersion(),
            implementationVersion(),
            descriptionString()
    )
}
