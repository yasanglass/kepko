package glass.yasan.kepko.foundation.color

import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.Dp

@Composable
public fun ProvideLocalMinimumInteractiveComponentSize(
    size: Dp?,
    content: @Composable () -> Unit,
) {
    if (size != null) {
        CompositionLocalProvider(
            value = LocalMinimumInteractiveComponentSize provides size,
        ) {
            content()
        }
    } else {
        content()
    }
}
