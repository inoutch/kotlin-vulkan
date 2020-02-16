package io.github.inoutch.kotlin.vulkan.example

import io.github.inoutch.kotlin.vulkan.example.math.Mat4
import io.github.inoutch.kotlin.vulkan.example.math.Vec2
import io.github.inoutch.kotlin.vulkan.example.math.Vec3
import io.github.inoutch.kotlin.vulkan.example.math.flatten
import kotlin.test.Test
import kotlin.test.assertEquals

class TutorialTest {
    @Test
    fun checkSize() {
        assertEquals(4 * 3, Vec3.SIZE)
        assertEquals(4 * 2, Vec2.SIZE)
        assertEquals(4 * 4 * 4, Mat4.SIZE)
        assertEquals(Vec3.SIZE + Vec3.SIZE + Vec2.SIZE, Vertex.SIZE)
        assertEquals(4 * 4 * 4 * 3, UniformBufferObject.SIZE)

        assertEquals(Vec3.SIZE, listOf(Vec3.Zero).flatten().size * FLOAT_BYTE_SIZE)
        assertEquals(Vec2.SIZE, listOf(Vec2.Zero).flatten().size * FLOAT_BYTE_SIZE)
        assertEquals(Mat4.SIZE, Mat4.createIdentity().flatten().size * FLOAT_BYTE_SIZE)
        assertEquals(
                UniformBufferObject.SIZE.toInt(),
                UniformBufferObject(
                        Mat4.createIdentity(),
                        Mat4.createIdentity(),
                        Mat4.createIdentity()
                ).flatten().size * FLOAT_BYTE_SIZE
        )
    }
}
