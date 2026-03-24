@file:Suppress("ForbiddenImport")

package glass.yasan.kepko.foundation.theme

import androidx.compose.runtime.Composable
import io.github.kdroidfilter.platformtools.darkmodedetector.isSystemInDarkMode

/**
 * Replacement for `androidx.compose.foundation.isSystemInDarkTheme` which does not work reliably on desktop platforms.
 */
@Composable
public fun isSystemInDarkTheme(): Boolean = isSystemInDarkMode()
