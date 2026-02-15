package glass.yasan.kepko.sample

import androidx.compose.ui.window.Window
import glass.yasan.kepko.composeapp.generated.resources.Res
import glass.yasan.kepko.composeapp.generated.resources.app_name
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

fun main() {
    NSApplication.sharedApplication()
    val appName = runBlocking { getString(Res.string.app_name) }
    Window(title = appName) {
        SampleApp()
    }
    NSApp?.run()
}
