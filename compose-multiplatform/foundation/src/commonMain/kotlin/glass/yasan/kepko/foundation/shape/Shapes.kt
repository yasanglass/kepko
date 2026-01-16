package glass.yasan.kepko.foundation.shape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

public data class Shapes(
    val extraSmall: CornerBasedShape,
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val large: CornerBasedShape,
    val extraLarge: CornerBasedShape,
)

internal val LocalShapes: ProvidableCompositionLocal<Shapes> =
    compositionLocalOf {
        shapes()
    }
