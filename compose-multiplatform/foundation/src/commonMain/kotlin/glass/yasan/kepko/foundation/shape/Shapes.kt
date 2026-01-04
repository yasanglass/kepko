package glass.yasan.kepko.foundation.shape

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Shape

public data class Shapes(
    val containerOuter: Shape,
    val container: Shape,
    val containerInner: Shape,
    val button: Shape,
)

internal val LocalShapes: ProvidableCompositionLocal<Shapes> =
    compositionLocalOf {
        shapes()
    }
