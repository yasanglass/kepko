package glass.yasan.kepko.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.IntSize

public object TextPillDefaults {

    @Immutable
    public class Animations(
        public val colorAnimationSpec: AnimationSpec<Color>,
        public val sizeAnimationSpec: FiniteAnimationSpec<IntSize>,
    )

    public fun animations(
        colorAnimationSpec: AnimationSpec<Color> = tween(durationMillis = 300),
        sizeAnimationSpec: FiniteAnimationSpec<IntSize> = spring(),
    ): Animations = Animations(
        colorAnimationSpec = colorAnimationSpec,
        sizeAnimationSpec = sizeAnimationSpec,
    )
}


