package glass.yasan.concrete.component

import androidx.compose.material3.Text as Material3Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import glass.yasan.concrete.foundation.font.rubikMonoFontFamily
import glass.yasan.concrete.foundation.theme.ConcreteTheme

@Composable
public fun TextMono(
    text: String,
    modifier: Modifier = Modifier,
    prominence: Prominence = NORMAL,
    color: Color? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
) {
    Material3Text(
        text = text,
        modifier = modifier,
        color = color ?: when (prominence) {
            SUBTLE -> ConcreteTheme.colors.contentSubtle
            else -> ConcreteTheme.colors.content
        },
        fontFamily = rubikMonoFontFamily(),
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
    )
}
