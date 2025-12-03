package glass.yasan.concrete.foundation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import glass.yasan.concrete.foundation.color.ColorTokens
import glass.yasan.concrete.foundation.color.Colors
import glass.yasan.concrete.foundation.color.LocalColors
import glass.yasan.concrete.foundation.color.rememberAccent
import glass.yasan.concrete.foundation.dimension.Dimensions
import glass.yasan.concrete.foundation.dimension.LocalDimensions
import glass.yasan.concrete.foundation.typography.rubikTypography

@Composable
public fun ConcreteTheme(
    primary: Color = ColorTokens.accentPrimary,
    secondary: Color = primary,
    tertiary: Color = primary,
    isDark: Boolean = isSystemInDarkTheme(),
    dimensions: Dimensions = ConcreteTheme.dimensions,
    typography: Typography = rubikTypography(),
    content: @Composable () -> Unit,
) {
    val accent = rememberAccent(isDark, primary, secondary, tertiary)
    val contentColors = if (isDark) ColorTokens.contentDark else ColorTokens.contentLight
    val layer = if (isDark) ColorTokens.layerDark else ColorTokens.layerLight
    val colors = Colors(accent = accent, content = contentColors, layer = layer)

    MaterialTheme(
        colorScheme = createMaterial3ColorScheme(accent, contentColors, layer),
        typography = typography,
    ) {
        CompositionLocalProvider(
            LocalColors provides colors,
            LocalDimensions provides dimensions,
            content = content
        )
    }
}

public object ConcreteTheme {

    public val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    public val dimensions: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

}

@Composable
private fun createMaterial3ColorScheme(
    accent: Colors.Accent,
    content: Colors.Content,
    layer: Colors.Layer,
): ColorScheme = colorScheme.copy(
    background = layer.midground,
    inverseOnSurface = layer.foreground,
    inversePrimary = accent.onPrimary,
    inverseSurface = layer.inverseForeground,
    onBackground = content.normal,
    onError = Color.Unspecified,
    onErrorContainer = Color.Unspecified,
    onPrimary = accent.onPrimary,
    onPrimaryContainer = accent.onPrimary,
    onSecondary = accent.onSecondary,
    onSecondaryContainer = accent.onSecondary,
    onSurface = content.normal,
    onSurfaceVariant = content.normal,
    onTertiary = accent.onTertiary,
    onTertiaryContainer = accent.onTertiary,
    outline = layer.background,
    outlineVariant = layer.background,
    primary = accent.primary,
    primaryContainer = accent.primaryContainer,
    scrim = layer.foreground,
    secondary = accent.secondary,
    secondaryContainer = accent.secondaryContainer,
    surface = layer.foreground,
    surfaceBright = layer.foreground,
    surfaceContainer = layer.midground,
    surfaceContainerHigh = layer.midground,
    surfaceContainerHighest = layer.midground,
    surfaceContainerLow = layer.midground,
    surfaceContainerLowest = layer.midground,
    surfaceDim = layer.midground,
    surfaceTint = layer.midground,
    surfaceVariant = layer.midground,
    tertiary = accent.tertiary,
    tertiaryContainer = accent.tertiaryContainer,
)
