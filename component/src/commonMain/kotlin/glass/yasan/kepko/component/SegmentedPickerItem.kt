package glass.yasan.kepko.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.painter.Painter

/**
 * @see SegmentedPicker
 */
@Immutable
public data class SegmentedPickerItem<T>(
    val value: T,
    val icon: Painter? = null,
    val text: String? = null,
    val contentDescription: String? = null,
    val enabled: Boolean = true,
)
