package glass.yasan.concrete.foundation.color

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

@Composable
public fun contentColor(
    backgroundColor: Color,
): Color =
    if (backgroundColor.luminance() > 0.5) {
        Color.Black
    } else {
        Color.White
    }

