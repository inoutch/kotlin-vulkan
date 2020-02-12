package io.github.inoutch.kotlin.vulkan.extension

inline fun Int.forEachIndexes(action: (index: Int) -> Unit) {
    var index = 0
    while (index < this) {
        action(index)
        index++
    }
}
