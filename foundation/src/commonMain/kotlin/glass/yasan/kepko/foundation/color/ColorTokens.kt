package glass.yasan.kepko.foundation.color

import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.ThemeStyle

public object ColorTokens {

    private val foregroundLight: Color = Color(0xFFFFFFFF)
    private val midgroundLight: Color = Color(0xFFEEEEEE)
    private val backgroundLight: Color = Color(0xFFD8D8D8)

    private val contentLight: Color = Color(0xFF212121)
    private val contentSubtleLight: Color = Color(0xFF666666)
    private val contentDisabledLight: Color = Color(0xFFB3B3B3)

    private val foregroundDark: Color = Color(0xFF1F1F1F)
    private val midgroundDark: Color = Color(0xFF121212)
    private val backgroundDark: Color = Color(0xFF000000)

    private val foregroundBlack: Color = Color(0xFF000000)
    private val midgroundDarkBlack: Color = Color(0xFF000000)
    private val backgroundDarkBlack: Color = Color(0xFF121212)

    private val outlineLight: Color = backgroundLight
    private val outlineDark: Color = backgroundDark
    private val outlineDarkBlack: Color = Color(0xFF505050)

    private val foregroundLightSolarized: Color = Color(0xFFfdf6e3)
    private val midgroundLightSolarized: Color = Color(0xFFeee8d5)
    private val backgroundLightSolarized: Color = Color(0xFFddd6c3)
    private val outlineLightSolarized: Color = backgroundLightSolarized

    private val foregroundDarkSolarized: Color = Color(0xFF073642)
    private val midgroundDarkSolarized: Color = Color(0xFF002b36)
    private val backgroundDarkSolarized: Color = Color(0xFF001f26)
    private val outlineDarkSolarized: Color = backgroundDarkSolarized

    private val contentDark: Color = Color(0xFFE0E0E0)
    private val contentSubtleDark: Color = Color(0xFFA0A0A0)
    private val contentDisabledDark: Color = Color(0xFF606060)
    private val contentDisabledBlack: Color = Color(0xFF505050)

    private val contentLightSolarized: Color = Color(0xFF657b83)
    private val contentSubtleLightSolarized: Color = Color(0xFF93a1a1)
    private val contentDisabledLightSolarized: Color = Color(0xFFC8CCC2)

    private val contentDarkSolarized: Color = Color(0xFF839496)
    private val contentSubtleDarkSolarized: Color = Color(0xFF586e75)
    private val contentDisabledDarkSolarized: Color = Color(0xFF30525C)

    private val foregroundCatppuccinLatte: Color = Color(0xFFEFF1F5)
    private val midgroundCatppuccinLatte: Color = Color(0xFFE6E9EF)
    private val backgroundCatppuccinLatte: Color = Color(0xFFDCE0E8)
    private val outlineCatppuccinLatte: Color = Color(0xFFCCD0DA)
    private val contentCatppuccinLatte: Color = Color(0xFF4C4F69)
    private val contentSubtleCatppuccinLatte: Color = Color(0xFF6C6F85)
    private val contentDisabledCatppuccinLatte: Color = Color(0xFF9CA0B0)

    private val foregroundCatppuccinFrappe: Color = Color(0xFF303446)
    private val midgroundCatppuccinFrappe: Color = Color(0xFF292C3C)
    private val backgroundCatppuccinFrappe: Color = Color(0xFF232634)
    private val outlineCatppuccinFrappe: Color = Color(0xFF414559)
    private val contentCatppuccinFrappe: Color = Color(0xFFC6D0F5)
    private val contentSubtleCatppuccinFrappe: Color = Color(0xFFA5ADCE)
    private val contentDisabledCatppuccinFrappe: Color = Color(0xFF838BA7)

    private val foregroundCatppuccinMacchiato: Color = Color(0xFF24273A)
    private val midgroundCatppuccinMacchiato: Color = Color(0xFF1E2030)
    private val backgroundCatppuccinMacchiato: Color = Color(0xFF181926)
    private val outlineCatppuccinMacchiato: Color = Color(0xFF363A4F)
    private val contentCatppuccinMacchiato: Color = Color(0xFFCAD3F5)
    private val contentSubtleCatppuccinMacchiato: Color = Color(0xFFA5ADCB)
    private val contentDisabledCatppuccinMacchiato: Color = Color(0xFF8087A2)

    private val foregroundCatppuccinMocha: Color = Color(0xFF1E1E2E)
    private val midgroundCatppuccinMocha: Color = Color(0xFF181825)
    private val backgroundCatppuccinMocha: Color = Color(0xFF11111B)
    private val outlineCatppuccinMocha: Color = Color(0xFF313244)
    private val contentCatppuccinMocha: Color = Color(0xFFCDD6F4)
    private val contentSubtleCatppuccinMocha: Color = Color(0xFFA6ADC8)
    private val contentDisabledCatppuccinMocha: Color = Color(0xFF7F849C)

    private val foregroundGruvboxLight: Color = Color(0xFFfbf1c7)
    private val midgroundGruvboxLight: Color = Color(0xFFf2e5bc)
    private val backgroundGruvboxLight: Color = Color(0xFFebdbb2)
    private val outlineGruvboxLight: Color = Color(0xFFd5c4a1)
    private val contentGruvboxLight: Color = Color(0xFF3c3836)
    private val contentSubtleGruvboxLight: Color = Color(0xFF665c54)
    private val contentDisabledGruvboxLight: Color = Color(0xFFa89984)

    private val foregroundGruvboxDark: Color = Color(0xFF282828)
    private val midgroundGruvboxDark: Color = Color(0xFF1d2021)
    private val backgroundGruvboxDark: Color = Color(0xFF141414)
    private val outlineGruvboxDark: Color = Color(0xFF3c3836)
    private val contentGruvboxDark: Color = Color(0xFFebdbb2)
    private val contentSubtleGruvboxDark: Color = Color(0xFFa89984)
    private val contentDisabledGruvboxDark: Color = Color(0xFF665c54)

    private val successGruvboxLight: Color = Color(0xFF79740e)
    private val successGruvboxDark: Color = Color(0xFFb8bb26)
    private val informationGruvboxLight: Color = Color(0xFF076678)
    private val informationGruvboxDark: Color = Color(0xFF83a598)
    private val cautionGruvboxLight: Color = Color(0xFFb57614)
    private val cautionGruvboxDark: Color = Color(0xFFfabd2f)
    private val dangerGruvboxLight: Color = Color(0xFF9d0006)
    private val dangerGruvboxDark: Color = Color(0xFFfb4934)

    private val successCatppuccinLatte: Color = Color(0xFF40A02B)
    private val successCatppuccinFrappe: Color = Color(0xFFA6D189)
    private val successCatppuccinMacchiato: Color = Color(0xFFA6DA95)
    private val successCatppuccinMocha: Color = Color(0xFFA6E3A1)
    private val informationCatppuccinLatte: Color = Color(0xFF1E66F5)
    private val informationCatppuccinFrappe: Color = Color(0xFF8CAAEE)
    private val informationCatppuccinMacchiato: Color = Color(0xFF8AADF4)
    private val informationCatppuccinMocha: Color = Color(0xFF89B4FA)
    private val cautionCatppuccinLatte: Color = Color(0xFFDF8E1D)
    private val cautionCatppuccinFrappe: Color = Color(0xFFE5C890)
    private val cautionCatppuccinMacchiato: Color = Color(0xFFEED49F)
    private val cautionCatppuccinMocha: Color = Color(0xFFF9E2AF)
    private val dangerCatppuccinLatte: Color = Color(0xFFD20F39)
    private val dangerCatppuccinFrappe: Color = Color(0xFFE78284)
    private val dangerCatppuccinMacchiato: Color = Color(0xFFED8796)
    private val dangerCatppuccinMocha: Color = Color(0xFFF38BA8)

    private val successStandard: Color = Color(0xFF04B34F)
    private val onSuccessStandard: Color = Color(0xFFFFFFFF)
    private val informationStandard: Color = Color(0xFF0057B8)
    private val onInformationStandard: Color = Color(0xFFFFFFFF)
    private val cautionStandard: Color = Color(0xFFFF9900)
    private val onCautionStandard: Color = Color(0xFF000000)
    private val dangerStandard: Color = Color(0xFFD00036)
    private val onDangerStandard: Color = Color(0xFFFFFFFF)

    private val successSolarized: Color = Color(0xFF859900)
    private val onSuccessSolarized: Color = Color(0xFFfdf6e3)
    private val informationSolarized: Color = Color(0xFF2aa198)
    private val onInformationSolarized: Color = Color(0xFFfdf6e3)
    private val cautionSolarized: Color = Color(0xFFb58900)
    private val onCautionSolarized: Color = Color(0xFFfdf6e3)
    private val dangerSolarized: Color = Color(0xFFdc322f)
    private val onDangerSolarized: Color = Color(0xFFfdf6e3)

    internal fun success(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> successStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> successSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> successCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> successCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> successCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> successCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> successGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> successGruvboxDark
    }

    internal fun onSuccess(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> onSuccessStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> onSuccessSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> foregroundCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> foregroundCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> foregroundCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> foregroundCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> foregroundGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> foregroundGruvboxDark
    }

    internal fun information(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> informationStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> informationSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> informationCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> informationCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> informationCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> informationCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> informationGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> informationGruvboxDark
    }

    internal fun onInformation(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> onInformationStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> onInformationSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> foregroundCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> foregroundCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> foregroundCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> foregroundCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> foregroundGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> foregroundGruvboxDark
    }

    internal fun caution(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> cautionStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> cautionSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> cautionCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> cautionCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> cautionCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> cautionCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> cautionGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> cautionGruvboxDark
    }

    internal fun onCaution(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> onCautionStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> onCautionSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> backgroundCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> foregroundCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> foregroundCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> foregroundCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> foregroundGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> foregroundGruvboxDark
    }

    internal fun danger(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> dangerStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> dangerSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> dangerCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> dangerCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> dangerCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> dangerCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> dangerGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> dangerGruvboxDark
    }

    internal fun onDanger(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> onDangerStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> onDangerSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> foregroundCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> foregroundCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> foregroundCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> foregroundCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> foregroundGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> foregroundGruvboxDark
    }


    internal fun foreground(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> foregroundLight
        ThemeStyle.DARK -> foregroundDark
        ThemeStyle.BLACK -> foregroundBlack
        ThemeStyle.SOLARIZED_LIGHT -> foregroundLightSolarized
        ThemeStyle.SOLARIZED_DARK -> foregroundDarkSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> foregroundCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> foregroundCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> foregroundCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> foregroundCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> foregroundGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> foregroundGruvboxDark
    }

    internal fun midground(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> midgroundLight
        ThemeStyle.DARK -> midgroundDark
        ThemeStyle.BLACK -> midgroundDarkBlack
        ThemeStyle.SOLARIZED_LIGHT -> midgroundLightSolarized
        ThemeStyle.SOLARIZED_DARK -> midgroundDarkSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> midgroundCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> midgroundCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> midgroundCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> midgroundCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> midgroundGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> midgroundGruvboxDark
    }

    internal fun background(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> backgroundLight
        ThemeStyle.DARK -> backgroundDark
        ThemeStyle.BLACK -> backgroundDarkBlack
        ThemeStyle.SOLARIZED_LIGHT -> backgroundLightSolarized
        ThemeStyle.SOLARIZED_DARK -> backgroundDarkSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> backgroundCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> backgroundCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> backgroundCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> backgroundCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> backgroundGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> backgroundGruvboxDark
    }

    internal fun outline(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> outlineLight
        ThemeStyle.DARK -> outlineDark
        ThemeStyle.BLACK -> outlineDarkBlack
        ThemeStyle.SOLARIZED_LIGHT -> outlineLightSolarized
        ThemeStyle.SOLARIZED_DARK -> outlineDarkSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> outlineCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> outlineCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> outlineCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> outlineCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> outlineGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> outlineGruvboxDark
    }

    internal fun content(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> contentLight
        ThemeStyle.DARK, ThemeStyle.BLACK -> contentDark
        ThemeStyle.SOLARIZED_LIGHT -> contentLightSolarized
        ThemeStyle.SOLARIZED_DARK -> contentDarkSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> contentCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> contentCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> contentCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> contentCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> contentGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> contentGruvboxDark
    }

    internal fun contentSubtle(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> contentSubtleLight
        ThemeStyle.DARK, ThemeStyle.BLACK -> contentSubtleDark
        ThemeStyle.SOLARIZED_LIGHT -> contentSubtleLightSolarized
        ThemeStyle.SOLARIZED_DARK -> contentSubtleDarkSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> contentSubtleCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> contentSubtleCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> contentSubtleCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> contentSubtleCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> contentSubtleGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> contentSubtleGruvboxDark
    }

    internal fun contentDisabled(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> contentDisabledLight
        ThemeStyle.DARK -> contentDisabledDark
        ThemeStyle.BLACK -> contentDisabledBlack
        ThemeStyle.SOLARIZED_LIGHT -> contentDisabledLightSolarized
        ThemeStyle.SOLARIZED_DARK -> contentDisabledDarkSolarized
        ThemeStyle.CATPPUCCIN_LATTE -> contentDisabledCatppuccinLatte
        ThemeStyle.CATPPUCCIN_FRAPPE -> contentDisabledCatppuccinFrappe
        ThemeStyle.CATPPUCCIN_MACCHIATO -> contentDisabledCatppuccinMacchiato
        ThemeStyle.CATPPUCCIN_MOCHA -> contentDisabledCatppuccinMocha
        ThemeStyle.GRUVBOX_LIGHT -> contentDisabledGruvboxLight
        ThemeStyle.GRUVBOX_DARK -> contentDisabledGruvboxDark
    }

}
