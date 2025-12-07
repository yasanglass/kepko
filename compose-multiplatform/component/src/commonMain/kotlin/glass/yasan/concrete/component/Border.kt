package glass.yasan.concrete.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import glass.yasan.concrete.foundation.theme.ConcreteTheme
import androidx.compose.foundation.border as ComposeFoundationBorder

@Composable
public fun Modifier.border(
    color: Color = ConcreteTheme.colors.background,
    shape: Shape = RectangleShape,
): Modifier =
    ComposeFoundationBorder(
        width = ConcreteTheme.dimensions.borderStrokeWidth,
        brush = SolidColor(color),
        shape = shape,
    )

@Composable
public fun Modifier.border(
    brush: Brush,
    shape: Shape = RectangleShape,
): Modifier =
    ComposeFoundationBorder(
        width = ConcreteTheme.dimensions.borderStrokeWidth,
        brush = brush,
        shape = shape,
    )
