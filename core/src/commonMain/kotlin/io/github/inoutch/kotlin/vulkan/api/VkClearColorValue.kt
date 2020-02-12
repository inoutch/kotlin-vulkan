package io.github.inoutch.kotlin.vulkan.api

class VkClearColorValue(val array: FloatArray) {

    constructor(x: Int, y: Int, z: Int, w: Int) : this(floatArrayOf(Float.fromBits(x), Float.fromBits(y), Float.fromBits(z), Float.fromBits(w)))

    constructor(x: Float, y: Float, z: Float, w: Float) : this(floatArrayOf(x, y, z, w))

    init {
        check(array.size == 4) { "array size must be 16 bytes" }
    }
}
