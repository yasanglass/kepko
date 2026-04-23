package glass.yasan.kepko.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
public data class SelectableChipColors(
    val unselectedContainerColor: Color,
    val unselectedContentColor: Color,
    val selectedContainerColor: Color,
    val selectedContentColor: Color,
    val outlineColor: Color,
)
