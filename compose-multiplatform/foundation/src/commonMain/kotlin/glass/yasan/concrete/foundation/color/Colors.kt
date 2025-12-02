package glass.yasan.concrete.foundation.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

public data class Accent(
    val primary: Color,
    val secondary: Color = primary,
    val tertiary: Color = secondary,
)

public data class Content(
    val major: Color,
    val minor: Color,
    val inverseMajor: Color,
    val inverseMinor: Color,
)

public data class Layer(
    val foreground: Color,
    val midground: Color,
    val background: Color,
    val inverseForeground: Color,
    val inverseMidground: Color,
    val inverseBackground: Color,
)

internal val LocalAccent: ProvidableCompositionLocal<Accent> =
    compositionLocalOf {
        Accent(primary = ColorTokens.accentPrimary)
    }

internal val LocalContent: ProvidableCompositionLocal<Content> =
    compositionLocalOf { ColorTokens.contentLight }

internal val LocalLayer: ProvidableCompositionLocal<Layer> =
    compositionLocalOf { ColorTokens.layerLight }

@Composable
internal expect fun rememberDynamicAccent(isDark: Boolean): Accent?

@Composable
internal fun rememberAccent(
    isDark: Boolean,
    primary: Color,
    secondary: Color,
    tertiary: Color,
): Accent = rememberDynamicAccent(isDark) ?: remember(primary, secondary, tertiary) {
    Accent(
        primary = primary,
        secondary = secondary,
        tertiary = tertiary,
    )
}
