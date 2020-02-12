package io.github.inoutch.kotlin.vulkan.api

class VkDeviceCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO,
    val flags: List<VkDeviceCreateFlagBits>,
    val queueCreateInfos: List<VkDeviceQueueCreateInfo>,
    val enabledLayerNames: List<String>,
    val enabledExtensionNames: List<String>,
    val enabledFeatures: VkPhysicalDeviceFeatures?
)
