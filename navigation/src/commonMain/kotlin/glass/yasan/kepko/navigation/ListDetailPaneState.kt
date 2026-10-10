package glass.yasan.kepko.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import glass.yasan.kepko.persistence.LocalKepkoPersistenceManager

/**
 * The list pane's width, which dragging the divider changes and [saveListWidth] hands to [onSave].
 */
@Stable
internal class ListDetailPaneState(
    listWidth: Dp,
    private val onSave: (Dp) -> Unit = {},
) {
    var listWidth: Dp by mutableStateOf(listWidth)
        private set

    fun resizeList(
        by: Dp,
        range: ClosedRange<Dp>,
    ) {
        listWidth = (listWidth.coerceIn(range) + by).coerceIn(range)
    }

    fun saveListWidth() {
        onSave(listWidth)
    }
}

/**
 * Starts from the width the user last dragged the list to in this app, and saves it when a drag ends.
 */
@Composable
internal fun rememberListDetailPaneState(): ListDetailPaneState {
    val persistenceManager = LocalKepkoPersistenceManager.current
    return remember(persistenceManager) {
        ListDetailPaneState(
            listWidth = persistenceManager.listPaneWidth ?: ListDetailDefaults.ListWidth,
            onSave = { width -> persistenceManager.listPaneWidth = width },
        )
    }
}
