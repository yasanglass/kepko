package glass.yasan.kepko.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
public data class SegmentedPickerColors(
    val containerColor: Color,
    val indicatorColor: Color,
    val selectedContentColor: Color,
    val unselectedContentColor: Color,
    val disabledContentColor: Color,
    val outlineColor: Color,
)
