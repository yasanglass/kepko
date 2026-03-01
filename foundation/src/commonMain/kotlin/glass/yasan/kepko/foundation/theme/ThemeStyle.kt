package glass.yasan.kepko.foundation.theme

import androidx.compose.runtime.Composable
import glass.yasan.kepko.resource.Strings

public enum class ThemeStyle(
    public val id: String,
    public val category: Category,
    public val isDark: Boolean = false,
    public val title: @Composable () -> String,
) {
    LIGHT(
        id = "light",
        category = Category.STANDARD,
        isDark = false,
        title = { Strings.themeStyleLight }
    ),
    DARK(
        id = "dark",
        category = Category.STANDARD,
        isDark = true,
        title = { Strings.themeStyleDark }
    ),
    BLACK(
        id = "black",
        category = Category.STANDARD,
        isDark = true,
        title = { Strings.themeStyleBlack }
    ),
    SOLARIZED_LIGHT(
        id = "solarized-light",
        category = Category.SOLARIZED,
        isDark = false,
        title = { Strings.themeStyleLightSolarized }
    ),
    SOLARIZED_DARK(
        id = "solarized-dark",
        category = Category.SOLARIZED,
        isDark = true,
        title = { Strings.themeStyleDarkSolarized }
    ),
    CATPPUCCIN_LATTE(
        id = "catppuccin-latte",
        category = Category.CATPPUCCIN,
        isDark = false,
        title = { Strings.themeStyleCatppuccinLatte }
    ),
    CATPPUCCIN_FRAPPE(
        id = "catppuccin-frappe",
        category = Category.CATPPUCCIN,
        isDark = true,
        title = { Strings.themeStyleCatppuccinFrappe }
    ),
    CATPPUCCIN_MACCHIATO(
        id = "catppuccin-macchiato",
        category = Category.CATPPUCCIN,
        isDark = true,
        title = { Strings.themeStyleCatppuccinMacchiato }
    ),
    CATPPUCCIN_MOCHA(
        id = "catppuccin-mocha",
        category = Category.CATPPUCCIN,
        isDark = true,
        title = { Strings.themeStyleCatppuccinMocha }
    ),
    ;

    public enum class Category {
        STANDARD,
        SOLARIZED,
        CATPPUCCIN,
    }

    public companion object {

        public val defaultLight: ThemeStyle get(): ThemeStyle = LIGHT

        public val defaultDark: ThemeStyle get(): ThemeStyle = DARK

        @Composable
        public fun fromIdOrSystemDarkTheme(id: String?): ThemeStyle = fromIdOrNull(id) ?: fromSystemDarkTheme()

        @Composable
        public fun fromSystemDarkTheme(): ThemeStyle = fromDarkTheme(isDark = isSystemInDarkTheme())

        public fun fromDarkTheme(isDark: Boolean): ThemeStyle = if (isDark) DARK else LIGHT

        public fun fromIdOrNull(id: String?): ThemeStyle? = entries.find { it.id == id }

    }
}
