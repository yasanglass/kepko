package glass.yasan.kepko.sample.readme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle

@ReadmePreview
@Composable
internal fun ThemeStyleLightReadmePreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleDarkReadmePreview() {
    KepkoTheme(style = ThemeStyle.DARK) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleBlackReadmePreview() {
    KepkoTheme(style = ThemeStyle.BLACK) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleSolarizedLightReadmePreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleSolarizedDarkReadmePreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleCatppuccinLatteReadmePreview() {
    KepkoTheme(style = ThemeStyle.CATPPUCCIN_LATTE) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleCatppuccinFrappeReadmePreview() {
    KepkoTheme(style = ThemeStyle.CATPPUCCIN_FRAPPE) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleCatppuccinMacchiatoReadmePreview() {
    KepkoTheme(style = ThemeStyle.CATPPUCCIN_MACCHIATO) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleCatppuccinMochaReadmePreview() {
    KepkoTheme(style = ThemeStyle.CATPPUCCIN_MOCHA) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleGruvboxLightReadmePreview() {
    KepkoTheme(style = ThemeStyle.GRUVBOX_LIGHT) {
        ThemeStyleReadmeContent()
    }
}

@ReadmePreview
@Composable
internal fun ThemeStyleGruvboxDarkReadmePreview() {
    KepkoTheme(style = ThemeStyle.GRUVBOX_DARK) {
        ThemeStyleReadmeContent()
    }
}

@Composable
private fun ThemeStyleReadmeContent() {
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
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}
