package glass.yasan.kepko.foundation.theme

import androidx.compose.runtime.Composable
import glass.yasan.kepko.resource.Strings

public enum class ColorPalette(
    public val id: String,
    public val category: Category,
    public val isDark: Boolean = false,
    public val title: @Composable () -> String,
) {
    LIGHT(
        id = "light",
        category = Category.STANDARD,
        isDark = false,
        title = { Strings.colorPaletteLight }
    ),
    DARK(
        id = "dark",
        category = Category.STANDARD,
        isDark = true,
        title = { Strings.colorPaletteDark }
    ),
    BLACK(
        id = "black",
        category = Category.STANDARD,
        isDark = true,
        title = { Strings.colorPaletteBlack }
    ),
    SOLARIZED_LIGHT(
        id = "solarized-light",
        category = Category.SOLARIZED,
        isDark = false,
        title = { Strings.colorPaletteLightSolarized }
    ),
    SOLARIZED_DARK(
        id = "solarized-dark",
        category = Category.SOLARIZED,
        isDark = true,
        title = { Strings.colorPaletteDarkSolarized }
    ),
    CATPPUCCIN_LATTE(
        id = "catppuccin-latte",
        category = Category.CATPPUCCIN,
        isDark = false,
        title = { Strings.colorPaletteCatppuccinLatte }
    ),
    CATPPUCCIN_FRAPPE(
        id = "catppuccin-frappe",
        category = Category.CATPPUCCIN,
        isDark = true,
        title = { Strings.colorPaletteCatppuccinFrappe }
    ),
    CATPPUCCIN_MACCHIATO(
        id = "catppuccin-macchiato",
        category = Category.CATPPUCCIN,
        isDark = true,
        title = { Strings.colorPaletteCatppuccinMacchiato }
    ),
    CATPPUCCIN_MOCHA(
        id = "catppuccin-mocha",
        category = Category.CATPPUCCIN,
        isDark = true,
        title = { Strings.colorPaletteCatppuccinMocha }
    ),
    GRUVBOX_LIGHT(
        id = "gruvbox-light",
        category = Category.GRUVBOX,
        isDark = false,
        title = { Strings.colorPaletteGruvboxLight }
    ),
    GRUVBOX_DARK(
        id = "gruvbox-dark",
        category = Category.GRUVBOX,
        isDark = true,
        title = { Strings.colorPaletteGruvboxDark }
    ),
    ;

    public enum class Category {
        STANDARD,
        SOLARIZED,
        CATPPUCCIN,
        GRUVBOX,
    }

    public companion object {

        public val defaultLight: ColorPalette get(): ColorPalette = LIGHT

        public val defaultDark: ColorPalette get(): ColorPalette = DARK

        @Composable
        public fun fromIdOrSystemDarkTheme(id: String?): ColorPalette = fromIdOrNull(id) ?: fromSystemDarkTheme()

        @Composable
        public fun fromSystemDarkTheme(): ColorPalette = fromDarkTheme(isDark = isSystemInDarkTheme())

        public fun fromDarkTheme(isDark: Boolean): ColorPalette = if (isDark) DARK else LIGHT

        public fun fromIdOrNull(id: String?): ColorPalette? = entries.find { it.id == id }

    }
}
