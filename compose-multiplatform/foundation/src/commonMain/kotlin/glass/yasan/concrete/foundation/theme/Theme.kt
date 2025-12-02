package glass.yasan.concrete.foundation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import glass.yasan.concrete.foundation.color.Colors
import glass.yasan.concrete.foundation.color.LocalColors
import glass.yasan.concrete.foundation.dimension.Dimensions
import glass.yasan.concrete.foundation.dimension.LocalDimensions
import glass.yasan.concrete.foundation.typography.rubikTypography

@Composable
public fun ConcreteTheme(
    colors: Colors = ConcreteTheme.colors,
    dimensions: Dimensions = ConcreteTheme.dimensions,
    typography: Typography = rubikTypography(),
    content: @Composable () -> Unit,
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    val rememberedDimensions = remember { dimensions.copy() }.apply { updateDimensionsFrom(dimensions) }

    MaterialTheme(
        colorScheme = colors.toMaterial3Colors(),
        typography = typography,
    ) {
        CompositionLocalProvider(
            LocalColors provides rememberedColors,
            LocalDimensions provides rememberedDimensions,
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
