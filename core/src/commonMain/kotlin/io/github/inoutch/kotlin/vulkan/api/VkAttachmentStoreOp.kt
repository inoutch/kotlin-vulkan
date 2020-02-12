package io.github.inoutch.kotlin.vulkan.api

enum class VkAttachmentStoreOp(val value: Int) {
    VK_ATTACHMENT_STORE_OP_STORE(0),
    VK_ATTACHMENT_STORE_OP_DONT_CARE(1),
    VK_ATTACHMENT_STORE_OP_MAX_ENUM(0x7FFFFFFF),
}
