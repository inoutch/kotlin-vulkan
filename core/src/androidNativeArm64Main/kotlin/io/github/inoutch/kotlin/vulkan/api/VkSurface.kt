package io.github.inoutch.kotlin.vulkan.api

import vulkan_android.VkSurfaceKHR

actual class VkSurface {
    lateinit var native: VkSurfaceKHR

    fun init(nativeSurface: VkSurfaceKHR) {
        this.native = nativeSurface
    }
}
