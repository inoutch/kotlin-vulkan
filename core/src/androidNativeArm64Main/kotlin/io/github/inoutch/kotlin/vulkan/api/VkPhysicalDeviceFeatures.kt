package io.github.inoutch.kotlin.vulkan.api

import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kotlinx.cinterop.ptr

@ExperimentalUnsignedTypes
fun VkPhysicalDeviceFeatures.copyToNative(native: vulkan_android.VkPhysicalDeviceFeatures) {
    native.robustBufferAccess = robustBufferAccess.toVkBool32()
    native.fullDrawIndexUint32 = fullDrawIndexUint32.toVkBool32()
    native.imageCubeArray = imageCubeArray.toVkBool32()
    native.independentBlend = independentBlend.toVkBool32()
    native.geometryShader = geometryShader.toVkBool32()
    native.tessellationShader = tessellationShader.toVkBool32()
    native.sampleRateShading = sampleRateShading.toVkBool32()
    native.dualSrcBlend = dualSrcBlend.toVkBool32()
    native.logicOp = logicOp.toVkBool32()
    native.multiDrawIndirect = multiDrawIndirect.toVkBool32()
    native.drawIndirectFirstInstance = drawIndirectFirstInstance.toVkBool32()
    native.depthClamp = depthClamp.toVkBool32()
    native.depthBiasClamp = depthBiasClamp.toVkBool32()
    native.fillModeNonSolid = fillModeNonSolid.toVkBool32()
    native.depthBounds = depthBounds.toVkBool32()
    native.wideLines = wideLines.toVkBool32()
    native.largePoints = largePoints.toVkBool32()
    native.alphaToOne = alphaToOne.toVkBool32()
    native.multiViewport = multiViewport.toVkBool32()
    native.samplerAnisotropy = samplerAnisotropy.toVkBool32()
    native.textureCompressionETC2 = textureCompressionETC2.toVkBool32()
    native.textureCompressionASTC_LDR = textureCompressionASTC_LDR.toVkBool32()
    native.textureCompressionBC = textureCompressionBC.toVkBool32()
    native.occlusionQueryPrecise = occlusionQueryPrecise.toVkBool32()
    native.pipelineStatisticsQuery = pipelineStatisticsQuery.toVkBool32()
    native.vertexPipelineStoresAndAtomics = vertexPipelineStoresAndAtomics.toVkBool32()
    native.fragmentStoresAndAtomics = fragmentStoresAndAtomics.toVkBool32()
    native.shaderTessellationAndGeometryPointSize = shaderTessellationAndGeometryPointSize.toVkBool32()
    native.shaderImageGatherExtended = shaderImageGatherExtended.toVkBool32()
    native.shaderStorageImageExtendedFormats = shaderStorageImageExtendedFormats.toVkBool32()
    native.shaderStorageImageMultisample = shaderStorageImageMultisample.toVkBool32()
    native.shaderStorageImageReadWithoutFormat = shaderStorageImageReadWithoutFormat.toVkBool32()
    native.shaderStorageImageWriteWithoutFormat = shaderStorageImageWriteWithoutFormat.toVkBool32()
    native.shaderUniformBufferArrayDynamicIndexing = shaderUniformBufferArrayDynamicIndexing.toVkBool32()
    native.shaderSampledImageArrayDynamicIndexing = shaderSampledImageArrayDynamicIndexing.toVkBool32()
    native.shaderStorageBufferArrayDynamicIndexing = shaderStorageBufferArrayDynamicIndexing.toVkBool32()
    native.shaderStorageImageArrayDynamicIndexing = shaderStorageImageArrayDynamicIndexing.toVkBool32()
    native.shaderClipDistance = shaderClipDistance.toVkBool32()
    native.shaderCullDistance = shaderCullDistance.toVkBool32()
    native.shaderFloat64 = shaderFloat64.toVkBool32()
    native.shaderInt64 = shaderInt64.toVkBool32()
    native.shaderInt16 = shaderInt16.toVkBool32()
    native.shaderResourceResidency = shaderResourceResidency.toVkBool32()
    native.shaderResourceMinLod = shaderResourceMinLod.toVkBool32()
    native.sparseBinding = sparseBinding.toVkBool32()
    native.sparseResidencyBuffer = sparseResidencyBuffer.toVkBool32()
    native.sparseResidencyImage2D = sparseResidencyImage2D.toVkBool32()
    native.sparseResidencyImage3D = sparseResidencyImage3D.toVkBool32()
    native.sparseResidency2Samples = sparseResidency2Samples.toVkBool32()
    native.sparseResidency4Samples = sparseResidency4Samples.toVkBool32()
    native.sparseResidency8Samples = sparseResidency8Samples.toVkBool32()
    native.sparseResidency16Samples = sparseResidency16Samples.toVkBool32()
    native.sparseResidencyAliased = sparseResidencyAliased.toVkBool32()
    native.variableMultisampleRate = variableMultisampleRate.toVkBool32()
    native.inheritedQueries = inheritedQueries.toVkBool32()
}

@ExperimentalUnsignedTypes
fun VkPhysicalDeviceFeatures.toNative(scope: MemScope) =
        scope.alloc<vulkan_android.VkPhysicalDeviceFeatures>().also { copyToNative(it) }.ptr

@ExperimentalUnsignedTypes
fun vulkan_android.VkPhysicalDeviceFeatures.toOrigin(): VkPhysicalDeviceFeatures {
    return VkPhysicalDeviceFeatures(
            robustBufferAccess.toBoolean(),
            fullDrawIndexUint32.toBoolean(),
            imageCubeArray.toBoolean(),
            independentBlend.toBoolean(),
            geometryShader.toBoolean(),
            tessellationShader.toBoolean(),
            sampleRateShading.toBoolean(),
            dualSrcBlend.toBoolean(),
            logicOp.toBoolean(),
            multiDrawIndirect.toBoolean(),
            drawIndirectFirstInstance.toBoolean(),
            depthBiasClamp.toBoolean(),
            depthBiasClamp.toBoolean(),
            fillModeNonSolid.toBoolean(),
            depthBounds.toBoolean(),
            wideLines.toBoolean(),
            largePoints.toBoolean(),
            alphaToOne.toBoolean(),
            multiViewport.toBoolean(),
            samplerAnisotropy.toBoolean(),
            textureCompressionETC2.toBoolean(),
            textureCompressionASTC_LDR.toBoolean(),
            textureCompressionBC.toBoolean(),
            occlusionQueryPrecise.toBoolean(),
            pipelineStatisticsQuery.toBoolean(),
            vertexPipelineStoresAndAtomics.toBoolean(),
            fragmentStoresAndAtomics.toBoolean(),
            shaderClipDistance.toBoolean(),
            shaderImageGatherExtended.toBoolean(),
            shaderStorageImageExtendedFormats.toBoolean(),
            shaderStorageImageMultisample.toBoolean(),
            shaderStorageImageReadWithoutFormat.toBoolean(),
            shaderStorageImageWriteWithoutFormat.toBoolean(),
            shaderUniformBufferArrayDynamicIndexing.toBoolean(),
            shaderSampledImageArrayDynamicIndexing.toBoolean(),
            shaderStorageBufferArrayDynamicIndexing.toBoolean(),
            shaderStorageImageArrayDynamicIndexing.toBoolean(),
            shaderClipDistance.toBoolean(),
            shaderCullDistance.toBoolean(),
            shaderFloat64.toBoolean(),
            shaderInt16.toBoolean(),
            shaderInt16.toBoolean(),
            shaderResourceResidency.toBoolean(),
            shaderResourceMinLod.toBoolean(),
            sparseBinding.toBoolean(),
            sparseResidencyBuffer.toBoolean(),
            sparseResidencyImage2D.toBoolean(),
            sparseResidencyImage3D.toBoolean(),
            sparseResidency2Samples.toBoolean(),
            sparseResidency4Samples.toBoolean(),
            sparseResidency8Samples.toBoolean(),
            sparseResidency16Samples.toBoolean(),
            sparseResidencyAliased.toBoolean(),
            variableMultisampleRate.toBoolean(),
            inheritedQueries.toBoolean()
    )
}
