package glass.yasan.kepko.sample.readme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.annotation.DelicateKepkoShapesApi
import glass.yasan.kepko.foundation.theme.KepkoTheme

@ReadmePreview
@Composable
internal fun ColorPaletteLightReadmePreview() {
    KepkoTheme(palette = LIGHT) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteDarkReadmePreview() {
    KepkoTheme(palette = DARK) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteBlackReadmePreview() {
    KepkoTheme(palette = BLACK) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteSolarizedLightReadmePreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteSolarizedDarkReadmePreview() {
    KepkoTheme(palette = SOLARIZED_DARK) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteCatppuccinLatteReadmePreview() {
    KepkoTheme(palette = CATPPUCCIN_LATTE) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteCatppuccinFrappeReadmePreview() {
    KepkoTheme(palette = CATPPUCCIN_FRAPPE) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteCatppuccinMacchiatoReadmePreview() {
    KepkoTheme(palette = CATPPUCCIN_MACCHIATO) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteCatppuccinMochaReadmePreview() {
    KepkoTheme(palette = CATPPUCCIN_MOCHA) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteGruvboxLightReadmePreview() {
    KepkoTheme(palette = GRUVBOX_LIGHT) {
        ColorPaletteReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ColorPaletteGruvboxDarkReadmePreview() {
    KepkoTheme(palette = GRUVBOX_DARK) {
        ColorPaletteReadmeContent()
    }
}

@OptIn(DelicateKepkoShapesApi::class)
@Composable
private fun ColorPaletteReadmeContent() {
    val colors = KepkoTheme.colors
    val swatchColors = listOf(
        colors.success,
        colors.information,
        colors.caution,
        colors.danger,
        colors.foreground,
        colors.midground,
        colors.background,
        colors.outline,
        colors.content,
        colors.contentSubtle,
        colors.contentDisabled,
    )
    Row(
        modifier = Modifier
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        swatchColors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(KepkoTheme.shapes.circle)
                    .background(color)
            )
        }
    }
}
