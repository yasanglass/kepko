package glass.yasan.concrete.foundation.color

import androidx.compose.ui.graphics.Color

public object ColorTokens {

    public val primary: Color = Color(0xFF006970)

    private val foregroundLight: Color = Color(0xFFFFFFFF)
    private val midgroundLight: Color = Color(0xFFEEEEEE)
    private val backgroundLight: Color = Color(0xFFD8D8D8)
    private val contentLight: Color = Color(0xFF212121)
    private val contentSubtleLight: Color = Color(0xFF666666)
    private val foregroundDark: Color = Color(0xFF1F1F1F)
    private val midgroundDark: Color = Color(0xFF121212)
    private val backgroundDark: Color = Color(0xFF000000)
    private val contentDark: Color = Color(0xFFE0E0E0)
    private val contentSubtleDark: Color = Color(0xFFA0A0A0)

    internal fun foreground(isDark: Boolean): Color = if (isDark) foregroundDark else foregroundLight
    internal fun midground(isDark: Boolean): Color = if (isDark) midgroundDark else midgroundLight
    internal fun background(isDark: Boolean): Color = if (isDark) backgroundDark else backgroundLight
    internal fun content(isDark: Boolean): Color = if (isDark) contentDark else contentLight
    internal fun contentSubtle(isDark: Boolean): Color = if (isDark) contentSubtleDark else contentSubtleLight

}
