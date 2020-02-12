package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.Version

class VkApplicationInfo(
    val sType: VkStructureType = VkStructureType.VK_STRUCTURE_TYPE_APPLICATION_INFO,
    val applicationName: String,
    val applicationVersion: Version,
    val engineName: String,
    val engineVersion: Version,
    val apiVersion: Version
)
