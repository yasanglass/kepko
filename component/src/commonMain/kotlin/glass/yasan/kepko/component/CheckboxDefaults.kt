package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.KepkoTheme

public object CheckboxDefaults {
    @Composable
    public fun colors(
        checkedCheckmarkColor: Color = KepkoTheme.colors.foreground,
        uncheckedCheckmarkColor: Color = Color.Transparent,
        checkedBoxColor: Color = KepkoTheme.colors.content,
        uncheckedBoxColor: Color = Color.Transparent,
        disabledCheckedBoxColor: Color = KepkoTheme.colors.contentDisabled,
        disabledUncheckedBoxColor: Color = Color.Transparent,
        disabledIndeterminateBoxColor: Color = KepkoTheme.colors.contentDisabled,
        checkedBorderColor: Color = KepkoTheme.colors.content,
        uncheckedBorderColor: Color = KepkoTheme.colors.content,
        disabledBorderColor: Color = KepkoTheme.colors.contentDisabled,
        disabledUncheckedBorderColor: Color = KepkoTheme.colors.contentDisabled,
        disabledIndeterminateBorderColor: Color = KepkoTheme.colors.contentDisabled,
    ): CheckboxColors = CheckboxColors(
        checkedCheckmarkColor = checkedCheckmarkColor,
        uncheckedCheckmarkColor = uncheckedCheckmarkColor,
        checkedBoxColor = checkedBoxColor,
        uncheckedBoxColor = uncheckedBoxColor,
        disabledCheckedBoxColor = disabledCheckedBoxColor,
        disabledUncheckedBoxColor = disabledUncheckedBoxColor,
        disabledIndeterminateBoxColor = disabledIndeterminateBoxColor,
        checkedBorderColor = checkedBorderColor,
        uncheckedBorderColor = uncheckedBorderColor,
        disabledBorderColor = disabledBorderColor,
        disabledUncheckedBorderColor = disabledUncheckedBorderColor,
        disabledIndeterminateBorderColor = disabledIndeterminateBorderColor,
    )
}
