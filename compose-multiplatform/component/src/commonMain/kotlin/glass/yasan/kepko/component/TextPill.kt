package glass.yasan.kepko.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.takeOrElse
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.border.borderStrokeFor
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@ExperimentalKepkoApi
@Composable
public fun TextPill(
    annotation: PreferenceAnnotation,
    modifier: Modifier = Modifier,
) {
    TextPill(
        text = annotation.text(),
        containerColor = annotation.containerColor(),
        contentColor = annotation.contentColor(),
        modifier = modifier,
    )
}

@Composable
public fun TextPill(
    text: String,
    containerColor: Color,
    modifier: Modifier = Modifier,
    contentColor: Color = contentColorFor(containerColor),
    border: BorderStroke? = borderStrokeFor(containerColor),
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Medium,
    textTransformation: (String) -> String = { it.uppercase() },
) {
    Text(
        text = textTransformation(text),
        fontSize = fontSize.takeOrElse { 10.sp },
        color = contentColor,
        fontWeight = fontWeight,
        modifier = modifier
            .then(if (border != null) Modifier.border(border, CircleShape) else Modifier)
            .clip(shape = CircleShape)
            .background(color = containerColor)
            .padding(horizontal = 12.dp)
    )
}

@Preview
@Composable
private fun TextPillPreview() {
    KepkoTheme {
        val containerColors = KepkoTheme.colors.getSemanticColors() +
                KepkoTheme.colors.foreground +
                KepkoTheme.colors.content

        Column(
            modifier = Modifier.background(KepkoTheme.colors.foreground)
        ) {
            containerColors.forEach { containerColor ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(
                        vertical = 2.dp,
                        horizontal = 4.dp,
                    ),
                ) {
                    TextPill(
                        text = "Label",
                        containerColor = containerColor,
                    )
                }
            }
        }
    }
}
