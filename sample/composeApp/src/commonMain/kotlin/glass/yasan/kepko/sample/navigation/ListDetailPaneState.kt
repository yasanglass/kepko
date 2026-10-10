package glass.yasan.kepko.sample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * The list pane's width, which dragging the divider between the panes changes.
 */
@Stable
internal class ListDetailPaneState(
    listWidth: Dp,
) {
    companion object {
        val Saver: Saver<ListDetailPaneState, Float> = Saver(
            save = { state -> state.listWidth.value },
            restore = { width -> ListDetailPaneState(listWidth = width.dp) },
        )
    }

    var listWidth: Dp by mutableStateOf(listWidth)
        private set

    fun resizeList(
        by: Dp,
        range: ClosedRange<Dp>,
    ) {
        listWidth = (listWidth.coerceIn(range) + by).coerceIn(range)
    }
}

@Composable
internal fun rememberListDetailPaneState(): ListDetailPaneState = rememberSaveable(saver = ListDetailPaneState.Saver) {
    ListDetailPaneState(listWidth = ListDetailDefaults.ListWidth)
}
