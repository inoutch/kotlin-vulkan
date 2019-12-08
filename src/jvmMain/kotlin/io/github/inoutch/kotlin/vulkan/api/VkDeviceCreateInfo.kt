package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.stringsToNative
import io.github.inoutch.kotlin.vulkan.utility.MemScope
import org.lwjgl.vulkan.VK10

fun VkDeviceCreateInfo.copyToNative(native: org.lwjgl.vulkan.VkDeviceCreateInfo, memScope: MemScope) {
    native.sType(VK10.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO)
            .pNext(VK10.VK_NULL_HANDLE)
            .flags(flags.sumBy { it.bit })
            .pQueueCreateInfos(queueCreateInfos.toNative(memScope))
            .ppEnabledExtensionNames(enabledExtensionNames.stringsToNative(memScope))
            .ppEnabledLayerNames(enabledLayerNames.stringsToNative(memScope))
            .pEnabledFeatures(enabledFeatures?.toNative(memScope))
}

fun VkDeviceCreateInfo.toNative(memScope: MemScope): org.lwjgl.vulkan.VkDeviceCreateInfo =
        memScope.add(org.lwjgl.vulkan.VkDeviceCreateInfo.calloc()
                .also { copyToNative(it, memScope) })
