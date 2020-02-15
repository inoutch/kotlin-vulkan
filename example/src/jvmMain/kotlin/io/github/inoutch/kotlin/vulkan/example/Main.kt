package io.github.inoutch.kotlin.vulkan.example

import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.createWindowSurface
import io.github.inoutch.kotlin.vulkan.extension.toStringList
import org.lwjgl.glfw.GLFW.GLFW_CLIENT_API
import org.lwjgl.glfw.GLFW.GLFW_FALSE
import org.lwjgl.glfw.GLFW.GLFW_NO_API
import org.lwjgl.glfw.GLFW.GLFW_RESIZABLE
import org.lwjgl.glfw.GLFW.GLFW_TRUE
import org.lwjgl.glfw.GLFW.GLFW_VISIBLE
import org.lwjgl.glfw.GLFW.glfwCreateWindow
import org.lwjgl.glfw.GLFW.glfwDefaultWindowHints
import org.lwjgl.glfw.GLFW.glfwInit
import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback
import org.lwjgl.glfw.GLFW.glfwShowWindow
import org.lwjgl.glfw.GLFW.glfwWindowHint
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose
import org.lwjgl.glfw.GLFWVulkan.glfwGetRequiredInstanceExtensions
import org.lwjgl.glfw.GLFWVulkan.glfwVulkanSupported
import org.lwjgl.system.MemoryUtil

const val TARGET_FPS = 60

fun main(args: Array<String>) {
    check(glfwInit()) { "Failed to initialize GLFW" }
    check(glfwVulkanSupported()) { "Unsupported vulkan" }

    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API)
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

    val title = "Kotlin Vulkan for JVM"
    val windowSize = VkExtent2D(300, 300)
    val window = glfwCreateWindow(windowSize.width, windowSize.height, title, MemoryUtil.NULL, MemoryUtil.NULL)

    val requiredExtensions = glfwGetRequiredInstanceExtensions()
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
    glfwSetFramebufferSizeCallback(window) { _, width, height ->
        application.resize(VkExtent2D(width, height))
    }
    glfwShowWindow(window)

    var lastTime = System.currentTimeMillis()
    while (!glfwWindowShouldClose(window)) {
        glfwPollEvents()

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
