package glass.yasan.concrete.foundation.color

import androidx.compose.ui.graphics.Color

public object ColorTokens {

    // Accent
    public val accentPrimary: Color = Color(0xFF006970)

    // Light
    private val foregroundLight: Color = Color(0xFFFFFFFF)
    private val midgroundLight: Color = Color(0xFFEEEEEE)
    private val backgroundLight: Color = Color(0xFFD8D8D8)
    private val contentMajorLight: Color = Color(0xFF212121)
    private val contentMinorLight: Color = Color(0xFF666666)

    // Dark
    private val foregroundDark: Color = Color(0xFF1F1F1F)
    private val midgroundDark: Color = Color(0xFF121212)
    private val backgroundDark: Color = Color(0xFF000000)
    private val contentMajorDark: Color = Color(0xFFE0E0E0)
    private val contentMinorDark: Color = Color(0xFFA0A0A0)

    // Content
    public val contentLight: Content = Content(
        major = contentMajorLight,
        minor = contentMinorLight,
        inverseMajor = contentMajorDark,
        inverseMinor = contentMinorDark,
    )
    public val contentDark: Content = Content(
        major = contentMajorDark,
        minor = contentMinorDark,
        inverseMajor = contentMajorLight,
        inverseMinor = contentMinorLight,
    )

    // Layer
    public val layerLight: Layer = Layer(
        foreground = foregroundLight,
        midground = midgroundLight,
        background = backgroundLight,
        inverseForeground = foregroundDark,
        inverseMidground = midgroundDark,
        inverseBackground = backgroundDark,
    )
    public val layerDark: Layer = Layer(
        foreground = foregroundDark,
        midground = midgroundDark,
        background = backgroundDark,
        inverseForeground = foregroundLight,
        inverseMidground = midgroundLight,
        inverseBackground = backgroundLight,
    )

}
