package glass.yasan.kepko.foundation.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import glass.yasan.kepko.resource.Fonts
import org.jetbrains.compose.resources.Font

@Composable
public fun rubikMonoFontFamily(): FontFamily = FontFamily(
    rubikMonoFont(),
)

@Composable
public fun rubikMonoFont(): Font = Font(Fonts.rubikMono)
