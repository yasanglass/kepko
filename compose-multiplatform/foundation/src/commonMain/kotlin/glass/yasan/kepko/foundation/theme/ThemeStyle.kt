package glass.yasan.kepko.foundation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.resource.Strings

public enum class ThemeStyle(
    public val id: String,
    public val isDark: Boolean = false,
    public val title: @Composable () -> String,
) {
    LIGHT(
        id = "light",
        isDark = false,
        title = { Strings.themeStyleLight }
    ),
    DARK(
        id = "dark",
        isDark = true,
        title = { Strings.themeStyleDark }
    ),
    BLACK(
        id = "black",
        isDark = true,
        title = { Strings.themeStyleBlack }
    ),
    @ExperimentalKepkoApi
    SOLARIZED_LIGHT(
        id = "solarized-light",
        isDark = false,
        title = { Strings.themeStyleLightSolarized }
    ),
    @ExperimentalKepkoApi
    SOLARIZED_DARK(
        id = "solarized-dark",
        isDark = true,
        title = { Strings.themeStyleDarkSolarized }
    ),
    ;

    public companion object {

        @Composable
        public fun fromIdOrSystemDarkTheme(id: String?): ThemeStyle = fromIdOrNull(id) ?: fromSystemDarkTheme()

        @Composable
        public fun fromSystemDarkTheme(): ThemeStyle = fromDarkTheme(isDark = isSystemInDarkTheme())

        public fun fromDarkTheme(isDark: Boolean): ThemeStyle = if (isDark) DARK else LIGHT

        public fun fromIdOrNull(id: String?): ThemeStyle? = entries.find { it.id == id }

    }
}
