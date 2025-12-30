package glass.yasan.kepko.foundation.theme

import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.resource.Res
import glass.yasan.kepko.resource.theme_style_black
import glass.yasan.kepko.resource.theme_style_dark
import glass.yasan.kepko.resource.theme_style_dark_solarized
import glass.yasan.kepko.resource.theme_style_light
import glass.yasan.kepko.resource.theme_style_light_solarized
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
    BLACK(
        id = "black",
        isDark = true,
        title = { stringResource(Res.string.theme_style_black) }
    ),
    @ExperimentalKepkoApi
    SOLARIZED_LIGHT(
        id = "solarized-light",
        isDark = false,
        title = { stringResource(Res.string.theme_style_light_solarized) }
    ),
    @ExperimentalKepkoApi
    SOLARIZED_DARK(
        id = "solarized-dark",
        isDark = true,
        title = { stringResource(Res.string.theme_style_dark_solarized) }
    ),
    ;

    public companion object {

        public fun fromDarkMode(isDark: Boolean): ThemeStyle = if (isDark) DARK else LIGHT

        public fun fromId(id: String): ThemeStyle? = entries.find { it.id == id }

    }
}
