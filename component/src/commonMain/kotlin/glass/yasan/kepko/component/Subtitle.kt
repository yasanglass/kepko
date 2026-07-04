package glass.yasan.kepko.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Immutable
public data class Subtitle(
    public val text: String,
    public val icon: Painter,
    public val iconContentDescription: String? = null,
    public val modifier: Modifier = Modifier,
    public val textTransformation: (String) -> String = String::uppercase,
)
