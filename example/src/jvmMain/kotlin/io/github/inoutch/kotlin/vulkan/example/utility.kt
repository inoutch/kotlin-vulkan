package io.github.inoutch.kotlin.vulkan.example

fun isWindows(): Boolean {
    return System.getProperty("os.name").startsWith("Windows")
}
