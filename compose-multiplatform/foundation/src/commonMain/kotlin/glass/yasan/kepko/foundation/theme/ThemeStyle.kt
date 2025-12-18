package glass.yasan.kepko.foundation.theme

import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.Res
import glass.yasan.kepko.foundation.theme_style_dark
import glass.yasan.kepko.foundation.theme_style_dark_amoled
import glass.yasan.kepko.foundation.theme_style_light
import org.jetbrains.compose.resources.stringResource

public enum class ThemeStyle(
    public val id: String,
    public val isDark: Boolean = false,
    public val title: @Composable () -> String,
) {
    LIGHT(
        id = "light",
        isDark = false,
        title = { stringResource(Res.string.theme_style_light) }
    ),
    DARK(
        id = "dark",
        isDark = true,
        title = { stringResource(Res.string.theme_style_dark) }
    ),
    DARK_AMOLED(
        id = "dark-amoled",
        isDark = true,
        title = { stringResource(Res.string.theme_style_dark_amoled) }
    ),
    ;

    public companion object {

        public fun fromDarkMode(isDark: Boolean): ThemeStyle = if (isDark) DARK else LIGHT

        public fun fromId(id: String): ThemeStyle? = entries.find { it.id == id }

    }
}
