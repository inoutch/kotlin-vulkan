package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkSubpassDependency.copyToNative(native: org.lwjgl.vulkan.VkSubpassDependency) {
    native.srcSubpass(srcSubpassIndex)
            .dstSubpass(dstSubpassIndex)
            .srcStageMask(srcStageMask.sumBy { it.bit })
            .dstStageMask(dstStageMask.sumBy { it.bit })
            .srcAccessMask(srcAccessMask.sumBy { it.bit })
            .dstAccessMask(dstAccessMask.sumBy { it.bit })
            .dependencyFlags(dependencyFlags.sumBy { it.bit })
}

fun List<VkSubpassDependency>.toNative(memScope: MemScope) =
        if (isEmpty()) null else memScope.add(org.lwjgl.vulkan.VkSubpassDependency.calloc(size)
                .also { forEachIndexed { index, x -> x.copyToNative(it[index]) } })
