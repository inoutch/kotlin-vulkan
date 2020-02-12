package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun Boolean.toVkBool32() = if (this) 1u else 0u

@ExperimentalUnsignedTypes
fun UInt.toBoolean() = this != 0u
