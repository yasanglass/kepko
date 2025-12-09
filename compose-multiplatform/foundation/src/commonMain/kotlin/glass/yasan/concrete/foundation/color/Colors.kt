package glass.yasan.concrete.foundation.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
public class Colors internal constructor(
    isDark: Boolean,

    public val content: Color = ColorTokens.content(isDark),
    public val contentSubtle: Color = ColorTokens.contentSubtle(isDark),
    public val contentDisabled: Color = ColorTokens.contentDisabled(isDark),

    public val inverseContent: Color = ColorTokens.content(!isDark),
    public val inverseContentSubtle: Color = ColorTokens.contentSubtle(!isDark),
    public val inverseContentDisabled: Color = ColorTokens.contentDisabled(!isDark),

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
        )
    }
