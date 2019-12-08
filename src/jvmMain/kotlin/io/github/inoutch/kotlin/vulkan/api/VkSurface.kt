package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MutableProperty
import io.github.inoutch.kotlin.vulkan.utility.memScoped

actual class VkSurface {
    var native: Long = 0
        private set

    fun init(nativeSurface: Long) {
        this.native = nativeSurface
    }
}

fun createWindowSurface(
    instance: VkInstance,
    window: Long,
    surface: MutableProperty<VkSurface>
): VkResult = memScoped {
    val native = allocLong()
    val result = org.lwjgl.glfw.GLFWVulkan.glfwCreateWindowSurface(
            instance.native,
            window,
            null,
            native).toVkResult()
    if (result.isSucceeded()) {
        surface set VkSurface().apply { init(native.get(0)) }
    }
    result
}
