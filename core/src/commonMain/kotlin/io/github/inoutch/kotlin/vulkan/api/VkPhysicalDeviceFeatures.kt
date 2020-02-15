package io.github.inoutch.kotlin.vulkan.api

class VkPhysicalDeviceFeatures(
    val robustBufferAccess: Boolean = false,
    val fullDrawIndexUint32: Boolean = false,
    val imageCubeArray: Boolean = false,
    val independentBlend: Boolean = false,
    val geometryShader: Boolean = false,
    val tessellationShader: Boolean = false,
    val sampleRateShading: Boolean = false,
    val dualSrcBlend: Boolean = false,
    val logicOp: Boolean = false,
    val multiDrawIndirect: Boolean = false,
    val drawIndirectFirstInstance: Boolean = false,
    val depthClamp: Boolean = false,
    val depthBiasClamp: Boolean = false,
    val fillModeNonSolid: Boolean = false,
    val depthBounds: Boolean = false,
    val wideLines: Boolean = false,
    val largePoints: Boolean = false,
    val alphaToOne: Boolean = false,
    val multiViewport: Boolean = false,
    val samplerAnisotropy: Boolean = false,
    val textureCompressionETC2: Boolean = false,
    val textureCompressionASTC_LDR: Boolean = false,
    val textureCompressionBC: Boolean = false,
    val occlusionQueryPrecise: Boolean = false,
    val pipelineStatisticsQuery: Boolean = false,
    val vertexPipelineStoresAndAtomics: Boolean = false,
    val fragmentStoresAndAtomics: Boolean = false,
    val shaderTessellationAndGeometryPointSize: Boolean = false,
    val shaderImageGatherExtended: Boolean = false,
    val shaderStorageImageExtendedFormats: Boolean = false,
    val shaderStorageImageMultisample: Boolean = false,
    val shaderStorageImageReadWithoutFormat: Boolean = false,
    val shaderStorageImageWriteWithoutFormat: Boolean = false,
    val shaderUniformBufferArrayDynamicIndexing: Boolean = false,
    val shaderSampledImageArrayDynamicIndexing: Boolean = false,
    val shaderStorageBufferArrayDynamicIndexing: Boolean = false,
    val shaderStorageImageArrayDynamicIndexing: Boolean = false,
    val shaderClipDistance: Boolean = false,
    val shaderCullDistance: Boolean = false,
    val shaderFloat64: Boolean = false,
    val shaderInt64: Boolean = false,
    val shaderInt16: Boolean = false,
    val shaderResourceResidency: Boolean = false,
    val shaderResourceMinLod: Boolean = false,
    val sparseBinding: Boolean = false,
    val sparseResidencyBuffer: Boolean = false,
    val sparseResidencyImage2D: Boolean = false,
    val sparseResidencyImage3D: Boolean = false,
    val sparseResidency2Samples: Boolean = false,
    val sparseResidency4Samples: Boolean = false,
    val sparseResidency8Samples: Boolean = false,
    val sparseResidency16Samples: Boolean = false,
    val sparseResidencyAliased: Boolean = false,
    val variableMultisampleRate: Boolean = false,
    val inheritedQueries: Boolean = false
)
