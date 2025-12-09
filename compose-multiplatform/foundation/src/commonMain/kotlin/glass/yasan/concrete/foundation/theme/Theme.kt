package glass.yasan.concrete.foundation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import glass.yasan.concrete.foundation.color.Colors
import glass.yasan.concrete.foundation.color.LocalColors
import glass.yasan.concrete.foundation.dimension.Dimensions
import glass.yasan.concrete.foundation.dimension.LocalDimensions
import glass.yasan.concrete.foundation.typography.rubikTypography

@Composable
public fun ConcreteTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    dimensions: Dimensions = ConcreteTheme.dimensions,
    content: @Composable () -> Unit,
) {
    val colors = Colors(
        isDark = isDark,
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
    inverseSurface = inverseForeground,
    onBackground = content,
    onSurface = content,
    onSurfaceVariant = content,
    outline = background,
    outlineVariant = background,
    scrim = foreground,
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
)
