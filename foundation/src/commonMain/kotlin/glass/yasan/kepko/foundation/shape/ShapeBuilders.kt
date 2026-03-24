package glass.yasan.kepko.foundation.shape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape

public fun shapes(
    extraSmall: CornerBasedShape = RoundedCornerShape(ShapeTokens.extraSmallCornerRadius),
    small: CornerBasedShape = RoundedCornerShape(ShapeTokens.smallCornerRadius),
    medium: CornerBasedShape = RoundedCornerShape(ShapeTokens.mediumCornerRadius),
    large: CornerBasedShape = RoundedCornerShape(ShapeTokens.largeCornerRadius),
    extraLarge: CornerBasedShape = RoundedCornerShape(ShapeTokens.extraLargeCornerRadius),
): Shapes = Shapes(
    extraSmall = extraSmall,
    small = small,
    medium = medium,
    large = large,
    extraLarge = extraLarge,
)
