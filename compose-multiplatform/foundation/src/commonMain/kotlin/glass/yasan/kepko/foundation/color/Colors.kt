package glass.yasan.kepko.foundation.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.ThemeStyle

@Immutable
public class Colors internal constructor(
    style: ThemeStyle,
    private val grayscale: Boolean = false,
) {
    private val inverseStyle: ThemeStyle = if (style == ThemeStyle.LIGHT) ThemeStyle.DARK else ThemeStyle.LIGHT

    private fun Color.applyGrayscale(): Color {
        if (!grayscale) return this

        val luminance = 0.2126f * red + 0.7152f * green + 0.0722f * blue
        return Color(luminance, luminance, luminance, alpha)
    }

    public val success: Color = ColorTokens.success(style).applyGrayscale()
    public val onSuccess: Color = ColorTokens.onSuccess(style).applyGrayscale()

    public val information: Color = ColorTokens.information(style).applyGrayscale()
    public val onInformation: Color = ColorTokens.onInformation(style).applyGrayscale()

    public val caution: Color = ColorTokens.caution(style).applyGrayscale()
    public val onCaution: Color = ColorTokens.onCaution(style).applyGrayscale()

    public val danger: Color = ColorTokens.danger(style).applyGrayscale()
    public val onDanger: Color = ColorTokens.onDanger(style).applyGrayscale()

    public val content: Color = ColorTokens.content(style).applyGrayscale()
    public val contentSubtle: Color = ColorTokens.contentSubtle(style).applyGrayscale()
    public val contentDisabled: Color = ColorTokens.contentDisabled(style).applyGrayscale()

    public val inverseContent: Color = ColorTokens.content(inverseStyle).applyGrayscale()
    public val inverseContentSubtle: Color = ColorTokens.contentSubtle(inverseStyle).applyGrayscale()
    public val inverseContentDisabled: Color = ColorTokens.contentDisabled(inverseStyle).applyGrayscale()

    public val foreground: Color = ColorTokens.foreground(style).applyGrayscale()
    public val midground: Color = ColorTokens.midground(style).applyGrayscale()
    public val background: Color = ColorTokens.background(style).applyGrayscale()

    public val outline: Color = ColorTokens.outline(style).applyGrayscale()

    public val inverseForeground: Color = ColorTokens.foreground(inverseStyle).applyGrayscale()
    public val inverseMidground: Color = ColorTokens.midground(inverseStyle).applyGrayscale()
    public val inverseBackground: Color = ColorTokens.background(inverseStyle).applyGrayscale()
}

internal val LocalColors: ProvidableCompositionLocal<Colors> =
    compositionLocalOf {
        Colors(
            style = ThemeStyle.LIGHT,
        )
    }

@Composable
public fun Colors.getSemanticColors(): Array<Color> = arrayOf(
    success,
    information,
    caution,
    danger,
)

@Composable
public fun Colors.getLayerColors(): Array<Color> = arrayOf(
    foreground,
    midground,
    background,
)

@Composable
public fun Colors.getContentColors(): Array<Color> = arrayOf(
    content,
    contentSubtle,
    contentDisabled,
)
