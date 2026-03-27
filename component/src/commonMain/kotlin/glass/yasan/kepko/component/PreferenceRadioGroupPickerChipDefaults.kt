package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.theme.KepkoTheme

public object PreferenceRadioGroupPickerChipDefaults {

    @Composable
    public fun colors(
        containerColor: Color = KepkoTheme.colors.foreground,
        contentColor: Color = contentColorFor(containerColor),
        outlineColor: Color = KepkoTheme.colors.outline,
    ): PreferenceRadioGroupPickerChipColors = PreferenceRadioGroupPickerChipColors(
        containerColor = containerColor,
        contentColor = contentColor,
        outlineColor = outlineColor,
    )
}
