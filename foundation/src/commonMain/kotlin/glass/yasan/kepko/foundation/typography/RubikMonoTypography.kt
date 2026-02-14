package glass.yasan.kepko.foundation.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import glass.yasan.kepko.resource.Fonts

@Composable
public fun rubikMonoTypography(): Typography = typography(
    fontFamily = Fonts.rubikMonoFontFamily(),
)
