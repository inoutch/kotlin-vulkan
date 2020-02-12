package io.github.inoutch.kotlin.vulkan.utility

class MutableProperty<T> {
    var value: T? = null
        private set

    infix fun set(other: T) {
        value = other
    }
}
