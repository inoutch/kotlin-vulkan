package io.github.inoutch.kotlin.vulkan.api

class VkClearColorValue(val array: FloatArray) {

    constructor(x: Int, y: Int, z: Int) : this(floatArrayOf(Float.fromBits(x), Float.fromBits(y), Float.fromBits(z)))

    constructor(x: Float, y: Float, z: Float) : this(floatArrayOf(x, y, z))

    init {
        check(array.size == 4) { "array ize must be 16 bytes" }
    }
}
