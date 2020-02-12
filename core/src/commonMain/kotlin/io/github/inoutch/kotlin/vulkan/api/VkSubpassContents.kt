package io.github.inoutch.kotlin.vulkan.api

enum class VkSubpassContents(val value: Int) {
    VK_SUBPASS_CONTENTS_INLINE(0),
    VK_SUBPASS_CONTENTS_SECONDARY_COMMAND_BUFFERS(1),
    VK_SUBPASS_CONTENTS_MAX_ENUM(0x7FFFFFFF),
}
