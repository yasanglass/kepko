package glass.yasan.concrete.foundation.size

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp

@Immutable
public class Sizes(
    public var borderStrokeWidth: Dp,
) {

    public fun copy(
        borderStrokeWidth: Dp = this.borderStrokeWidth,
    ): Sizes = Sizes(
        borderStrokeWidth = borderStrokeWidth,
    )

    public fun updateSizesFrom(
        other: Sizes,
    ) {
        borderStrokeWidth = other.borderStrokeWidth
    }
}

internal val LocalSizes: ProvidableCompositionLocal<Sizes> =
    compositionLocalOf {
        sizes()
    }
