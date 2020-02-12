package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkComponentMapping.copyToNative(native: org.lwjgl.vulkan.VkComponentMapping) {
    native.r(r.value)
            .g(g.value)
            .b(b.value)
            .a(a.value)
}

fun VkComponentMapping.toNative(scope: MemScope): org.lwjgl.vulkan.VkComponentMapping =
        scope.add(org.lwjgl.vulkan.VkComponentMapping.calloc()
                .also { copyToNative(it) })
