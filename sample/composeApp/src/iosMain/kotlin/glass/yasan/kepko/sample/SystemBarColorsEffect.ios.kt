package glass.yasan.kepko.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarStyleDarkContent
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.setStatusBarStyle

@Composable
actual fun SystemBarColorsEffect(
    statusBarColor: Color,
    navigationBarColor: Color,
    isDark: Boolean,
) {
    LaunchedEffect(isDark) {
        val style = if (isDark) {
            UIStatusBarStyleLightContent
        } else {
            UIStatusBarStyleDarkContent
        }
        UIApplication.sharedApplication.setStatusBarStyle(style, animated = true)
    }
}
