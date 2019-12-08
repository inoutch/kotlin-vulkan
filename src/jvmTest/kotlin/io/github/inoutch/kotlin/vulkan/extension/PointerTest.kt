package io.github.inoutch.kotlin.vulkan.extension

import io.github.inoutch.kotlin.vulkan.utility.memScoped
import java.nio.FloatBuffer
import java.nio.IntBuffer
import kotlin.test.Test
import kotlin.test.assertEquals

class PointerTest {
    @Test
    fun stringsToNativeTest() {
        val strings = listOf("kotlin", "vulkan", "test")
        memScoped {
            val native = strings.stringsToNative(this)
            assertEquals(3, native.limit())
            assertEquals("kotlin", native.getStringUTF8(0))
            assertEquals("vulkan", native.getStringUTF8(1))
            assertEquals("test", native.getStringUTF8(2))
        }
    }

    @Test
    fun intBufferToIntArrayTest() {
        val intBuffer = IntBuffer.allocate(4)
        intBuffer.put(1)
        intBuffer.put(2)
        intBuffer.put(4)
        intBuffer.put(8)
        intBuffer.flip()

        val intArray = intBuffer.toIntArray()
        assertEquals(4, intArray.size)
        assertEquals(1, intArray[0])
        assertEquals(2, intArray[1])
        assertEquals(4, intArray[2])
        assertEquals(8, intArray[3])
    }

    @Test
    fun floatBufferToFloatArrayTest() {
        val floatBuffer = FloatBuffer.allocate(4)
        floatBuffer.put(1.0f)
        floatBuffer.put(2.0f)
        floatBuffer.put(4.0f)
        floatBuffer.put(8.0f)

        val floatArray = floatBuffer.toFloatArray()
        assertEquals(4, floatArray.size)
        assertEquals(1.0f, floatArray[0])
        assertEquals(2.0f, floatArray[1])
        assertEquals(4.0f, floatArray[2])
        assertEquals(8.0f, floatArray[3])
    }
}
