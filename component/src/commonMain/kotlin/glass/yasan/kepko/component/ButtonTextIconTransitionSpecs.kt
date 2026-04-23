package glass.yasan.kepko.component

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.painter.Painter

@Immutable
public data class ButtonTextIconTransitionSpecs(
    val leading: AnimatedContentTransitionScope<Painter?>.() -> ContentTransform,
    val trailing: AnimatedContentTransitionScope<Painter?>.() -> ContentTransform,
)
