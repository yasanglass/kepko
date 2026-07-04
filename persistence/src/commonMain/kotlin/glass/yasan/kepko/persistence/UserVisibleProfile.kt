package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

/**
 * [id] is used as the persistence key for this profile's overrides.
 */
public data class UserVisibleProfile(
    val id: String,
    val name: @Composable () -> String,
    val icon: @Composable () -> Painter,
)
