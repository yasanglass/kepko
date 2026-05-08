package glass.yasan.kepko.component

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.theme.KepkoTheme

public object ExpandableColumnDefaults {
    public const val CHEVRON_ANIMATION_DURATION_MILLIS: Int = 250
    public const val FADE_DURATION_MILLIS: Int = 220
    public const val FADE_DELAY_MILLIS: Int = 24
    public const val ENTER_OFFSET_HEIGHT_DIVISOR: Int = 10
    public const val EXIT_OFFSET_HEIGHT_DIVISOR: Int = 16

    public val containerColor: Color
        @Composable get() = KepkoTheme.colors.midground

    @Composable
    public fun contentColor(
        containerColor: Color = ExpandableColumnDefaults.containerColor,
    ): Color = contentColorFor(containerColor)

    @Composable
    public fun colors(
        containerColor: Color = ExpandableColumnDefaults.containerColor,
        contentColor: Color = contentColor(containerColor),
    ): ExpandableColumnColors = ExpandableColumnColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )

    public val border: BorderStroke? = null

    public fun contentEnterTransition(
        sizeAnimationSpec: FiniteAnimationSpec<IntSize> = contentSizeAnimationSpec(),
        offsetAnimationSpec: FiniteAnimationSpec<IntOffset> = contentOffsetAnimationSpec(),
    ): EnterTransition = expandVertically(
        expandFrom = Alignment.Top,
        animationSpec = sizeAnimationSpec,
    ) + slideInVertically(
        animationSpec = offsetAnimationSpec,
        initialOffsetY = { fullHeight -> fullHeight / ENTER_OFFSET_HEIGHT_DIVISOR },
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = FADE_DURATION_MILLIS,
            delayMillis = FADE_DELAY_MILLIS,
        ),
    )

    public fun contentExitTransition(
        sizeAnimationSpec: FiniteAnimationSpec<IntSize> = contentSizeAnimationSpec(),
        offsetAnimationSpec: FiniteAnimationSpec<IntOffset> = contentOffsetAnimationSpec(),
    ): ExitTransition = shrinkVertically(
        shrinkTowards = Alignment.Top,
        animationSpec = sizeAnimationSpec,
    ) + slideOutVertically(
        animationSpec = offsetAnimationSpec,
        targetOffsetY = { fullHeight -> fullHeight / EXIT_OFFSET_HEIGHT_DIVISOR },
    ) + fadeOut(animationSpec = tween(durationMillis = FADE_DURATION_MILLIS))

    public fun contentSizeAnimationSpec(): FiniteAnimationSpec<IntSize> = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow,
    )

    public fun contentOffsetAnimationSpec(): FiniteAnimationSpec<IntOffset> = spring(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessMediumLow,
    )

    public fun chevronAnimationSpec(): AnimationSpec<Float> = tween(
        durationMillis = CHEVRON_ANIMATION_DURATION_MILLIS,
        easing = EaseInOut,
    )
}
