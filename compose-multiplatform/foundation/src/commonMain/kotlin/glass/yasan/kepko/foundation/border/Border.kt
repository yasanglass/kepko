package glass.yasan.kepko.foundation.border

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import androidx.compose.foundation.border as ComposeFoundationBorder

@Composable
public fun Modifier.border(
    color: Color = KepkoTheme.colors.outline,
    shape: Shape = RectangleShape,
    width: Dp = KepkoTheme.dimensions.borderThickness,
): Modifier =
    ComposeFoundationBorder(
        width = width,
        brush = SolidColor(value = color),
        shape = shape,
    )

@Composable
public fun Modifier.border(
    brush: Brush,
    shape: Shape = RectangleShape,
    width: Dp = KepkoTheme.dimensions.borderThickness,
): Modifier =
    ComposeFoundationBorder(
        width = width,
        brush = brush,
        shape = shape,
    )
