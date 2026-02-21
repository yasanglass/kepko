package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import glass.yasan.kepko.foundation.theme.KepkoTheme

public object ModalBottomSheetDefaults {

    public val shape: Shape = RectangleShape

    @Composable
    public fun colors(
        containerColor: Color = KepkoTheme.colors.midground,
        contentColor: Color = KepkoTheme.colors.content,
    ): ModalBottomSheetColors = ModalBottomSheetColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )
}
