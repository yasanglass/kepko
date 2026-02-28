package glass.yasan.kepko.persistence

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.component.ModalBottomSheet
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.resource.Strings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ColorPaletteBottomSheet(
    style: ThemeStyle,
    grayscale: Boolean,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                text = Strings.persistenceColorPaletteTitle,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 12.dp),
            )
            Text(
                text = style.title(),
                color = KepkoTheme.colors.contentSubtle,
                modifier = Modifier.padding(bottom = 12.dp),
            )
            ColorPaletteContent(
                style = style,
                grayscale = grayscale,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 24.dp),
            )
        }
    }
}

@Composable
private fun ColorPaletteContent(
    style: ThemeStyle,
    grayscale: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
    ) {
        KepkoTheme(style = style, grayscale = grayscale) {
            val colors = KepkoTheme.colors
            val swatches = listOf(
                Strings.colorSuccess to colors.success,
                Strings.colorInformation to colors.information,
                Strings.colorCaution to colors.caution,
                Strings.colorDanger to colors.danger,
                Strings.colorForeground to colors.foreground,
                Strings.colorMidground to colors.midground,
                Strings.colorBackground to colors.background,
                Strings.colorOutline to colors.outline,
                Strings.colorContent to colors.content,
                Strings.colorContentSubtle to colors.contentSubtle,
                Strings.colorContentDisabled to colors.contentDisabled,
            )

            for ((label, color) in swatches) {
                ColorSwatchItem(label = label, color = color)
            }
        }
    }
}

@Composable
private fun ColorSwatchItem(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(KepkoTheme.shapes.small)
            .background(color)
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = label,
            color = contentColorFor(containerColor = color),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@PreviewWithTest
@Composable
internal fun ColorPaletteContentLightPreview() {
    ColorPaletteContentPreview(style = ThemeStyle.LIGHT)
}

@PreviewWithTest
@Composable
internal fun ColorPaletteContentDarkPreview() {
    ColorPaletteContentPreview(style = ThemeStyle.DARK)
}

@PreviewWithTest
@Composable
internal fun ColorPaletteContentBlackPreview() {
    ColorPaletteContentPreview(style = ThemeStyle.BLACK)
}

@PreviewWithTest
@Composable
internal fun ColorPaletteContentSolarizedLightPreview() {
    ColorPaletteContentPreview(style = ThemeStyle.SOLARIZED_LIGHT)
}

@PreviewWithTest
@Composable
internal fun ColorPaletteContentSolarizedDarkPreview() {
    ColorPaletteContentPreview(style = ThemeStyle.SOLARIZED_DARK)
}

@Composable
private fun ColorPaletteContentPreview(style: ThemeStyle) {
    PreviewPersistentKepkoTheme(
        isSystemInDarkTheme = style.isDark,
        configure = { stylePrimary = style },
    ) {
        ColorPaletteContent(
            style = style,
            grayscale = false,
            modifier = Modifier.padding(16.dp),
        )
    }
}
