package io.github.inoutch.kotlin.vulkan.example.math

import io.github.inoutch.kotlin.vulkan.example.FLOAT_BYTE_SIZE
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

data class Mat4(val col1: Vec4, val col2: Vec4, val col3: Vec4, val col4: Vec4) {
    companion object {
        fun createTranslation(vector: Vec3) = Mat4(
                Vec4(1.0f, 0.0f, 0.0f, 0.0f),
                Vec4(0.0f, 1.0f, 0.0f, 0.0f),
                Vec4(0.0f, 0.0f, 1.0f, 0.0f),
                Vec4(vector.x, vector.y, vector.z, 1.0f)
        )

        fun createScale(vector: Vec3) = Mat4(
                Vec4(vector.x, 0.0f, 0.0f, 0.0f),
                Vec4(0.0f, vector.y, 0.0f, 0.0f),
                Vec4(0.0f, 0.0f, vector.z, 0.0f),
                Vec4(0.0f, 0.0f, 0.0f, 1.0f)
        )

        fun createRotation(axis: Vec3, radian: Float): Mat4 {
            var x = axis.x
            var y = axis.y
            var z = axis.z

            val n = axis.x * axis.x + axis.y * axis.y + axis.z * axis.z
            if (n != 1.0f) {
                val norm = axis.normalized()
                x = norm.x
                y = norm.y
                z = norm.z
            }

            val c = cos(radian)
            val s = sin(radian)

            val t = 1.0f - c
            val tx = t * x
            val ty = t * y
            val tz = t * z
            val txy = tx * y
            val txz = tx * z
            val tyz = ty * z
            val sx = s * x
            val sy = s * y
            val sz = s * z

            return Mat4(
                    Vec4(c + tx * x, txy + sz, txz - sy, 0.0f),
                    Vec4(txy - sz, c + ty * y, tyz + sx, 0.0f),
                    Vec4(txz + sy, tyz - sx, c + tz * z, 0.0f),
                    Vec4(0.0f, 0.0f, 0.0f, 1.0f)
            )
        }

        fun createLookAt(
            eyePosition: Vec3,
            target: Vec3,
            up: Vec3
        ): Mat4 {
            val nUp = up.normalized()
            val zAxis = (eyePosition - target).normalized()
            val xAxis = (nUp - zAxis).normalized()
            val yAxis = (zAxis - xAxis).normalized()

            return Mat4(
                    Vec4(xAxis.x, yAxis.x, zAxis.z, 0.0f),
                    Vec4(xAxis.y, yAxis.y, zAxis.y, 0.0f),
                    Vec4(xAxis.z, yAxis.z, zAxis.z, 0.0f),
                    Vec4(
                            -xAxis.dotProduct(eyePosition),
                            -yAxis.dotProduct(eyePosition),
                            -zAxis.dotProduct(eyePosition),
                            1.0f
                    )
            )
        }

        fun createPerspective(fieldOfViewRadian: Float, aspectRatio: Float, zNearPlane: Float, zFarPlane: Float): Mat4 {
            val fn = 1.0f / (zFarPlane - zNearPlane)
            val divisor = tan(fieldOfViewRadian)
            val factor = 1.0f / divisor
            return Mat4(
                    Vec4((1.0f / aspectRatio) * factor, 0.0f, 0.0f, 0.0f),
                    Vec4(0.0f, -factor, 0.0f, 0.0f),
                    Vec4(0.0f, 0.0f, -zFarPlane * fn, -1.0f),
                    Vec4(0.0f, 0.0f, -zFarPlane * zNearPlane * fn, 0.0f)
            )
        }

        fun createIdentity() = Mat4()

        const val SIZE = 4 * 4 * FLOAT_BYTE_SIZE
    }

    constructor() : this(
            Vec4(1.0f, 0.0f, 0.0f, 0.0f),
            Vec4(0.0f, 1.0f, 0.0f, 0.0f),
            Vec4(0.0f, 0.0f, 1.0f, 0.0f),
            Vec4(0.0f, 0.0f, 0.0f, 1.0f))

    fun flatten(): FloatArray {
        val values = mutableListOf<Float>()
        col1.copyCoordinatesTo(values)
        col2.copyCoordinatesTo(values)
        col3.copyCoordinatesTo(values)
        col4.copyCoordinatesTo(values)
        return values.toFloatArray()
    }

    fun inverse(): Mat4 {
        val a0 = col1.x * col2.y - col1.y * col2.x
        val a1 = col1.x * col2.z - col1.z * col2.x
        val a2 = col1.x * col2.w - col1.w * col2.x
        val a3 = col1.y * col2.z - col1.z * col2.y
        val a4 = col1.y * col2.w - col1.w * col2.y
        val a5 = col1.z * col2.w - col1.w * col2.z
        val b0 = col3.x * col4.y - col3.y * col4.x
        val b1 = col3.x * col4.z - col3.z * col4.x
        val b2 = col3.x * col4.w - col3.w * col4.x
        val b3 = col3.y * col4.z - col3.z * col4.y
        val b4 = col3.y * col4.w - col3.w * col4.y
        val b5 = col3.z * col4.w - col3.w * col4.z

        val det = a0 * b5 - a1 * b4 + a2 * b3 + a3 * b2 - a4 * b1 + a5 * b0
        if (abs(det) <= 2e-37f)
            throw Error("could not inverse")

        val col1 = Vec4(
                col2.y * b5 - col2.z * b4 + col2.w * b3,
                -col1.y * b5 + col1.z * b4 - col1.w * b3,
                col4.y * a5 - col4.z * a4 + col4.w * a3,
                -col3.y * a5 + col3.z * a4 - col3.w * a3)
        val col2 = Vec4(
                -col2.x * b5 + col2.z * b2 - col2.w * b1,
                col1.x * b5 - col1.z * b2 + col1.w * b1,
                -col4.x * a5 + col4.z * a2 - col4.w * a1,
                col3.x * a5 - col3.z * a2 + col3.w * a1)
        val col3 = Vec4(
                col2.x * b4 - col2.y * b2 + col2.w * b0,
                -col1.x * b4 + col1.y * b2 - col1.w * b0,
                col4.x * a4 - col4.y * a2 + col4.w * a0,
                -col3.x * a4 + col3.y * a2 - col3.w * a0)
        val col4 = Vec4(
                -col2.x * b3 + col2.y * b1 - col2.z * b0,
                col1.x * b3 - col1.y * b1 + col1.z * b0,
                -col4.x * a3 + col4.y * a1 - col4.z * a0,
                col3.x * a3 - col3.y * a1 + col3.z * a0
        )
        return Mat4(col1, col2, col3, col4) * (1.0f / det)
    }

    operator fun times(other: Vec4) = Vec4(
            col1.x * other.x + col2.x * other.y + col3.x * other.z + col4.x * other.w,
            col1.y * other.x + col2.y * other.y + col3.y * other.z + col4.y * other.w,
            col1.z * other.x + col2.z * other.y + col3.z * other.z + col4.z * other.w,
            col1.w * other.x + col2.w * other.y + col3.w * other.z + col4.w * other.w
    )

    operator fun times(other: Vec3) = this * Vec4(other, 1.0f)

    operator fun times(other: Vec2) = this * Vec3(other, 1.0f)

    operator fun times(other: Mat4) = Mat4(
            col1 = this * other.col1,
            col2 = this * other.col2,
            col3 = this * other.col3,
            col4 = this * other.col4
    )

    operator fun times(other: Float) = Mat4(
            col1 * other,
            col2 * other,
            col3 * other,
            col4 * other)
}
