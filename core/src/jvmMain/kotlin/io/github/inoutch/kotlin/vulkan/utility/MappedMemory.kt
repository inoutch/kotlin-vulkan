package io.github.inoutch.kotlin.vulkan.utility

import io.github.inoutch.kotlin.vulkan.api.VkDevice
import io.github.inoutch.kotlin.vulkan.api.VkDeviceMemory
import io.github.inoutch.kotlin.vulkan.constant.FLOAT_SIZE
import io.github.inoutch.kotlin.vulkan.constant.INT_SIZE
import io.github.inoutch.kotlin.vulkan.extension.toNative
import org.lwjgl.system.MemoryUtil
import org.lwjgl.vulkan.VK10

actual class MappedMemory actual constructor(size: Long) {

    actual val bytesSize = size

    var native: Long = 0

    private lateinit var device: VkDevice

    private lateinit var memory: VkDeviceMemory

    fun init(native: Long, device: VkDevice, memory: VkDeviceMemory) {
        this.native = native
        this.device = device
        this.memory = memory
    }

    actual fun destroy() {
        VK10.vkUnmapMemory(device.native, memory.native)
    }

    actual fun copy(offset: Long, size: Long, array: FloatArray) = memScoped {
        check(offset * FLOAT_SIZE + size * FLOAT_SIZE <= bytesSize) {
            "Access outside the allocated memory range"
        }
        val buffer = array.toNative(this)
        MemoryUtil.memCopy(MemoryUtil.memAddress(buffer), native + offset * FLOAT_SIZE, size * FLOAT_SIZE)
    }

    actual fun copy(offset: Long, size: Long, array: IntArray) = memScoped {
        check(offset * INT_SIZE + size * INT_SIZE <= bytesSize) {
            "Access outside the allocated memory range"
        }
        val buffer = array.toNative(this)
        MemoryUtil.memCopy(MemoryUtil.memAddress(buffer), native + offset * INT_SIZE, size * INT_SIZE)
    }

    actual fun copy(offset: Long, size: Long, array: ByteArray) = memScoped {
        check(offset + size <= bytesSize) {
            "Access outside the allocated memory range"
        }
        val buffer = array.toNative(this)
        MemoryUtil.memCopy(MemoryUtil.memAddress(buffer), native + offset, size)
    }
}
