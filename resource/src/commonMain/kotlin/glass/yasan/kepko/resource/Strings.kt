package glass.yasan.kepko.resource

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource

public object Strings {

    public val back: String
        @Composable get() = stringResource(Res.string.back)

    public val goBack: String
        @Composable get() = stringResource(Res.string.go_back)

    public val enabled: String
        @Composable get() = stringResource(Res.string.enabled)

    public val disabled: String
        @Composable get() = stringResource(Res.string.disabled)

    public val grayscaleEnabled: String
        @Composable get() = stringResource(Res.string.grayscale_enabled)

    public val grayscaleDisabled: String
        @Composable get() = stringResource(Res.string.grayscale_disabled)

    public val preferenceBadgeActive: String
        @Composable get() = stringResource(Res.string.preference_badge_active)

    public val preferenceBadgeAlpha: String
        @Composable get() = stringResource(Res.string.preference_badge_alpha)

    public val preferenceBadgeBeta: String
        @Composable get() = stringResource(Res.string.preference_badge_beta)

    public val preferenceBadgeDefault: String
        @Composable get() = stringResource(Res.string.preference_badge_default)

    public val preferenceBadgeEarlyAccess: String
        @Composable get() = stringResource(Res.string.preference_badge_early_access)

    public val preferenceBadgeExperimental: String
        @Composable get() = stringResource(Res.string.preference_badge_experimental)

    public val preferenceBadgeLegacy: String
        @Composable get() = stringResource(Res.string.preference_badge_legacy)

    public val preferenceBadgeNew: String
        @Composable get() = stringResource(Res.string.preference_badge_new)

    @Composable
    public fun preferenceBadgeOverriddenBy(profileName: String): String =
        stringResource(Res.string.preference_badge_overridden_by, profileName)

    public val preferenceBadgePreview: String
        @Composable get() = stringResource(Res.string.preference_badge_preview)

    public val colorPaletteSystem: String
        @Composable get() = stringResource(Res.string.color_palette_system)

    public val colorPaletteBlack: String
        @Composable get() = stringResource(Res.string.color_palette_black)

    public val colorPaletteCatppuccinFrappe: String
        @Composable get() = stringResource(Res.string.color_palette_catppuccin_frappe)

    public val colorPaletteCatppuccinLatte: String
        @Composable get() = stringResource(Res.string.color_palette_catppuccin_latte)

    public val colorPaletteCatppuccinMacchiato: String
        @Composable get() = stringResource(Res.string.color_palette_catppuccin_macchiato)

    public val colorPaletteCatppuccinMocha: String
        @Composable get() = stringResource(Res.string.color_palette_catppuccin_mocha)

    public val colorPaletteGruvboxLight: String
        @Composable get() = stringResource(Res.string.color_palette_gruvbox_light)

    public val colorPaletteGruvboxDark: String
        @Composable get() = stringResource(Res.string.color_palette_gruvbox_dark)

    public val colorPaletteDark: String
        @Composable get() = stringResource(Res.string.color_palette_dark)

    public val colorPaletteDarkSolarized: String
        @Composable get() = stringResource(Res.string.color_palette_dark_solarized)

    public val colorPaletteLight: String
        @Composable get() = stringResource(Res.string.color_palette_light)

    public val colorPaletteLightSolarized: String
        @Composable get() = stringResource(Res.string.color_palette_light_solarized)

    public val preferenceDarkPaletteDescription: String
        @Composable get() = stringResource(Res.string.preference_dark_palette_description)

    public val preferenceDarkPaletteTitle: String
        @Composable get() = stringResource(Res.string.preference_dark_palette_title)

    public val preferenceFollowGlobalTitle: String
        @Composable get() = stringResource(Res.string.preference_follow_global_title)

    public val preferenceGrayscaleDescription: String
        @Composable get() = stringResource(Res.string.preference_grayscale_description)

    public val preferenceGrayscaleTitle: String
        @Composable get() = stringResource(Res.string.preference_grayscale_title)

    public val preferenceLightPaletteDescription: String
        @Composable get() = stringResource(Res.string.preference_light_palette_description)

    public val preferenceLightPaletteTitle: String
        @Composable get() = stringResource(Res.string.preference_light_palette_title)

    public val preferencePaletteModeDynamic: String
        @Composable get() = stringResource(Res.string.preference_palette_mode_dynamic)

    public val preferencePaletteModeDynamicDescription: String
        @Composable get() = stringResource(Res.string.preference_palette_mode_dynamic_description)

    public val preferencePaletteTitle: String
        @Composable get() = stringResource(Res.string.preference_palette_title)

    public val preferenceStaticPaletteTitle: String
        @Composable get() = stringResource(Res.string.preference_static_palette_title)

    public val preferenceOutlineTitle: String
        @Composable get() = stringResource(Res.string.preference_outline_title)

    public val preferenceRoundnessTitle: String
        @Composable get() = stringResource(Res.string.preference_roundness_title)

    public val reset: String
        @Composable get() = stringResource(Res.string.reset)

    public val preferenceThemeScreenTitle: String
        @Composable get() = stringResource(Res.string.preference_theme_screen_title)

    public val colorSuccess: String
        @Composable get() = stringResource(Res.string.color_success)

    public val colorInformation: String
        @Composable get() = stringResource(Res.string.color_information)

    public val colorCaution: String
        @Composable get() = stringResource(Res.string.color_caution)

    public val colorDanger: String
        @Composable get() = stringResource(Res.string.color_danger)

    public val colorForeground: String
        @Composable get() = stringResource(Res.string.color_foreground)

    public val colorMidground: String
        @Composable get() = stringResource(Res.string.color_midground)

    public val colorBackground: String
        @Composable get() = stringResource(Res.string.color_background)

    public val colorOutline: String
        @Composable get() = stringResource(Res.string.color_outline)

    public val colorContent: String
        @Composable get() = stringResource(Res.string.color_content)

    public val colorContentSubtle: String
        @Composable get() = stringResource(Res.string.color_content_subtle)

    public val colorContentDisabled: String
        @Composable get() = stringResource(Res.string.color_content_disabled)

}
