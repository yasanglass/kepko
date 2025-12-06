package glass.yasan.concrete.foundation.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

public class Colors internal constructor(
    isDark: Boolean,
    public val primary: Color,
    public val secondary: Color = primary,
    public val tertiary: Color = secondary,

    public val primaryDark: Color = primary.darken(),
    public val secondaryDark: Color = secondary.darken(),
    public val tertiaryDark: Color = tertiary.darken(),

    public val primaryLight: Color = primary.lighten(),
    public val secondaryLight: Color = secondary.lighten(),
    public val tertiaryLight: Color = tertiary.lighten(),

    public val primaryContainer: Color = primary.container(),
    public val secondaryContainer: Color = secondary.container(),
    public val tertiaryContainer: Color = tertiary.container(),

    public val onPrimary: Color = primary.content(),
    public val onSecondary: Color = secondary.content(),
    public val onTertiary: Color = tertiary.content(),

    public val content: Color = ColorTokens.content(isDark),
    public val contentSubtle: Color = ColorTokens.contentSubtle(isDark),

    public val inverseContent: Color = ColorTokens.content(!isDark),
    public val inverseContentSubtle: Color = ColorTokens.contentSubtle(!isDark),

    public val foreground: Color = ColorTokens.foreground(isDark),
    public val midground: Color = ColorTokens.midground(isDark),
    public val background: Color = ColorTokens.background(isDark),

    public val inverseForeground: Color = ColorTokens.foreground(!isDark),
    public val inverseMidground: Color = ColorTokens.midground(!isDark),
    public val inverseBackground: Color = ColorTokens.background(!isDark),
)

internal val LocalColors: ProvidableCompositionLocal<Colors> =
    compositionLocalOf {
        Colors(
            isDark = false,
            primary = ColorTokens.primary,
        )
    }

internal data class Accent(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
)

@Composable
internal expect fun rememberDynamicAccent(isDark: Boolean): Accent?

@Composable
internal fun rememberAccent(
    isDynamicAccentAllowed: Boolean,
    isDark: Boolean,
    primary: Color,
    secondary: Color,
    tertiary: Color,
): Accent {
    val dynamicAccent = if (isDynamicAccentAllowed) {
        rememberDynamicAccent(isDark)
    } else {
        null
    }

    return dynamicAccent ?: remember(primary, secondary, tertiary) {
        Accent(
            primary = primary,
            secondary = secondary,
            tertiary = tertiary,
        )
    }
}

@Composable
public expect fun isDynamicAccentSupported(): Boolean
