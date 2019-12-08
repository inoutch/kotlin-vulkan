package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.extension.toFloatArray
import io.github.inoutch.kotlin.vulkan.extension.toIntArray

@ExperimentalUnsignedTypes
fun vulkan_android.VkPhysicalDeviceLimits.toOrigin() = VkPhysicalDeviceLimits(
        maxImageDimension1D.toInt(),
        maxImageDimension2D.toInt(),
        maxImageDimension3D.toInt(),
        maxImageDimensionCube.toInt(),
        maxImageArrayLayers.toInt(),
        maxTexelBufferElements.toInt(),
        maxUniformBufferRange.toInt(),
        maxStorageBufferRange.toInt(),
        maxPushConstantsSize.toInt(),
        maxMemoryAllocationCount.toInt(),
        maxSamplerAllocationCount.toInt(),
        bufferImageGranularity.toLong(),
        sparseAddressSpaceSize.toLong(),
        maxBoundDescriptorSets.toInt(),
        maxPerStageDescriptorSamplers.toInt(),
        maxPerStageDescriptorUniformBuffers.toInt(),
        maxPerStageDescriptorStorageBuffers.toInt(),
        maxPerStageDescriptorSampledImages.toInt(),
        maxPerStageDescriptorStorageImages.toInt(),
        maxPerStageDescriptorInputAttachments.toInt(),
        maxPerStageResources.toInt(),
        maxDescriptorSetSamplers.toInt(),
        maxDescriptorSetUniformBuffers.toInt(),
        maxDescriptorSetUniformBuffersDynamic.toInt(),
        maxDescriptorSetStorageBuffers.toInt(),
        maxDescriptorSetStorageBuffersDynamic.toInt(),
        maxDescriptorSetSampledImages.toInt(),
        maxDescriptorSetStorageImages.toInt(),
        maxDescriptorSetInputAttachments.toInt(),
        maxVertexInputAttributes.toInt(),
        maxVertexInputBindings.toInt(),
        maxVertexInputAttributeOffset.toInt(),
        maxVertexInputBindingStride.toInt(),
        maxVertexOutputComponents.toInt(),
        maxTessellationGenerationLevel.toInt(),
        maxTessellationPatchSize.toInt(),
        maxTessellationControlPerVertexInputComponents.toInt(),
        maxTessellationControlPerVertexOutputComponents.toInt(),
        maxTessellationControlPerPatchOutputComponents.toInt(),
        maxTessellationControlTotalOutputComponents.toInt(),
        maxTessellationEvaluationInputComponents.toInt(),
        maxTessellationEvaluationOutputComponents.toInt(),
        maxGeometryShaderInvocations.toInt(),
        maxGeometryInputComponents.toInt(),
        maxGeometryOutputComponents.toInt(),
        maxGeometryOutputVertices.toInt(),
        maxGeometryTotalOutputComponents.toInt(),
        maxFragmentInputComponents.toInt(),
        maxFragmentOutputAttachments.toInt(),
        maxFragmentDualSrcAttachments.toInt(),
        maxFragmentCombinedOutputResources.toInt(),
        maxComputeSharedMemorySize.toInt(),
        maxComputeWorkGroupCount.toIntArray(3), // [3]
        maxComputeWorkGroupInvocations.toInt(),
        maxComputeWorkGroupSize.toIntArray(3), // [3]
        subPixelPrecisionBits.toInt(),
        subTexelPrecisionBits.toInt(),
        mipmapPrecisionBits.toInt(),
        maxDrawIndexedIndexValue.toInt(),
        maxDrawIndirectCount.toInt(),
        maxSamplerLodBias,
        maxSamplerAnisotropy,
        maxViewports.toInt(),
        maxViewportDimensions.toIntArray(2), // [2]
        viewportBoundsRange.toFloatArray(2), // [2]
        viewportSubPixelBits.toInt(),
        minMemoryMapAlignment.toLong(),
        minTexelBufferOffsetAlignment.toLong(),
        minUniformBufferOffsetAlignment.toLong(),
        minStorageBufferOffsetAlignment.toLong(),
        minTexelOffset,
        maxTexelOffset.toInt(),
        minTexelGatherOffset,
        maxTexelGatherOffset.toInt(),
        minInterpolationOffset,
        maxInterpolationOffset,
        subPixelInterpolationOffsetBits.toInt(),
        maxFramebufferWidth.toInt(),
        maxFramebufferHeight.toInt(),
        maxFramebufferLayers.toInt(),
        VkSampleCountFlagBits.values().filter { it.bit and framebufferColorSampleCounts.toInt() == it.bit },
        VkSampleCountFlagBits.values().filter { it.bit and framebufferDepthSampleCounts.toInt() == it.bit },
        VkSampleCountFlagBits.values().filter { it.bit and framebufferStencilSampleCounts.toInt() == it.bit },
        VkSampleCountFlagBits.values().filter { it.bit and framebufferNoAttachmentsSampleCounts.toInt() == it.bit },
        maxColorAttachments.toInt(),
        VkSampleCountFlagBits.values().filter { it.bit and sampledImageColorSampleCounts.toInt() == it.bit },
        VkSampleCountFlagBits.values().filter { it.bit and sampledImageIntegerSampleCounts.toInt() == it.bit },
        VkSampleCountFlagBits.values().filter { it.bit and sampledImageDepthSampleCounts.toInt() == it.bit },
        VkSampleCountFlagBits.values().filter { it.bit and sampledImageStencilSampleCounts.toInt() == it.bit },
        VkSampleCountFlagBits.values().filter { it.bit and storageImageSampleCounts.toInt() == it.bit },
        maxSampleMaskWords.toInt(),
        timestampComputeAndGraphics.toBoolean(),
        timestampPeriod,
        maxClipDistances.toInt(),
        maxCullDistances.toInt(),
        maxCombinedClipAndCullDistances.toInt(),
        discreteQueuePriorities.toInt(),
        pointSizeRange.toFloatArray(2), // [2]
        lineWidthRange.toFloatArray(2), // [2]
        pointSizeGranularity,
        lineWidthGranularity,
        strictLines.toBoolean(),
        standardSampleLocations.toBoolean(),
        optimalBufferCopyOffsetAlignment.toLong(),
        optimalBufferCopyRowPitchAlignment.toLong(),
        nonCoherentAtomSize.toLong())
