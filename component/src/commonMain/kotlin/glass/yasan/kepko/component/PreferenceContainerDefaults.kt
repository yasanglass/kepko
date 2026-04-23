package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.KepkoTheme

public object PreferenceContainerDefaults {
    @Composable
    public fun colors(
        containerColor: Color = KepkoTheme.colors.foreground,
        contentColor: Color = KepkoTheme.colors.content,
        descriptionColor: Color = KepkoTheme.colors.contentSubtle,
        disabledContainerColor: Color = containerColor,
        disabledContentColor: Color = KepkoTheme.colors.contentDisabled,
        disabledDescriptionColor: Color = KepkoTheme.colors.contentDisabled,
    ): PreferenceContainerColors = PreferenceContainerColors(
        containerColor = containerColor,
        contentColor = contentColor,
        descriptionColor = descriptionColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledDescriptionColor = disabledDescriptionColor,
    )
}
