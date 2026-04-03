@file:Suppress("ForbiddenImport")

package glass.yasan.kepko.foundation.shape

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import glass.yasan.kepko.foundation.annotation.DelicateKepkoShapesApi

@Immutable
public data class Shapes(
    val extraSmall: CornerBasedShape,
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val large: CornerBasedShape,
    val extraLarge: CornerBasedShape,
) {

    @DelicateKepkoShapesApi
    val rectangle: Shape = RectangleShape

    @DelicateKepkoShapesApi
    val circle: CornerBasedShape = CircleShape
}

internal val LocalShapes: ProvidableCompositionLocal<Shapes> =
    compositionLocalOf {
        shapes()
    }
