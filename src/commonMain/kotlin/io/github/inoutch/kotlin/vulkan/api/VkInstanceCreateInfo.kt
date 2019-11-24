package io.github.inoutch.kotlin.vulkan.api

class VkInstanceCreateInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO,
    val flags: Int,
    val applicationInfo: VkApplicationInfo,
    val enabledLayerNames: List<String>,
    val enabledExtensionNames: List<String>
)
