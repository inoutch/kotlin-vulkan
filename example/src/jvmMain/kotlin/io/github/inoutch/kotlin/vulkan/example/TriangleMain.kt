package io.github.inoutch.kotlin.vulkan.example

import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.createWindowSurface
import io.github.inoutch.kotlin.vulkan.extension.toStringList
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWVulkan
import org.lwjgl.system.MemoryUtil

const val TARGET_FPS = 60

fun triangleMain(args: Array<String>) {
    check(GLFW.glfwInit()) { "Failed to initialize GLFW" }
    check(GLFWVulkan.glfwVulkanSupported()) { "Unsupported vulkan" }

    GLFW.glfwDefaultWindowHints()
    GLFW.glfwWindowHint(GLFW.GLFW_CLIENT_API, GLFW.GLFW_NO_API)
    GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
    GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)

    val title = "Kotlin Vulkan for JVM"
    val windowSize = VkExtent2D(300, 300)
    val window = GLFW.glfwCreateWindow(windowSize.width, windowSize.height, title, MemoryUtil.NULL, MemoryUtil.NULL)

    val requiredExtensions = GLFWVulkan.glfwGetRequiredInstanceExtensions()
    checkNotNull(requiredExtensions) { "Failed to find list of required Vulkan extensions" }

    val physicalDeviceLayers = if (isWindows()) listOf("VK_LAYER_LUNARG_standard_validation") else emptyList()
    val physicalDeviceExtensions = requiredExtensions.toStringList()
    val logicalDeviceLayers = listOf<String>()
    val logicalDeviceExtensions = listOf("VK_KHR_swapchain")

    val vk = VK(
            title,
            title,
            physicalDeviceLayers,
            physicalDeviceExtensions,
            logicalDeviceLayers,
            logicalDeviceExtensions,
            windowSize
    ) { surface, instance ->
        createWindowSurface(instance, window, surface)
    }

    val application = Application(vk)
    GLFW.glfwSetFramebufferSizeCallback(window) { _, width, height ->
        application.resize(VkExtent2D(width, height))
    }
    GLFW.glfwShowWindow(window)

    var lastTime = System.currentTimeMillis()
    while (!GLFW.glfwWindowShouldClose(window)) {
        GLFW.glfwPollEvents()

        application.render()
        val now = System.currentTimeMillis()
        val ideal = (lastTime + 1.0 / TARGET_FPS * 1000.0).toLong()
        if (now < ideal) {
            Thread.sleep(ideal - now)
        }
        lastTime = now
    }
    application.destroy()
    vk.destroy()
}