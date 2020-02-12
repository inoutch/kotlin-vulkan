package io.github.inoutch.kotlin.vulkan.extension

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CArrayPointer
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.UIntVar
import kotlinx.cinterop.ULongVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.refTo
import kotlinx.cinterop.set
import platform.posix.memcpy
import platform.posix.uint32_tVar

@ExperimentalUnsignedTypes
fun List<UInt>.toNative(scope: MemScope): CArrayPointer<UIntVar> {
    val natives = scope.allocArray<UIntVar>(size)

    forEachIndexed { i, x -> natives[i] = x }

    return natives
}

fun List<Int>.toNative(scope: MemScope): CArrayPointer<IntVar> {
    val natives = scope.allocArray<IntVar>(size)

    forEachIndexed { i, x -> natives[i] = x }

    return natives
}

fun List<Float>.toNative(scope: MemScope): CArrayPointer<FloatVar> {
    val natives = scope.allocArray<FloatVar>(size)
    forEachIndexed { i, x -> natives[i] = x }
    return natives
}

@ExperimentalUnsignedTypes
fun List<ULong>.toNative(scope: MemScope): CArrayPointer<ULongVar> {
    val natives = scope.allocArray<ULongVar>(size)
    forEachIndexed { i, x -> natives[i] = x }
    return natives
}

fun <T : CPointed> List<CPointer<T>>.toNative(scope: MemScope): CArrayPointer<CPointerVar<T>> {
    val natives = scope.allocArray<CPointerVar<T>>(size)
    forEachIndexed { index, x -> natives[index] = x }
    return natives
}

@ExperimentalUnsignedTypes
fun ByteArray.copyToUIntPointer(scope: MemScope): CPointer<UIntVar> {
    val p = scope.allocArray<UIntVar>(size / 2 + 1)
    memcpy(p, refTo(0), size.toULong())
    return p
}

fun CPointer<ByteVar>.toByteArray(size: Int) = ByteArray(size).also {
    for (i in 0 until size) {
        it[i] = this[i]
    }
}

@ExperimentalUnsignedTypes
fun CArrayPointer<uint32_tVar>.toIntArray(size: Int): IntArray {
    val array = IntArray(size)
    size.forEachIndexes { array[it] = this[it].toInt() }
    return array
}

fun CArrayPointer<FloatVar>.toFloatArray(size: Int): FloatArray {
    val array = FloatArray(size)
    size.forEachIndexes { array[it] = this[it] }
    return array
}
