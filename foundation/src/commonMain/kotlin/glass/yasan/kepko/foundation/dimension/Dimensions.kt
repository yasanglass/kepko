package glass.yasan.kepko.foundation.dimension

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp

@Immutable
public data class Dimensions(
    val borderThickness: Dp,
    val iconSize: Dp,
)

internal val LocalDimensions: ProvidableCompositionLocal<Dimensions> =
    compositionLocalOf {
        dimensions()
    }
