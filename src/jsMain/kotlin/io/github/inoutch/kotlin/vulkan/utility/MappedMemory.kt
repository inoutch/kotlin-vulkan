package io.github.inoutch.kotlin.vulkan.utility

actual class MappedMemory actual constructor(size: Long) {
    actual val bytesSize: Long
        get() = throw UnsupportedOperationException()

    actual fun copy(offset: Long, size: Long, array: FloatArray) {
        throw UnsupportedOperationException()
    }

    actual fun copy(offset: Long, size: Long, array: IntArray) {
        throw UnsupportedOperationException()
    }

    actual fun copy(offset: Long, size: Long, array: ByteArray) {
        throw UnsupportedOperationException()
    }

    actual fun destroy() {
        throw UnsupportedOperationException()
    }
}
