package glass.yasan.kepko.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
expect fun SystemBarColorsEffect(
    statusBarColor: Color,
    navigationBarColor: Color,
    isDark: Boolean,
)
