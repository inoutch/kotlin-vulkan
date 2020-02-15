package io.github.inoutch.kotlin.vulkan.example.math

import io.github.inoutch.kotlin.vulkan.example.FLOAT_BYTE_SIZE
import kotlin.math.sqrt

data class Vec3(var x: Float, var y: Float, var z: Float) {
    companion object {
        val Zero: Vec3
            get() = Vec3(0.0f, 0.0f, 0.0f)

        const val SIZE = 3 * FLOAT_BYTE_SIZE
    }

    private constructor() : this(0.0f, 0.0f, 0.0f)

    constructor(v: Float) : this(v, v, v)

    constructor(vector2: Vec2, z: Float) : this(vector2.x, vector2.y, z)

    fun length() = sqrt(x * x + y * y + z * z)

    fun normalized(): Vec3 {
        val len = length()
        return Vec3(x / len, y / len, z / len)
    }

    fun copyCoordinatesTo(arr: MutableList<Float>) {
        arr.add(x)
        arr.add(y)
        arr.add(z)
    }

    fun dotProduct(other: Vec3) = x * other.x + y * other.y + z * other.z

    fun crossProduct(other: Vec3): Vec3 =
            Vec3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

    operator fun minus(other: Vec3) = Vec3(x - other.x, y - other.y, z - other.z)

    operator fun plus(other: Vec3) = Vec3(x + other.x, y + other.y, z + other.z)

    operator fun times(other: Float) = Vec3(x * other, y * other, z * other)

    operator fun times(other: Vec3) = Vec3(x * other.x, y * other.y, z * other.z)

    operator fun div(other: Vec3) = Vec3(x / other.x, y / other.y, z / other.z)

    operator fun div(other: Float) = Vec3(x / other, y / other, z / other)
}

fun List<Vec3>.flatten() = FloatArray(this.size * 3).also {
    this.forEachIndexed { index, vector ->
        it[index * 3 + 0] = vector.x
        it[index * 3 + 1] = vector.y
        it[index * 3 + 2] = vector.z
    }
}
