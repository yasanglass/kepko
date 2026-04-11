package glass.yasan.kepko.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.IntSize
import glass.yasan.kepko.foundation.theme.KepkoTheme

public object TextPillDefaults {

    public val shape: Shape
        @Composable get() = KepkoTheme.shapes.extraLarge

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


