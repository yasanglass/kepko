package glass.yasan.kepko.component

import androidx.compose.runtime.Composable

/**
 * @param segment optional segment key to group items based on
 * in [PreferenceRadioGroup] and [PreferenceRadioGroupPicker].
 * */
public data class PreferenceRadioGroupItem(
    val id: String,
    val annotation: PreferenceAnnotation? = null,
    val segment: Int = 0,
    val enabled: Boolean = true,
    val title: @Composable () -> String
)
