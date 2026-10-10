package glass.yasan.kepko.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.navigation3.scene.Scene
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi

/**
 * Whether only the layout changed, as when a resized window becomes large or stops being large, not the screen.
 */
@ExperimentalKepkoApi
public fun <T : Any> AnimatedContentTransitionScope<Scene<T>>.isLayoutChange(): Boolean =
    initialState.entries.lastOrNull()?.contentKey == targetState.entries.lastOrNull()?.contentKey

/**
 * Cross-fades a layout change instead of sliding, since the same screen stays on top.
 */
@ExperimentalKepkoApi
public fun layoutChangeTransform(): ContentTransform {
    val spec = tween<Float>(durationMillis = ListDetailDefaults.LAYOUT_CHANGE_MILLIS)
    return fadeIn(spec) togetherWith fadeOut(spec)
}
