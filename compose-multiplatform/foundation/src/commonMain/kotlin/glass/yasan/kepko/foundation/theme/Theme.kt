package glass.yasan.kepko.foundation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import glass.yasan.kepko.foundation.color.Colors
import glass.yasan.kepko.foundation.color.LocalColors
import glass.yasan.kepko.foundation.dimension.Dimensions
import glass.yasan.kepko.foundation.dimension.LocalDimensions
import glass.yasan.kepko.foundation.shape.LocalShapes
import glass.yasan.kepko.foundation.shape.Shapes
import glass.yasan.kepko.foundation.typography.rubikTypography

@Composable
public fun KepkoTheme(
    style: ThemeStyle = if (isSystemInDarkTheme()) ThemeStyle.DARK else ThemeStyle.LIGHT,
    colors: Colors = Colors(isDark = style == ThemeStyle.DARK),
    dimensions: Dimensions = KepkoTheme.dimensions,
    shapes: Shapes = KepkoTheme.shapes,
    material3ColorScheme: ColorScheme = colors.toMaterial3ColorScheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = material3ColorScheme,
        typography = rubikTypography(),
    ) {
        CompositionLocalProvider(
            LocalColors provides colors,
            LocalDimensions provides dimensions,
            LocalShapes provides shapes,
            LocalContentColor provides colors.content,
            content = content,
        )
    }
}

@Composable
public fun KepkoTheme(
    isDark: Boolean,
    colors: Colors = Colors(isDark = isDark),
    dimensions: Dimensions = KepkoTheme.dimensions,
    shapes: Shapes = KepkoTheme.shapes,
    material3ColorScheme: ColorScheme = colors.toMaterial3ColorScheme(),
    content: @Composable () -> Unit,
) {
    KepkoTheme(
        style = if (isDark) ThemeStyle.DARK else ThemeStyle.LIGHT,
        colors = colors,
        dimensions = dimensions,
        shapes = shapes,
        material3ColorScheme = material3ColorScheme,
        content = content,
    )
}

public object KepkoTheme {

    public val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    public val dimensions: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    public val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

}

@Composable
public fun Colors.toMaterial3ColorScheme(): ColorScheme = colorScheme.copy(
    error = danger,
    onError = onDanger,
    background = midground,
    inverseOnSurface = foreground,
    inverseSurface = inverseForeground,
    onBackground = content,
    onSurface = content,
    onSurfaceVariant = content,
    outline = outline,
    outlineVariant = outline,
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
