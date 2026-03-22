package glass.yasan.kepko.foundation.system

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import glass.yasan.kepko.foundation.color.isDark

@Composable
public actual fun StatusBarColorEffect(backgroundColor: Color) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(backgroundColor) {
            val window = (view.context as Activity).window

            @Suppress("DEPRECATION")
            window.statusBarColor = backgroundColor.toArgb()

            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !backgroundColor.isDark()
        }
    }
}

@Composable
public actual fun NavigationBarColorEffect(backgroundColor: Color) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(backgroundColor) {
            val window = (view.context as Activity).window

            @Suppress("DEPRECATION")
            window.navigationBarColor = backgroundColor.toArgb()

            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightNavigationBars = !backgroundColor.isDark()
        }
    }
}
