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
) {
    private val inverseStyle: ThemeStyle = if (style == ThemeStyle.LIGHT) ThemeStyle.DARK else ThemeStyle.LIGHT

    public val success: Color = ColorTokens.success(style)
    public val onSuccess: Color = ColorTokens.onSuccess(style)

    public val information: Color = ColorTokens.information(style)
    public val onInformation: Color = ColorTokens.onInformation(style)

    public val caution: Color = ColorTokens.caution(style)
    public val onCaution: Color = ColorTokens.onCaution(style)

    public val danger: Color = ColorTokens.danger(style)
    public val onDanger: Color = ColorTokens.onDanger(style)

    public val content: Color = ColorTokens.content(style)
    public val contentSubtle: Color = ColorTokens.contentSubtle(style)
    public val contentDisabled: Color = ColorTokens.contentDisabled(style)

    public val inverseContent: Color = ColorTokens.content(inverseStyle)
    public val inverseContentSubtle: Color = ColorTokens.contentSubtle(inverseStyle)
    public val inverseContentDisabled: Color = ColorTokens.contentDisabled(inverseStyle)

    public val foreground: Color = ColorTokens.foreground(style)
    public val midground: Color = ColorTokens.midground(style)
    public val background: Color = ColorTokens.background(style)

    public val outline: Color = ColorTokens.outline(style)

    public val inverseForeground: Color = ColorTokens.foreground(inverseStyle)
    public val inverseMidground: Color = ColorTokens.midground(inverseStyle)
    public val inverseBackground: Color = ColorTokens.background(inverseStyle)
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
