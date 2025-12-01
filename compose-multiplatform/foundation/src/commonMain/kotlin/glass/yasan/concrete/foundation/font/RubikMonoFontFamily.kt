package glass.yasan.concrete.foundation.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import glass.yasan.concrete.foundation.Res
import glass.yasan.concrete.foundation.rubik_mono
import org.jetbrains.compose.resources.Font

@Composable
public fun rubikMonoFontFamily(): FontFamily = FontFamily(
    Font(Res.font.rubik_mono),
)
