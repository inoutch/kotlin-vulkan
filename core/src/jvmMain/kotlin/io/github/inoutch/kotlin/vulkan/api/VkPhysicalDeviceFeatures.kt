package io.github.inoutch.kotlin.vulkan.api

import io.github.inoutch.kotlin.vulkan.utility.MemScope

fun VkPhysicalDeviceFeatures.copyToNative(native: org.lwjgl.vulkan.VkPhysicalDeviceFeatures) {
    native.robustBufferAccess(robustBufferAccess)
            .fullDrawIndexUint32(fullDrawIndexUint32)
            .imageCubeArray(imageCubeArray)
            .independentBlend(independentBlend)
            .geometryShader(geometryShader)
            .tessellationShader(tessellationShader)
            .sampleRateShading(sampleRateShading)
            .dualSrcBlend(dualSrcBlend)
            .logicOp(logicOp)
            .multiDrawIndirect(multiDrawIndirect)
            .drawIndirectFirstInstance(drawIndirectFirstInstance)
            .depthClamp(depthClamp)
            .fillModeNonSolid(fillModeNonSolid)
            .depthBounds(depthBounds)
            .wideLines(wideLines)
            .largePoints(largePoints)
            .alphaToOne(alphaToOne)
            .multiViewport(multiViewport)
            .samplerAnisotropy(samplerAnisotropy)
            .textureCompressionETC2(textureCompressionETC2)
            .textureCompressionASTC_LDR(textureCompressionASTC_LDR)
            .textureCompressionBC(textureCompressionBC)
            .occlusionQueryPrecise(occlusionQueryPrecise)
            .pipelineStatisticsQuery(pipelineStatisticsQuery)
            .vertexPipelineStoresAndAtomics(vertexPipelineStoresAndAtomics)
            .fragmentStoresAndAtomics(fragmentStoresAndAtomics)
            .shaderTessellationAndGeometryPointSize(shaderTessellationAndGeometryPointSize)
            .shaderImageGatherExtended(shaderImageGatherExtended)
            .shaderStorageImageExtendedFormats(shaderStorageImageExtendedFormats)
            .shaderStorageImageMultisample(shaderStorageImageMultisample)
            .shaderStorageImageReadWithoutFormat(shaderStorageImageReadWithoutFormat)
            .shaderStorageImageWriteWithoutFormat(shaderStorageImageWriteWithoutFormat)
            .shaderUniformBufferArrayDynamicIndexing(shaderUniformBufferArrayDynamicIndexing)
            .shaderSampledImageArrayDynamicIndexing(shaderSampledImageArrayDynamicIndexing)
            .shaderStorageBufferArrayDynamicIndexing(shaderStorageBufferArrayDynamicIndexing)
            .shaderStorageImageArrayDynamicIndexing(shaderStorageImageArrayDynamicIndexing)
            .shaderCullDistance(shaderCullDistance)
            .shaderFloat64(shaderFloat64)
            .shaderInt64(shaderInt64)
            .shaderInt16(shaderInt16)
            .shaderResourceResidency(shaderResourceResidency)
            .shaderResourceMinLod(shaderResourceMinLod)
            .sparseBinding(sparseBinding)
            .sparseResidencyBuffer(sparseResidencyBuffer)
            .sparseResidencyImage2D(sparseResidencyImage2D)
            .sparseResidencyImage3D(sparseResidencyImage3D)
            .sparseResidency2Samples(sparseResidency2Samples)
            .sparseResidency4Samples(sparseResidency4Samples)
            .sparseResidency8Samples(sparseResidency8Samples)
            .sparseResidency16Samples(sparseResidency16Samples)
            .sparseResidencyAliased(sparseResidencyAliased)
            .variableMultisampleRate(variableMultisampleRate)
            .inheritedQueries(inheritedQueries)
}

fun VkPhysicalDeviceFeatures.toNative(memScope: MemScope): org.lwjgl.vulkan.VkPhysicalDeviceFeatures =
        memScope.add(org.lwjgl.vulkan.VkPhysicalDeviceFeatures.calloc().also { copyToNative(it) })

fun org.lwjgl.vulkan.VkPhysicalDeviceFeatures.toOrigin(): VkPhysicalDeviceFeatures {
    return VkPhysicalDeviceFeatures(
            robustBufferAccess(),
            fullDrawIndexUint32(),
            imageCubeArray(),
            independentBlend(),
            geometryShader(),
            tessellationShader(),
            sampleRateShading(),
            dualSrcBlend(),
            logicOp(),
            multiDrawIndirect(),
            drawIndirectFirstInstance(),
            depthBiasClamp(),
            depthBiasClamp(),
            fillModeNonSolid(),
            depthBounds(),
            wideLines(),
            largePoints(),
            alphaToOne(),
            multiViewport(),
            samplerAnisotropy(),
            textureCompressionETC2(),
            textureCompressionASTC_LDR(),
            textureCompressionBC(),
            occlusionQueryPrecise(),
            pipelineStatisticsQuery(),
            vertexPipelineStoresAndAtomics(),
            fragmentStoresAndAtomics(),
            shaderClipDistance(),
            shaderImageGatherExtended(),
            shaderStorageImageExtendedFormats(),
            shaderStorageImageMultisample(),
            shaderStorageImageReadWithoutFormat(),
            shaderStorageImageWriteWithoutFormat(),
            shaderUniformBufferArrayDynamicIndexing(),
            shaderSampledImageArrayDynamicIndexing(),
            shaderStorageBufferArrayDynamicIndexing(),
            shaderStorageImageArrayDynamicIndexing(),
            shaderClipDistance(),
            shaderCullDistance(),
            shaderFloat64(),
            shaderInt16(),
            shaderInt16(),
            shaderResourceResidency(),
            shaderResourceMinLod(),
            sparseBinding(),
            sparseResidencyBuffer(),
            sparseResidencyImage2D(),
            sparseResidencyImage3D(),
            sparseResidency2Samples(),
            sparseResidency4Samples(),
            sparseResidency8Samples(),
            sparseResidency16Samples(),
            sparseResidencyAliased(),
            variableMultisampleRate(),
            inheritedQueries()
    )
}
