package glass.yasan.kepko.navigation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal object ListDetailDefaults {
    val ListWidth: Dp = 360.dp
    val MinListWidth: Dp = 280.dp
    val DividerWidth: Dp = 1.dp
    val DividerHandleWidth: Dp = 16.dp
    const val MAX_LIST_WIDTH_FRACTION: Float = 0.5f
    const val PANE_STIFFNESS: Float = 300f
    const val LAYOUT_CHANGE_MILLIS: Int = 250

    fun listWidthRange(windowWidth: Dp): ClosedRange<Dp> =
        MinListWidth..maxOf(MinListWidth, windowWidth * MAX_LIST_WIDTH_FRACTION)
}
