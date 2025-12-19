package glass.yasan.kepko.util

import glass.yasan.kepko.component.PreferenceAnnotation
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.foundation.theme.ThemeStyle

public fun ThemeStyle.Companion.asPreferenceRadioGroupItems(): List<PreferenceRadioGroupItem> =
    ThemeStyle.entries.map { style -> style.asPreferenceRadioGroupItem() }

public fun ThemeStyle.asPreferenceRadioGroupItem(): PreferenceRadioGroupItem = PreferenceRadioGroupItem(
    id = id,
    annotation = when (this) {
        ThemeStyle.LIGHT -> null
        ThemeStyle.DARK -> null
        ThemeStyle.DARK_AMOLED -> PreferenceAnnotation.new
        ThemeStyle.SOLARIZED_LIGHT -> PreferenceAnnotation.experimental
        ThemeStyle.SOLARIZED_DARK -> PreferenceAnnotation.experimental
    },
) {
    title()
}
