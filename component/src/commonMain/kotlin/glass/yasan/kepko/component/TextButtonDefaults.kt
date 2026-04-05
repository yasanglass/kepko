package glass.yasan.kepko.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.KepkoTheme
import androidx.compose.material3.ButtonDefaults as Material3ButtonDefaults

public object TextButtonDefaults {
    public val ContentPadding: PaddingValues = Material3ButtonDefaults.TextButtonContentPadding

    @Composable
    public fun colors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = KepkoTheme.colors.content,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = KepkoTheme.colors.contentDisabled,
    ): TextButtonColors = TextButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )
}
