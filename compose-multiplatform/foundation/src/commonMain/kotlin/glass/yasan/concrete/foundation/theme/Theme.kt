package glass.yasan.concrete.foundation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
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

/**
 * @param isDynamicAccentAllowed Whether to use dynamic accent colors if available.
 */
@Composable
public fun ConcreteTheme(
    primary: Color = ColorTokens.primary,
    secondary: Color = primary,
    tertiary: Color = secondary,
    isDark: Boolean = isSystemInDarkTheme(),
    isDynamicAccentAllowed: Boolean = true,
    dimensions: Dimensions = ConcreteTheme.dimensions,
    content: @Composable () -> Unit,
) {
    val accent = rememberAccent(isDynamicAccentAllowed, isDark, primary, secondary, tertiary)

    val colors = Colors(
        isDark = isDark,
        primary = accent.primary,
        secondary = accent.secondary,
        tertiary = accent.tertiary,
    )

    MaterialTheme(
        colorScheme = colors.toMaterial3ColorScheme(),
        typography = rubikTypography(),
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
private fun Colors.toMaterial3ColorScheme(): ColorScheme = colorScheme.copy(
    background = midground,
    inverseOnSurface = foreground,
    inversePrimary = onPrimary,
    inverseSurface = inverseForeground,
    onBackground = content,
    onError = Color.Unspecified,
    onErrorContainer = Color.Unspecified,
    onPrimary = onPrimary,
    onPrimaryContainer = onPrimary,
    onSecondary = onSecondary,
    onSecondaryContainer = onSecondary,
    onSurface = content,
    onSurfaceVariant = content,
    onTertiary = onTertiary,
    onTertiaryContainer = onTertiary,
    outline = background,
    outlineVariant = background,
    primary = primary,
    primaryContainer = primaryContainer,
    scrim = foreground,
    secondary = secondary,
    secondaryContainer = secondaryContainer,
    surface = foreground,
    surfaceBright = foreground,
    surfaceContainer = midground,
    surfaceContainerHigh = midground,
    surfaceContainerHighest = midground,
    surfaceContainerLow = midground,
    surfaceContainerLowest = midground,
    surfaceDim = midground,
    surfaceTint = midground,
    surfaceVariant = midground,
    tertiary = tertiary,
    tertiaryContainer = tertiaryContainer,
)
