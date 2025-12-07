package glass.yasan.concrete.foundation.color

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import glass.yasan.toolkit.compose.color.darken
import glass.yasan.toolkit.compose.color.lighten

public fun Color.container(): Color = copy(alpha = 0.75f)

public fun Color.content(): Color =
    if (luminance() > 0.5f) {
        Color.Black
    } else {
        Color.White
    }

public fun Color.heighten(isDark: Boolean): Color = if (isDark) lighten() else darken()

public fun Color.lower(isDark: Boolean): Color = if (isDark) darken() else lighten()
