package glass.yasan.kepko.foundation.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.color.isDark
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarStyleDarkContent
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.setStatusBarStyle

@Composable
public actual fun StatusBarColorEffect(backgroundColor: Color) {
    LaunchedEffect(backgroundColor) {
        val style = if (backgroundColor.isDark()) {
            UIStatusBarStyleLightContent
        } else {
            UIStatusBarStyleDarkContent
        }
        UIApplication.sharedApplication.setStatusBarStyle(style, animated = true)
    }
}

@Composable
public actual fun NavigationBarColorEffect(backgroundColor: Color) {
    /* No-op */
}
