import io.github.inoutch.kotlin.vulkan.api.VkExtent2D
import io.github.inoutch.kotlin.vulkan.api.VkInstance
import io.github.inoutch.kotlin.vulkan.api.VkSurface
import io.github.inoutch.kotlin.vulkan.api.vk
import io.github.inoutch.kotlin.vulkan.example.Application
import io.github.inoutch.kotlin.vulkan.example.VK
import io.github.inoutch.kotlin.vulkan.utility.MutableProperty
import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.toCPointer
import kotlinx.cinterop.useContents
import platform.Foundation.NSCoder
import platform.Foundation.NSDefaultRunLoopMode
import platform.Foundation.NSRunLoop
import platform.Foundation.NSSelectorFromString
import platform.QuartzCore.CADisplayLink
import platform.UIKit.UIScreen
import platform.UIKit.UITextFieldDelegateProtocol
import platform.UIKit.UIViewController
import platform.UIKit.contentScaleFactor
import platform.UIKit.multipleTouchEnabled
import vulkan_ios.VK_STRUCTURE_TYPE_IOS_SURFACE_CREATE_INFO_MVK
import vulkan_ios.VkIOSSurfaceCreateInfoMVK

@ExperimentalUnsignedTypes
@ExportObjCClass
class ViewController : UIViewController, UITextFieldDelegateProtocol {

    private lateinit var displayLink: CADisplayLink

    private lateinit var screenSize: VkExtent2D

    private lateinit var windowSize: VkExtent2D

    private lateinit var vkContext: VK

    private lateinit var application: Application

    @OverrideInit
    @Suppress("ConvertSecondaryConstructorToPrimary")
    constructor(coder: NSCoder) : super(coder)

    override fun viewDidLoad() {
        super.viewDidLoad()
        view.multipleTouchEnabled = true
        view.contentScaleFactor = UIScreen.mainScreen.nativeScale

        displayLink = CADisplayLink.displayLinkWithTarget(this, NSSelectorFromString("render:"))
        displayLink.preferredFramesPerSecond = 60
        displayLink.addToRunLoop(NSRunLoop.currentRunLoop, NSDefaultRunLoopMode)

        screenSize = UIScreen.mainScreen.bounds.useContents {
            VkExtent2D(size.width.toInt(), size.height.toInt())
        }
        windowSize = UIScreen.mainScreen.nativeBounds.useContents {
            VkExtent2D(size.width.toInt(), size.height.toInt())
        }
        val title = "Kotlin Vulkan for JVM"
        vkContext = VK(
                title,
                title,
                emptyList(),
                listOf("VK_KHR_surface", "VK_MVK_ios_surface"),
                emptyList(),
                listOf("VK_KHR_swapchain"), windowSize) { surface, instance ->
            createSurface(instance, surface)
        }
        application = Application(vkContext)
    }

    @Suppress("UNUSED_PARAMETER")
    @ObjCAction
    fun render(sender: CADisplayLink) {
        application.render()
    }

    private fun createSurface(instance: VkInstance, surfaceRef: MutableProperty<VkSurface>) = memScoped {
        val createInfo = alloc<VkIOSSurfaceCreateInfoMVK>()
        createInfo.sType = VK_STRUCTURE_TYPE_IOS_SURFACE_CREATE_INFO_MVK
        createInfo.pNext = null
        createInfo.flags = 0u
        createInfo.pView = view.objcPtr().toLong().toCPointer()

        vk.createIOSSurfaceMVK(instance, createInfo, surfaceRef)
    }
}
