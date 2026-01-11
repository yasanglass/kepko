package glass.yasan.kepko.foundation.shape

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

public fun shapes(
    containerOuter: Shape = RoundedCornerShape(40.dp),
    container: Shape = RoundedCornerShape(32.dp),
    containerInner: Shape = RoundedCornerShape(24.dp),
    button: Shape = CircleShape,
): Shapes = Shapes(
    containerOuter = containerOuter,
    container = container,
    containerInner = containerInner,
    button = button,
)
