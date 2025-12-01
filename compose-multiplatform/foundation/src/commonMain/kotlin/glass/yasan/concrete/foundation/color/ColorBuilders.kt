package glass.yasan.concrete.foundation.color

import androidx.compose.ui.graphics.Color
import glass.yasan.concrete.foundation.color.Colors.Content
import glass.yasan.concrete.foundation.color.Colors.Layer
import glass.yasan.concrete.foundation.color.Colors.Theme

public fun lightColors(
    themePrimary: Color = ColorTokens.themePrimary,
    themeSecondary: Color = ColorTokens.themeSecondary,
    layerForeground: Color = ColorTokens.layerForegroundLight,
    layerMidground: Color = ColorTokens.layerMidgroundLight,
    layerBackground: Color = ColorTokens.layerBackgroundLight,
    layerForegroundInverse: Color = ColorTokens.layerForegroundDark,
    layerMidgroundInverse: Color = ColorTokens.layerMidgroundDark,
    layerBackgroundInverse: Color = ColorTokens.layerBackgroundDark,
    textTitle: Color = ColorTokens.contentMajorLight,
    textDescription: Color = ColorTokens.contentMinorLight,
): Colors = Colors(
    theme = Theme(
        primary = themePrimary,
        secondary = themeSecondary,
    ),
    content = Content(
        major = textTitle,
        minor = textDescription,
    ),
    layer = Layer(
        foreground = layerForeground,
        midground = layerMidground,
        background = layerBackground,
        foregroundInverse = layerForegroundInverse,
        midgroundInverse = layerMidgroundInverse,
        backgroundInverse = layerBackgroundInverse,
    ),
    isLight = true,
)

public fun darkColors(
    themePrimary: Color = ColorTokens.themePrimary,
    themeSecondary: Color = ColorTokens.themeSecondary,
    layerForeground: Color = ColorTokens.layerForegroundDark,
    layerMidground: Color = ColorTokens.layerMidgroundDark,
    layerBackground: Color = ColorTokens.layerBackgroundDark,
    layerForegroundInverse: Color = ColorTokens.layerForegroundLight,
    layerMidgroundInverse: Color = ColorTokens.layerMidgroundLight,
    layerBackgroundInverse: Color = ColorTokens.layerBackgroundLight,
    textTitle: Color = ColorTokens.contentMajorDark,
    textDescription: Color = ColorTokens.contentMinorDark,
): Colors = Colors(
    theme = Theme(
        primary = themePrimary,
        secondary = themeSecondary,
    ),
    content = Content(
        major = textTitle,
        minor = textDescription,
    ),
    layer = Layer(
        foreground = layerForeground,
        midground = layerMidground,
        background = layerBackground,
        foregroundInverse = layerForegroundInverse,
        midgroundInverse = layerMidgroundInverse,
        backgroundInverse = layerBackgroundInverse,
    ),
    isLight = true,
)
