package io.github.inoutch.kotlin.vulkan.utility

import io.github.inoutch.kotlin.vulkan.api.VkDevice
import io.github.inoutch.kotlin.vulkan.api.VkDeviceMemory
import io.github.inoutch.kotlin.vulkan.constant.FLOAT_SIZE
import io.github.inoutch.kotlin.vulkan.constant.INT_SIZE
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.usePinned
import platform.posix.memcpy

actual class MappedMemory actual constructor(size: Long) {
    actual val bytesSize = size

    lateinit var native: COpaquePointer

    private lateinit var device: VkDevice

    private lateinit var memory: VkDeviceMemory

    @ExperimentalUnsignedTypes
    actual fun copy(offset: Long, size: Long, array: FloatArray) = memScoped {
        if (size <= 0) {
            return@memScoped
        }
        array.usePinned {
            val p = (native.rawValue.toLong() + offset * FLOAT_SIZE).toCPointer<FloatVar>()
            memcpy(p, it.addressOf(0), (size * FLOAT_SIZE).toULong())
        }
        Unit
    }

    @ExperimentalUnsignedTypes
    actual fun copy(offset: Long, size: Long, array: IntArray) = memScoped {
        array.usePinned {
            val p = (native.rawValue.toLong() + offset * INT_SIZE).toCPointer<IntVar>()
            memcpy(p, it.addressOf(0), (size * INT_SIZE).toULong())
        }
        Unit
    }

    @ExperimentalUnsignedTypes
    actual fun copy(offset: Long, size: Long, array: ByteArray) = memScoped {
        array.usePinned {
            val p = (native.rawValue.toLong() + offset).toCPointer<ByteVar>()
            memcpy(p, it.addressOf(0), size.toULong())
        }
        Unit
    }

    actual fun destroy() {
        vulkan_android.vkUnmapMemory(device.native, memory.native)
    }

    fun init(nativeMappedMemory: COpaquePointer, device: VkDevice, memory: VkDeviceMemory) {
        this.native = nativeMappedMemory
        this.device = device
        this.memory = memory
    }
}
