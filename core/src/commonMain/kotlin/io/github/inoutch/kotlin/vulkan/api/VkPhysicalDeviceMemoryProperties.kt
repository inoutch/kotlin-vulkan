package io.github.inoutch.kotlin.vulkan.api

class VkPhysicalDeviceMemoryProperties(
    val memoryTypes: List<VkMemoryType>,
    val memoryHeaps: List<VkMemoryHeap>
) {
    fun findMemoryTypeIndex(
        memoryRequirements: VkMemoryRequirements,
        memoryProperties: List<VkMemoryPropertyFlagBits>
    ): Int? {
        val bits = memoryProperties.sumBy { it.bit }
        var i = 0
        while (i < memoryTypes.size) {
            val type = memoryTypes[i]
            val typeBits = type.propertyFlags.sumBy { it.bit }
            if (memoryRequirements.memoryTypeBits and (1 shl i) != 0 && typeBits and bits != 0) {
                return i
            }
            i++
        }
        return null
    }
}
