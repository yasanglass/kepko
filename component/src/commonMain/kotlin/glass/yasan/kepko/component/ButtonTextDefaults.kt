package glass.yasan.kepko.component

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme

public object ButtonTextDefaults {
    public val ContentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)

    @Composable
    public fun shape(): Shape = KepkoTheme.shapes.extraLarge

    @Composable
    public fun contentPadding(
        contentPadding: PaddingValues = ContentPadding,
    ): PaddingValues = contentPadding

    public fun iconTransitionSpecs(
        leading: AnimatedContentTransitionScope<Painter?>.() -> ContentTransform =
            iconTransitionSpec(slideFromStart = true),
        trailing: AnimatedContentTransitionScope<Painter?>.() -> ContentTransform =
            iconTransitionSpec(slideFromStart = false),
    ): ButtonTextIconTransitionSpecs = ButtonTextIconTransitionSpecs(
        leading = leading,
        trailing = trailing,
    )

    private fun iconTransitionSpec(
        slideFromStart: Boolean,
    ): AnimatedContentTransitionScope<Painter?>.() -> ContentTransform = {
        val sign = if (slideFromStart) -1 else 1
        val duration = 400
        (slideInHorizontally { it * sign } + fadeIn(tween(duration, easing = EaseIn)))
            .togetherWith(slideOutHorizontally { it * sign } + fadeOut(tween(duration, easing = EaseOut)))
    }
}
