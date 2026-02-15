package glass.yasan.kepko.util

import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.foundation.theme.ThemeStyle

public fun ThemeStyle.Companion.asPreferenceRadioGroupItems(): List<PreferenceRadioGroupItem> =
    ThemeStyle.entries.map { style -> style.asPreferenceRadioGroupItem() }

public fun ThemeStyle.asPreferenceRadioGroupItem(): PreferenceRadioGroupItem = PreferenceRadioGroupItem(
    id = id,
) {
    title()
}
