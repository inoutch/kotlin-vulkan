package io.github.inoutch.kotlin.vulkan.utility

import kotlin.test.Test
import kotlin.test.assertTrue

@ExperimentalUnsignedTypes
class UIntTest {
    @Test
    fun bitTest() {
        val a = 0b00100
        val b = 0b00100u
        assertTrue { a and b.toInt() == a }
        assertTrue { a.toUInt() and b == b }
    }
}
