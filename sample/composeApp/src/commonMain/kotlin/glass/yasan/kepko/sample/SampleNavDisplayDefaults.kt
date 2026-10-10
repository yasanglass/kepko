package glass.yasan.kepko.sample

import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

internal object SampleNavDisplayDefaults {
    val BackStackSaver = listSaver<SnapshotStateList<Route>, String>(
        save = { backStack -> backStack.map(Route::path) },
        restore = { paths -> paths.mapNotNull(Route::fromPath).ifEmpty { listOf(Route.Home) }.toMutableStateList() },
    )
}
