package io.github.inoutch.kotlin.vulkan.example.math

import io.github.inoutch.kotlin.vulkan.example.FLOAT_BYTE_SIZE
import kotlin.math.sqrt

data class Vec2(var x: Float, var y: Float) {
    companion object {
        val Zero: Vec2
            get() = Vec2()

        const val SIZE = 2 * FLOAT_BYTE_SIZE
    }

    private constructor() : this(0.0f, 0.0f)

    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())

    constructor(vector: Vec3) : this(vector.x, vector.y)

    fun length() = sqrt(x * x + y * y)

    fun normalized(): Vec2 {
        val len = length()
        return Vec2(x / len, y / len)
    }

    fun copyCoordinatesTo(arr: MutableList<Float>) {
        arr.add(x)
        arr.add(y)
    }

    fun dotProduct(other: Vec2) = x * other.x + y * other.y

    fun inRange(p: Vec2) = 0 <= p.x && 0 <= p.y && p.x < x && p.y < y

    operator fun minus(other: Vec2) = Vec2(x - other.x, y - other.y)

    operator fun minus(other: Float) = Vec2(x - other, y - other)

    operator fun plus(other: Vec2) = Vec2(x + other.x, y + other.y)

    operator fun plus(other: Float) = Vec2(x + other, y + other)

    operator fun times(other: Float) = Vec2(x * other, y * other)

    operator fun times(other: Vec2) = Vec2(x * other.x, y * other.y)

    operator fun div(other: Float) = Vec2(x / other, y / other)

    operator fun div(other: Vec2) = Vec2(x / other.x, y / other.y)

    fun toVector3F(z: Float = 0.0f) = Vec3(x, y, z)
}

fun List<Vec2>.flatten() = FloatArray(this.size * 2).also {
    this.forEachIndexed { index, vector ->
        it[index * 2 + 0] = vector.x
        it[index * 2 + 1] = vector.y
    }
}
