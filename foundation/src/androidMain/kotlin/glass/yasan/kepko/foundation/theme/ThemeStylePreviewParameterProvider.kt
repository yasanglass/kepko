package glass.yasan.kepko.foundation.theme

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi

@ExperimentalKepkoApi
public class ThemeStylePreviewParameterProvider(
    override val values: Sequence<ThemeStyle> = ThemeStyle.entries.asSequence(),
) : PreviewParameterProvider<ThemeStyle>
