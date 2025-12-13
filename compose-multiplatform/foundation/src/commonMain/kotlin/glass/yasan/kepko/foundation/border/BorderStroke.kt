package glass.yasan.kepko.foundation.border

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import androidx.compose.foundation.BorderStroke as MaterialBorderStroke

@Composable
public fun borderStroke(
    color: Color = KepkoTheme.colors.background,
    thickness: Dp = KepkoTheme.dimensions.borderThickness,
): MaterialBorderStroke =
    MaterialBorderStroke(
        width = thickness,
        color = color,
    )

@Composable
public fun borderStrokeFor(
    containerColor: Color,
): BorderStroke? = when (containerColor) {
    // Layer
    KepkoTheme.colors.foreground,
    KepkoTheme.colors.midground,
    KepkoTheme.colors.background -> borderStroke()
    // Other
    else -> null
}

