package io.github.inoutch.kotlin.vulkan.example

import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.VkInstance
import io.github.inoutch.kotlin.vulkan.api.VkResult
import io.github.inoutch.kotlin.vulkan.api.VkSurface
import io.github.inoutch.kotlin.vulkan.api.createWindowSurface
import io.github.inoutch.kotlin.vulkan.extension.toStringList
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty
import org.lwjgl.glfw.GLFW.GLFW_CLIENT_API
import org.lwjgl.glfw.GLFW.GLFW_FALSE
import org.lwjgl.glfw.GLFW.GLFW_NO_API
import org.lwjgl.glfw.GLFW.GLFW_RESIZABLE
import org.lwjgl.glfw.GLFW.GLFW_TRUE
import org.lwjgl.glfw.GLFW.GLFW_VISIBLE
import org.lwjgl.glfw.GLFW.glfwCreateWindow
import org.lwjgl.glfw.GLFW.glfwDefaultWindowHints
import org.lwjgl.glfw.GLFW.glfwGetFramebufferSize
import org.lwjgl.glfw.GLFW.glfwInit
import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwShowWindow
import org.lwjgl.glfw.GLFW.glfwWindowHint
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose
import org.lwjgl.glfw.GLFWVulkan.glfwGetRequiredInstanceExtensions
import org.lwjgl.glfw.GLFWVulkan.glfwVulkanSupported
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil

fun tutorialMain(args: Array<String>) {
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

    val app = HelloTriangleApplication(
            object : HelloTriangleApplication.Delegate {
                override fun getSwapchainExtensionNames(): List<String> {
                    return listOf("VK_KHR_swapchain")
                }

                override fun createSurface(instance: VkInstance, surface: MutableProperty<VkSurface>): VkResult {
                    return createWindowSurface(instance, window, surface)
                }

                override fun getRequiredInstanceExtensions(): List<String> {
                    return requiredExtensions.toStringList()
                }

                override fun getFramebufferSize(width: MutableProperty<Int>, height: MutableProperty<Int>) {
                    stackPush().use {
                        val widthBuffer = it.callocInt(1)
                        val heightBuffer = it.callocInt(1)
                        glfwGetFramebufferSize(window, widthBuffer, heightBuffer)
                        width set widthBuffer[0]
                        height set heightBuffer[0]
                    }
                }

                override fun destroy() {}
            }
    )

    app.initVulkan()
    glfwShowWindow(window)

    var lastTime = System.currentTimeMillis()
    while (!glfwWindowShouldClose(window)) {
        glfwPollEvents()

        app.drawFrame()
        val now = System.currentTimeMillis()
        val ideal = (lastTime + 1.0 / TARGET_FPS * 1000.0).toLong()
        if (now < ideal) {
            Thread.sleep(ideal - now)
        }
        lastTime = now
    }
    app.cleanup()
}
