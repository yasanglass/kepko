package glass.yasan.kepko.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

public object SegmentedPickerDefaults {

    public val ContentPaddingHorizontal: Dp = 18.dp
    public val ContentPaddingVertical: Dp = 16.dp
    public val IndicatorInset: Dp = 3.dp
    public val RevealDuration: Duration = 1.3.seconds

    @Composable
    public fun shape(): Shape = KepkoTheme.shapes.extraLarge

    public fun contentPadding(
        horizontal: Dp = ContentPaddingHorizontal,
        vertical: Dp = ContentPaddingVertical,
    ): PaddingValues = PaddingValues(horizontal = horizontal, vertical = vertical)

    @Composable
    public fun colors(
        containerColor: Color = KepkoTheme.colors.background,
        indicatorColor: Color = KepkoTheme.colors.foreground,
        selectedContentColor: Color = KepkoTheme.colors.content,
        unselectedContentColor: Color = KepkoTheme.colors.contentSubtle,
        disabledContentColor: Color = KepkoTheme.colors.contentDisabled,
        outlineColor: Color = KepkoTheme.colors.outline,
    ): SegmentedPickerColors = SegmentedPickerColors(
        containerColor = containerColor,
        indicatorColor = indicatorColor,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,
        disabledContentColor = disabledContentColor,
        outlineColor = outlineColor,
    )
}
