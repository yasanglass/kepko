package glass.yasan.kepko.component.extensions

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo

@Composable
public fun BoxWithConstraintsScope.isFullWidth(): Boolean {
    val screenWidthPixels = LocalWindowInfo.current.containerSize.width

    return constraints.maxWidth >= screenWidthPixels
}
