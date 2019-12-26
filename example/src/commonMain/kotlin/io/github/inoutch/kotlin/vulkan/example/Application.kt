package io.github.inoutch.kotlin.vulkan.example

import io.github.inoutch.kotlin.vulkan.api.VK_QUEUE_FAMILY_IGNORED
import io.github.inoutch.kotlin.vulkan.api.VkBlendFactor
import io.github.inoutch.kotlin.vulkan.api.VkClearColorValue
import io.github.inoutch.kotlin.vulkan.api.VkClearDepthStencilValue
import io.github.inoutch.kotlin.vulkan.api.VkClearValue
import io.github.inoutch.kotlin.vulkan.api.VkCullModeFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkDescriptorSetLayout
import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.VkImageAspectFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT
import io.github.inoutch.kotlin.vulkan.api.VkImageLayout.VK_IMAGE_LAYOUT_GENERAL
import io.github.inoutch.kotlin.vulkan.api.VkImageLayout.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR
import io.github.inoutch.kotlin.vulkan.api.VkImageMemoryBarrier
import io.github.inoutch.kotlin.vulkan.api.VkImageSubresourceRange
import io.github.inoutch.kotlin.vulkan.api.VkOffset2D
import io.github.inoutch.kotlin.vulkan.api.VkPipelineBindPoint
import io.github.inoutch.kotlin.vulkan.api.VkPipelineLayout
import io.github.inoutch.kotlin.vulkan.api.VkPipelineLayoutCreateInfo
import io.github.inoutch.kotlin.vulkan.api.VkPipelineStageFlagBits
import io.github.inoutch.kotlin.vulkan.api.VkPolygonMode
import io.github.inoutch.kotlin.vulkan.api.VkRect2D
import io.github.inoutch.kotlin.vulkan.api.VkRenderPassBeginInfo
import io.github.inoutch.kotlin.vulkan.api.VkShaderModule
import io.github.inoutch.kotlin.vulkan.api.VkStructureType
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER
import io.github.inoutch.kotlin.vulkan.api.VkStructureType.VK_STRUCTURE_TYPE_RENDER_PASS_BEGIN_INFO
import io.github.inoutch.kotlin.vulkan.api.VkSubpassContents
import io.github.inoutch.kotlin.vulkan.api.VkViewport
import io.github.inoutch.kotlin.vulkan.api.vk
import io.github.inoutch.kotlin.vulkan.example.shader.triangleFragData
import io.github.inoutch.kotlin.vulkan.example.shader.triangleVertData
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty

class Application(private val vkContext: VK) {
    private val vertShaderModule: VkShaderModule

    private val fragShaderModule: VkShaderModule

    private val destroyers = mutableListOf<() -> Unit>()

    private val descriptorSetLayout: VkDescriptorSetLayout

    init {
        vertShaderModule = checkNotNull(createShaderModule(vkContext.device, triangleVertData))
        destroyers.add { vk.destroyShaderModule(vkContext.device, vertShaderModule) }

        fragShaderModule = checkNotNull(createShaderModule(vkContext.device, triangleFragData))
        destroyers.add { vk.destroyShaderModule(vkContext.device, fragShaderModule) }

        descriptorSetLayout = checkNotNull(createDescriptorSetLayout(vkContext.device, listOf()))
        destroyers.add { vk.destroyDescriptorSetLayout(vkContext.device, descriptorSetLayout) }

        val pipelineLayoutCreateInfo = VkPipelineLayoutCreateInfo(
                VkStructureType.VK_STRUCTURE_TYPE_GRAPHICS_PIPELINE_CREATE_INFO,
                0,
                listOf(descriptorSetLayout),
                listOf()
        )
        val pipelineLayoutRef = MutableProperty<VkPipelineLayout>()
        vk.createPipelineLayout(vkContext.device, pipelineLayoutCreateInfo, pipelineLayoutRef)
        val pipelineLayout = checkNotNull(pipelineLayoutRef.value)
        destroyers.add { vk.destroyPipelineLayout(vkContext.device, pipelineLayout) }

        val viewport = VkViewport(
                0.0f,
                0.0f,
                vkContext.swapchainExtent.width.toFloat(),
                vkContext.swapchainExtent.height.toFloat(),
                0.0f,
                1.0f
        )
        val scissor = VkRect2D(VkOffset2D(0, 0), vkContext.swapchainExtent)
        val graphicsPipeline = checkNotNull(createGraphicsPipeline(
                vkContext.device,
                null,
                pipelineLayout,
                vkContext.renderPass,
                vertShaderModule,
                fragShaderModule,
                viewport,
                scissor,
                false,
                VkCullModeFlagBits.VK_CULL_MODE_FRONT_AND_BACK,
                VkPolygonMode.VK_POLYGON_MODE_FILL,
                VkBlendFactor.VK_BLEND_FACTOR_SRC_ALPHA,
                VkBlendFactor.VK_BLEND_FACTOR_ONE_MINUS_SRC_ALPHA
        ))
        destroyers.add { vk.destroyPipeline(vkContext.device, graphicsPipeline) }

        vkContext.cmd { commandBuffer, framebuffer, swapchainImage ->
            val imageMemoryBarrier = VkImageMemoryBarrier(
                    VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER,
                    listOf(),
                    listOf(),
                    VK_IMAGE_LAYOUT_PRESENT_SRC_KHR,
                    VK_IMAGE_LAYOUT_GENERAL,
                    VK_QUEUE_FAMILY_IGNORED,
                    VK_QUEUE_FAMILY_IGNORED,
                    swapchainImage,
                    VkImageSubresourceRange(listOf(VkImageAspectFlagBits.VK_IMAGE_ASPECT_COLOR_BIT), 0, 1, 0, 1)
            )
            vk.cmdPipelineBarrier(
                    commandBuffer,
                    listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_TRANSFER_BIT),
                    listOf(VkPipelineStageFlagBits.VK_PIPELINE_STAGE_FRAGMENT_SHADER_BIT),
                    listOf(),
                    listOf(),
                    listOf(),
                    listOf(imageMemoryBarrier)
            )
            vk.cmdClearColorImage(
                    commandBuffer,
                    swapchainImage,
                    VK_IMAGE_LAYOUT_GENERAL,
                    VkClearColorValue(1, 0, 0, 1),
                    listOf(VkImageSubresourceRange(listOf(VK_IMAGE_ASPECT_COLOR_BIT), 0, 1, 0, 1))
            )

            val renderPassInfo = VkRenderPassBeginInfo(
                    VK_STRUCTURE_TYPE_RENDER_PASS_BEGIN_INFO,
                    vkContext.renderPass,
                    framebuffer,
                    VkRect2D(VkOffset2D(0, 0), vkContext.swapchainExtent),
                    listOf(VkClearValue(VkClearColorValue(1, 0, 0, 1)), VkClearValue(VkClearDepthStencilValue(0.0f, 0)))
            )
            vk.cmdBeginRenderPass(commandBuffer, renderPassInfo, VkSubpassContents.VK_SUBPASS_CONTENTS_INLINE)

            vk.cmdSetViewport(commandBuffer, 0, listOf(viewport))
            vk.cmdSetScissor(commandBuffer, 0, listOf(scissor))
            vk.cmdBindPipeline(commandBuffer, VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS, graphicsPipeline)
            vk.cmdDraw(commandBuffer, 3, 1, 0, 0)

            vk.cmdEndRenderPass(commandBuffer)
        }
    }

    fun render() {
        vkContext.submit()
    }

    fun resize(windowSize: VkExtent2D) {
        vkContext.resize(windowSize)
    }

    fun destroy() {
        vk.deviceWaitIdle(vkContext.device)
        vkContext.reset()
        destroyers.reversed().forEach { it.invoke() }
    }
}
