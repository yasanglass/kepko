package glass.yasan.concrete.foundation.color

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

private const val CONTAINER_ALPHA = 0.75f
private const val LUMINANCE_THRESHOLD = 0.5f

public fun Color.toContainerColor(): Color = copy(alpha = CONTAINER_ALPHA)

public fun Color.toContentColor(): Color =
    if (luminance() > LUMINANCE_THRESHOLD) {
        Color.Black
    } else {
        Color.White
    }
