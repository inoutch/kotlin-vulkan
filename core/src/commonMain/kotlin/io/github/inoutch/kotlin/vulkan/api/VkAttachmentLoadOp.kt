package io.github.inoutch.kotlin.vulkan.api

enum class VkAttachmentLoadOp(val value: Int) {
    VK_ATTACHMENT_LOAD_OP_LOAD(0),
    VK_ATTACHMENT_LOAD_OP_CLEAR(1),
    VK_ATTACHMENT_LOAD_OP_DONT_CARE(2),
    VK_ATTACHMENT_LOAD_OP_MAX_ENUM(0x7FFFFFFF)
}
