package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi

internal val LocalTitleBarBackButtonVisible: ProvidableCompositionLocal<Boolean> =
    staticCompositionLocalOf { true }

/**
 * Hides the back button of the outermost [Scaffold]s in [content]; scaffolds nested in their content keep theirs.
 */
@ExperimentalKepkoApi
@Composable
public fun ProvideTitleBarBackButton(
    visible: Boolean,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalTitleBarBackButtonVisible provides visible, content = content)
}
