package io.github.inoutch.kotlin.vulkan.api

class VkClearValue(val color: VkClearColorValue?, val depthStencil: VkClearDepthStencilValue?) {

    constructor(color: VkClearColorValue) : this(color, null)

    constructor(depthStencil: VkClearDepthStencilValue) : this(null, depthStencil)
}
