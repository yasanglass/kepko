package glass.yasan.kepko.foundation.shape

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Shape

public fun shapes(
    containerOuter: Shape = CircleShape,
    container: Shape = CircleShape,
    containerInner: Shape = CircleShape,
    button: Shape = CircleShape,
): Shapes = Shapes(
    containerOuter = containerOuter,
    container = container,
    containerInner = containerInner,
    button = button,
)
