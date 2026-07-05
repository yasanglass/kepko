package glass.yasan.kepko.persistence

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

/**
 * [id] is used as the persistence key for this profile's overrides.
 */
public class UserVisibleProfile(
    public val id: String,
    public val name: @Composable () -> String,
    public val icon: @Composable () -> Painter,
) {
    public override fun equals(other: Any?): Boolean = other is UserVisibleProfile && id == other.id

    public override fun hashCode(): Int = id.hashCode()
}
