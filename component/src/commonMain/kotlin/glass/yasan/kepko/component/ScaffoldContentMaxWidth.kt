package glass.yasan.kepko.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.isSpecified
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi

internal val LocalScaffoldContentMaxWidth: ProvidableCompositionLocal<Dp> =
    staticCompositionLocalOf { Dp.Unspecified }

/**
 * Caps and centers the content and bottom bar of every [Scaffold] inside [content]; title bars stay full width.
 */
@ExperimentalKepkoApi
@Composable
public fun ProvideScaffoldContentMaxWidth(
    maxWidth: Dp,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalScaffoldContentMaxWidth provides maxWidth, content = content)
}

internal fun Modifier.centeredMaxWidth(maxWidth: Dp): Modifier = if (maxWidth.isSpecified) {
    fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
        .widthIn(max = maxWidth)
} else {
    this
}
