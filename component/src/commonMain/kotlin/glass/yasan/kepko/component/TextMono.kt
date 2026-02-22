package glass.yasan.kepko.component

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text as Material3Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.resource.Fonts
import glass.yasan.kepko.resource.Strings

@Composable
public fun TextMono(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    lineHeight: TextUnit = TextUnit.Unspecified,
) {
    Material3Text(
        text = text,
        modifier = modifier,
        color = color,
        fontFamily = Fonts.rubikMonoFontFamily(),
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        lineHeight = lineHeight,
    )
}

@PreviewWithTest
@Composable
internal fun TextMonoLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { TextMonoPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextMonoDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { TextMonoPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextMonoBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { TextMonoPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextMonoSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { TextMonoPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextMonoSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { TextMonoPreviewContent() }
}

@Composable
private fun TextMonoPreviewContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(16.dp),
    ) {
        TextMono(text = Strings.persistenceLightThemeStyleTitle)
        TextMono(text = Strings.preferenceAnnotationActive, fontWeight = FontWeight.Bold)
        TextMono(text = Strings.themeStyleLightSolarized, fontSize = 12.sp)
        TextMono(text = Strings.persistenceGrayscaleTitle, fontSize = 24.sp)
        TextMono(text = Strings.persistenceLightThemeStyleDescription, maxLines = 1)
    }
}
