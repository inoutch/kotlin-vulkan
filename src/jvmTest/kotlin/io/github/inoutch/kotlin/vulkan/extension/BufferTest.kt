package io.github.inoutch.kotlin.vulkan.extension

import java.nio.ByteBuffer
import kotlin.test.Test
import kotlin.test.assertEquals

class BufferTest {
    @Test
    fun byteBufferToByteArray() {
        val byteBuffer = ByteBuffer.allocate(4)
        byteBuffer.put(1.toByte())
        byteBuffer.put(2.toByte())
        byteBuffer.put(4.toByte())
        byteBuffer.put(8.toByte())
        byteBuffer.flip()

        val byteArray = byteBuffer.toByteArray()
        assertEquals(4, byteArray.size)

        assertEquals(1, byteArray[0])
        assertEquals(2, byteArray[1])
        assertEquals(4, byteArray[2])
        assertEquals(8, byteArray[3])
    }
}