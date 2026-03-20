package glass.yasan.kepko.foundation.theme

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi

@ExperimentalKepkoApi
public class ColorPalettePreviewParameterProvider(
    override val values: Sequence<ColorPalette> = ColorPalette.entries.asSequence(),
) : PreviewParameterProvider<ColorPalette>
