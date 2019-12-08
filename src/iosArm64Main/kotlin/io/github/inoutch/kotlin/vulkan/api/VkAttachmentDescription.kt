package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get

@ExperimentalUnsignedTypes
fun VkAttachmentDescription.copyToNative(native: vulkan_ios.VkAttachmentDescription) {
    native.flags = flags.sumBy { it.bit }.toUInt()
    native.format = format.value.toUInt()
    native.samples = samples.sumBy { it.bit }.toUInt()
    native.loadOp = loadOp.value.toUInt()
    native.storeOp = storeOp.value.toUInt()
    native.stencilLoadOp = stencilLoadOp.value.toUInt()
    native.stencilStoreOp = stencilStoreOp.value.toUInt()
    native.initialLayout = initialLayout.value.toUInt()
    native.finalLayout = finalLayout.value.toUInt()
}

@ExperimentalUnsignedTypes
fun VkAttachmentDescription.toNative(scope: MemScope) =
        scope.alloc<vulkan_ios.VkAttachmentDescription>().also { copyToNative(it) }

@ExperimentalUnsignedTypes
fun List<VkAttachmentDescription>.toNative(scope: MemScope) =
        if (isEmpty()) null else scope.allocArray<vulkan_ios.VkAttachmentDescription>(size).also {
            forEachIndexed { index, x -> x.copyToNative(it[index]) }
        }
