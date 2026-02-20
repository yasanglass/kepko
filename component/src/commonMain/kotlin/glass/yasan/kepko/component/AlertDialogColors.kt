package glass.yasan.kepko.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
public data class AlertDialogColors(
    val containerColor: Color,
    val confirmButtonContainerColor: Color,
    val iconContentColor: Color,
    val titleContentColor: Color,
    val textContentColor: Color,
)
