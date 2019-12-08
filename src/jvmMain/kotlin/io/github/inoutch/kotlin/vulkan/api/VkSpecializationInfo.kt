package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toNative
import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkSpecializationInfo.copyToNative(
    native: org.lwjgl.vulkan.VkSpecializationInfo,
    scope: MemScope
) {
    native.pMapEntries(mapEntities.toNative(scope))
            .pData(data.toNative(scope))
}

fun VkSpecializationInfo.toNative(scope: MemScope): org.lwjgl.vulkan.VkSpecializationInfo =
        scope.add(org.lwjgl.vulkan.VkSpecializationInfo.calloc()
                .pMapEntries(mapEntities.toNative(scope)))
