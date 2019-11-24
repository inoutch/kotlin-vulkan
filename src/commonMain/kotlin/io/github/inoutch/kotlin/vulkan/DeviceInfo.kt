package io.github.inoutch.kotlin.vulkan

expect class DeviceInfo {
    companion object {
        fun isSupported(): Boolean
    }
}
