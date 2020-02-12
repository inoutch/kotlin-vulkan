package io.github.inoutch.kotlin.vulkan.utility

expect class MappedMemory(size: Long) {
    val bytesSize: Long

    fun copy(offset: Long, size: Long, array: FloatArray)

    fun copy(offset: Long, size: Long, array: IntArray)

    fun copy(offset: Long, size: Long, array: ByteArray)

    fun destroy()
}
