package io.github.inoutch.kotlin.vulkan.utility

import io.github.inoutch.kotlin.vulkan.api.VkPhysicalDevice
import io.github.inoutch.kotlin.vulkan.api.VkQueueFamilyProperties
import io.github.inoutch.kotlin.vulkan.api.VkSurface
import io.github.inoutch.kotlin.vulkan.api.vk

class DeviceQueueFamilyIndices private constructor(
    val graphicsQueueFamilyIndexes: List<Int>,
    val presentQueueFamilyIndexes: List<Int>
) {
    companion object {
        fun find(physicalDevice: VkPhysicalDevice, surface: VkSurface): DeviceQueueFamilyIndices? {
            val deviceQueueFamilyProperties = mutableListOf<VkQueueFamilyProperties>()
            vk.getPhysicalDeviceQueueFamilyProperties(physicalDevice, deviceQueueFamilyProperties)
            val graphicsQueueFamilyIndexes = deviceQueueFamilyProperties.mapIndexedNotNull { index, properties ->
                index.takeIf { properties.isGraphicsQueueFamily() }
            }
            val supportPresents = deviceQueueFamilyProperties.mapIndexed { index, _ ->
                val supported = MutableProperty<Boolean>()
                vk.getPhysicalDeviceSurfaceSupportKHR(physicalDevice, index, surface, supported)
                supported.value
            }
            val presentQueueFamilyIndexes = supportPresents.mapIndexedNotNull { index, supportPresent ->
                index.takeIf { supportPresent == true && deviceQueueFamilyProperties[index].isGraphicsQueueFamily() }
            }

            if (graphicsQueueFamilyIndexes.isEmpty() || presentQueueFamilyIndexes.isEmpty()) {
                return null
            }
            return DeviceQueueFamilyIndices(graphicsQueueFamilyIndexes, presentQueueFamilyIndexes)
        }
    }

    val graphicsQueueFamilyIndex
        get() = graphicsQueueFamilyIndexes.first()

    val presentQueueFamilyIndex
        get() = presentQueueFamilyIndexes.first()
}
