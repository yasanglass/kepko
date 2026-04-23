package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.KepkoTheme

public object SelectableChipDefaults {

    @Composable
    public fun colors(
        unselectedContainerColor: Color = KepkoTheme.colors.foreground,
        unselectedContentColor: Color = KepkoTheme.colors.content,
        selectedContainerColor: Color = KepkoTheme.colors.content,
        selectedContentColor: Color = KepkoTheme.colors.foreground,
        outlineColor: Color = KepkoTheme.colors.outline,
    ): SelectableChipColors = SelectableChipColors(
        unselectedContainerColor = unselectedContainerColor,
        unselectedContentColor = unselectedContentColor,
        selectedContainerColor = selectedContainerColor,
        selectedContentColor = selectedContentColor,
        outlineColor = outlineColor,
    )
}
