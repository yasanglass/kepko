package glass.yasan.kepko.foundation.dimension

import androidx.compose.ui.unit.Dp

public fun dimensions(
    borderThickness: Dp = DimensionTokens.borderThickness,
    iconSize: Dp = DimensionTokens.iconSize,
): Dimensions = Dimensions(
    borderThickness = borderThickness,
    iconSize = iconSize,
)
