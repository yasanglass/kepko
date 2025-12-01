package glass.yasan.concrete.foundation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import glass.yasan.concrete.foundation.color.Colors
import glass.yasan.concrete.foundation.color.LocalColors
import glass.yasan.concrete.foundation.size.LocalSizes
import glass.yasan.concrete.foundation.size.Sizes

@Composable
public fun ConcreteTheme(
    colors: Colors = ConcreteTheme.colors,
    sizes: Sizes = ConcreteTheme.sizes,
    content: @Composable () -> Unit,
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    val rememberedSizes = remember { sizes.copy() }.apply { updateSizesFrom(sizes) }

    MaterialTheme(
        colorScheme = colors.toMaterial3Colors(),
    ) {
        CompositionLocalProvider(
            LocalColors provides rememberedColors,
            LocalSizes provides rememberedSizes,
            content = content
        )
    }
}

public object ConcreteTheme {

    public val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    public val sizes: Sizes
        @Composable
        @ReadOnlyComposable
        get() = LocalSizes.current
}
