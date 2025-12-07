package glass.yasan.concrete.foundation.color

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import glass.yasan.toolkit.compose.color.darken
import glass.yasan.toolkit.compose.color.lighten

private const val CONTAINER_ALPHA = 0.75f
private const val LUMINANCE_THRESHOLD = 0.5f

public fun Color.container(): Color = copy(alpha = CONTAINER_ALPHA)

public fun Color.content(): Color =
    if (luminance() > LUMINANCE_THRESHOLD) {
        Color.Black
    } else {
        Color.White
    }

public fun Color.heighten(isDark: Boolean): Color = if (isDark) lighten() else darken()

public fun Color.lower(isDark: Boolean): Color = if (isDark) darken() else lighten()
