package glass.yasan.kepko.foundation.shape

import androidx.compose.foundation.shape.CornerBasedShape

public fun shapes(
    extraSmall: CornerBasedShape = ShapeTokens.extraSmall,
    small: CornerBasedShape = ShapeTokens.small,
    medium: CornerBasedShape = ShapeTokens.medium,
    large: CornerBasedShape = ShapeTokens.large,
    extraLarge: CornerBasedShape = ShapeTokens.extraLarge,
): Shapes = Shapes(
    extraSmall = extraSmall,
    small = small,
    medium = medium,
    large = large,
    extraLarge = extraLarge,
)
