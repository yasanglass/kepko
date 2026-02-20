package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme

public object AlertDialogDefaults {

    public val shape: Shape
        @Composable get() = KepkoTheme.shapes.extraLarge

    public val border: Brush
        @Composable get() = SolidColor(KepkoTheme.colors.outline)

    public val BorderThickness: Dp
        @Composable get() = KepkoTheme.dimensions.borderThickness

    public val TonalElevation: Dp = 0.dp

    @Composable
    public fun colors(
        containerColor: Color = KepkoTheme.colors.foreground,
        confirmButtonContainerColor: Color = KepkoTheme.colors.content,
        iconContentColor: Color = KepkoTheme.colors.content,
        titleContentColor: Color = KepkoTheme.colors.content,
        textContentColor: Color = KepkoTheme.colors.contentSubtle,
    ): AlertDialogColors = AlertDialogColors(
        containerColor = containerColor,
        confirmButtonContainerColor = confirmButtonContainerColor,
        iconContentColor = iconContentColor,
        titleContentColor = titleContentColor,
        textContentColor = textContentColor,
    )

}
