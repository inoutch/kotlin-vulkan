package io.github.inoutch.kotlin.vulkan.utility

@ExperimentalUnsignedTypes
fun Version.toNative(): UInt {
    return (major.toUInt() shl 22) or (minor.toUInt() shl 12) or (patch.toUInt())
}
