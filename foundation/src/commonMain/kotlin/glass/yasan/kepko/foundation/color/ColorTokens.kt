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
    }

    internal fun onSuccess(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> onSuccessStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> onSuccessSolarized
    }

    internal fun information(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> informationStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> informationSolarized
    }

    internal fun onInformation(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> onInformationStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> onInformationSolarized
    }

    internal fun caution(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> cautionStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> cautionSolarized
    }

    internal fun onCaution(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> onCautionStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> onCautionSolarized
    }

    internal fun danger(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> dangerStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> dangerSolarized
    }

    internal fun onDanger(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT, ThemeStyle.DARK, ThemeStyle.BLACK -> onDangerStandard
        ThemeStyle.SOLARIZED_LIGHT, ThemeStyle.SOLARIZED_DARK -> onDangerSolarized
    }


    internal fun foreground(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> foregroundLight
        ThemeStyle.DARK -> foregroundDark
        ThemeStyle.BLACK -> foregroundBlack
        ThemeStyle.SOLARIZED_LIGHT -> foregroundLightSolarized
        ThemeStyle.SOLARIZED_DARK -> foregroundDarkSolarized
    }

    internal fun midground(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> midgroundLight
        ThemeStyle.DARK -> midgroundDark
        ThemeStyle.BLACK -> midgroundDarkBlack
        ThemeStyle.SOLARIZED_LIGHT -> midgroundLightSolarized
        ThemeStyle.SOLARIZED_DARK -> midgroundDarkSolarized
    }

    internal fun background(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> backgroundLight
        ThemeStyle.DARK -> backgroundDark
        ThemeStyle.BLACK -> backgroundDarkBlack
        ThemeStyle.SOLARIZED_LIGHT -> backgroundLightSolarized
        ThemeStyle.SOLARIZED_DARK -> backgroundDarkSolarized
    }

    internal fun outline(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> outlineLight
        ThemeStyle.DARK -> outlineDark
        ThemeStyle.BLACK -> outlineDarkBlack
        ThemeStyle.SOLARIZED_LIGHT -> outlineLightSolarized
        ThemeStyle.SOLARIZED_DARK -> outlineDarkSolarized
    }

    internal fun content(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> contentLight
        ThemeStyle.DARK, ThemeStyle.BLACK -> contentDark
        ThemeStyle.SOLARIZED_LIGHT -> contentLightSolarized
        ThemeStyle.SOLARIZED_DARK -> contentDarkSolarized
    }

    internal fun contentSubtle(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> contentSubtleLight
        ThemeStyle.DARK, ThemeStyle.BLACK -> contentSubtleDark
        ThemeStyle.SOLARIZED_LIGHT -> contentSubtleLightSolarized
        ThemeStyle.SOLARIZED_DARK -> contentSubtleDarkSolarized
    }

    internal fun contentDisabled(style: ThemeStyle): Color = when (style) {
        ThemeStyle.LIGHT -> contentDisabledLight
        ThemeStyle.DARK -> contentDisabledDark
        ThemeStyle.BLACK -> contentDisabledBlack
        ThemeStyle.SOLARIZED_LIGHT -> contentDisabledLightSolarized
        ThemeStyle.SOLARIZED_DARK -> contentDisabledDarkSolarized
    }

}
