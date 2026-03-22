package glass.yasan.kepko.foundation.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Applies background colors to both system bars. A `null` color leaves that bar unchanged.
 *
 * No-op on non-mobile platforms.
 *
 * @see StatusBarColorEffect
 * @see NavigationBarColorEffect
 */
@Composable
public fun SystemBarColorsEffect(
    statusBarBackgroundColor: Color? = null,
    navigationBarBackgroundColor: Color? = null,
) {
    if (statusBarBackgroundColor != null) {
        StatusBarColorEffect(statusBarBackgroundColor)
    }
    if (navigationBarBackgroundColor != null) {
        NavigationBarColorEffect(navigationBarBackgroundColor)
    }
}

/**
 * Applies the background color to the status bar. Content style is resolved from luminance.
 *
 * No-op on non-mobile platforms.
 *
 * @see SystemBarColorsEffect
 * @see NavigationBarColorEffect
 */
@Composable
public expect fun StatusBarColorEffect(backgroundColor: Color)

/**
 * Applies the background color to the navigation bar. Content style is resolved from luminance.
 *
 * No-op on non-mobile platforms.
 *
 * @see SystemBarColorsEffect
 * @see StatusBarColorEffect
 */
@Composable
public expect fun NavigationBarColorEffect(backgroundColor: Color)
