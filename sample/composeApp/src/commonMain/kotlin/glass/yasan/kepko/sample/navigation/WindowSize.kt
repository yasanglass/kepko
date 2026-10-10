package glass.yasan.kepko.sample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Whether the window is at least tablet-sized, as on tablets, Chromebooks and desktops.
 */
@Composable
internal fun isLargeWindow(): Boolean = isLargeWindow(LocalWindowInfo.current.containerDpSize)

internal fun isLargeWindow(size: DpSize): Boolean = minOf(size.width, size.height) >= 600.dp

/**
 * Whether the window is large and wide enough to show a list and its detail side by side.
 */
@Composable
internal fun isTwoPaneWindow(): Boolean = isTwoPaneWindow(LocalWindowInfo.current.containerDpSize)

internal fun isTwoPaneWindow(size: DpSize): Boolean = isLargeWindow(size) && size.width >= 840.dp
