package io.github.inoutch.kotlin.vulkan.api

@ExperimentalUnsignedTypes
fun VkStencilOpState.copyToNative(native: vulkan_android.VkStencilOpState) {
    native.failOp = failOp.value.toUInt()
    native.passOp = passOp.value.toUInt()
    native.depthFailOp = depthFailOp.value.toUInt()
    native.compareOp = compareOp.value.toUInt()
    native.compareMask = compareMask.toUInt()
    native.writeMask = writeMask.toUInt()
    native.reference = reference.toUInt()
}
