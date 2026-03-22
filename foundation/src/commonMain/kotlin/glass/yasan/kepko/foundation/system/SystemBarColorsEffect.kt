package glass.yasan.kepko.foundation.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
public expect fun SystemBarColorsEffect(
    statusBarColor: Color,
    navigationBarColor: Color,
    isDark: Boolean,
)
