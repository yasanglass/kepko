package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.painter.Painter

/**
 * @param segment optional segment key used to visually group items.
 *
 * @see PreferenceRadioGroup
 * @see PreferenceRadioGroupPicker
 * @see PreferenceRadioGroupPickerChip
 * @see PreferenceRadioGroupSheet
 */
@Immutable
public data class PreferenceRadioGroupItem(
    val id: String,
    val annotation: PreferenceAnnotation? = null,
    val segment: Int = 0,
    val enabled: Boolean = true,
    val icon: Painter? = null,
    val title: @Composable () -> String
)
