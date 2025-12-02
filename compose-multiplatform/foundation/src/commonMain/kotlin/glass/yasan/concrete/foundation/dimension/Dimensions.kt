package glass.yasan.concrete.foundation.dimension

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp

@Immutable
public class Dimensions(
    public var borderStrokeWidth: Dp,
) {

    public fun copy(
        borderStrokeWidth: Dp = this.borderStrokeWidth,
    ): Dimensions = Dimensions(
        borderStrokeWidth = borderStrokeWidth,
    )

    public fun updateDimensionsFrom(
        other: Dimensions,
    ) {
        borderStrokeWidth = other.borderStrokeWidth
    }
}

internal val LocalDimensions: ProvidableCompositionLocal<Dimensions> =
    compositionLocalOf {
        dimensions()
    }
