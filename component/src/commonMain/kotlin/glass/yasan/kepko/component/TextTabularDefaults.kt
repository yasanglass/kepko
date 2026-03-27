package glass.yasan.kepko.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi

@ExperimentalKepkoApi
public object TextTabularDefaults {

    public val defaultCandidates: List<Char> =
        ('0'..'9') + ('A'..'Z') + ('a'..'z') + listOf('.', '-', '+', ',', ':', ' ')

    public fun animationSpec(
        dampingRatio: Float = Spring.DampingRatioLowBouncy,
        stiffness: Float = Spring.StiffnessMedium,
    ): AnimationSpec<Float> = spring(
        dampingRatio = dampingRatio,
        stiffness = stiffness,
    )
}
