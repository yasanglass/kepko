package glass.yasan.concrete.foundation.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

public data class Colors(
    val accent: Accent,
    val content: Content,
    val layer: Layer,
) {

    public data class Accent(
        val primary: Color,
        val secondary: Color = primary,
        val tertiary: Color = secondary,
    ) {

        val primaryDark: Color = primary.darken()
        val secondaryDark: Color = secondary.darken()
        val tertiaryDark: Color = tertiary.darken()

        val primaryLight: Color = primary.lighten()
        val secondaryLight: Color = secondary.lighten()
        val tertiaryLight: Color = tertiary.lighten()

        val onPrimary: Color = primary.toContentColor()
        val onSecondary: Color = secondary.toContentColor()
        val onTertiary: Color = tertiary.toContentColor()

        val primaryContainer: Color = primary.toContentColor()
        val secondaryContainer: Color = secondary.toContainerColor()
        val tertiaryContainer: Color = tertiary.toContainerColor()

    }

    public data class Content(
        val normal: Color,
        val subtle: Color,
        val inverseNormal: Color,
        val inverseSubtle: Color,
    )

    public data class Layer(
        val foreground: Color,
        val midground: Color,
        val background: Color,
        val inverseForeground: Color,
        val inverseMidground: Color,
        val inverseBackground: Color,
    ) {

        val outline: Color = background

    }

}

internal val LocalColors: ProvidableCompositionLocal<Colors> =
    compositionLocalOf {
        Colors(
            accent = Colors.Accent(primary = ColorTokens.accentPrimary),
            content = ColorTokens.contentLight,
            layer = ColorTokens.layerLight,
        )
    }

@Composable
internal expect fun rememberDynamicAccent(isDark: Boolean): Colors.Accent?

@Composable
internal fun rememberAccent(
    isDark: Boolean,
    primary: Color,
    secondary: Color,
    tertiary: Color,
): Colors.Accent = rememberDynamicAccent(isDark) ?: remember(primary, secondary, tertiary) {
    Colors.Accent(
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
    )
}
