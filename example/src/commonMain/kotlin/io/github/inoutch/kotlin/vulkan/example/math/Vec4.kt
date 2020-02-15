package io.github.inoutch.kotlin.vulkan.example.math

import io.github.inoutch.kotlin.vulkan.example.FLOAT_BYTE_SIZE
import kotlin.math.sqrt

data class Vec4(var x: Float, var y: Float, var z: Float, var w: Float) {
    companion object {
        val Zero: Vec4
            get() = Vec4()

        const val SIZE = 4 * FLOAT_BYTE_SIZE

        fun covertHsvToRgb(h: Float, s: Float, v: Float): Vec3 {
            var r = v
            var g = v
            var b = v
            val hh = h * 6.0f

            if (s > 0.0f) {
                val i = hh.toInt()
                val f = hh - i
                when (i) {
                    0 -> {
                        g *= 1 - s * (1 - f)
                        b *= 1 - s
                    }
                    1 -> {
                        r *= 1 - s * f
                        b *= 1 - s
                    }
                    2 -> {
                        r *= 1 - s
                        b *= 1 - s * (1 - f)
                    }
                    3 -> {
                        r *= 1 - s
                        g *= 1 - s * f
                    }
                    4 -> {
                        r *= 1 - s * (1 - f)
                        g *= 1 - s
                    }
                    5 -> {
                        g *= 1 - s
                        b *= 1 - s * f
                    }
                }
                return Vec3(r, g, b)
            }
            return Vec3(v, v, v)
        }
    }

    private constructor() : this(0.0f, 0.0f, 0.0f, 0.0f)

    constructor(v: Float) : this(v, v, v, v)

    constructor(vector3: Vec3, w: Float) : this(vector3.x, vector3.y, vector3.z, w)

    fun length() = sqrt(x * x + y * y + z * z + w * w)

    fun normalized(): Vec4 {
        val len = length()
        return Vec4(x / len, y / len, z / len, w / len)
    }

    fun copyCoordinatesTo(arr: MutableList<Float>) {
        arr.add(x)
        arr.add(y)
        arr.add(z)
        arr.add(w)
    }

    operator fun plus(other: Vec4) = Vec4(x + other.x, y + other.y, z + other.z, w + other.w)

    operator fun plus(other: Float) = Vec4(x + other, y + other, z + other, w + other)

    operator fun minus(other: Vec4) = Vec4(x - other.x, y - other.y, z - other.z, w - other.w)

    operator fun minus(other: Float) = Vec4(x - other, y - other, z - other, w - other)

    operator fun times(other: Vec4) = Vec4(x * other.x, y * other.y, z * other.z, w * other.w)

    operator fun times(other: Float) = Vec4(x * other, y * other, z * other, w * other)

    operator fun div(other: Float) = Vec4(x / other, y / other, z / other, w / other)

    operator fun div(other: Vec4) = Vec4(x / other.x, y / other.y, z / other.z, w / other.w)

    fun flatten() = FloatArray(4).also {
        it[0] = x
        it[1] = y
        it[2] = z
        it[3] = w
    }

    fun toVector2F() = Vec2(x, y)

    fun toVector3F() = Vec3(x, y, z)
}

fun List<Vec4>.flatten() = FloatArray(this.size * 4).also {
    this.forEachIndexed { index, vector ->
        it[index * 4 + 0] = vector.x
        it[index * 4 + 1] = vector.y
        it[index * 4 + 2] = vector.z
        it[index * 4 + 3] = vector.w
    }
}
