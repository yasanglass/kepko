package glass.yasan.kepko.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons

@Immutable
public data class Subtitle(
    public val text: String,
    public val icon: Painter,
    public val iconContentDescription: String? = null,
    public val modifier: Modifier = Modifier,
    public val textTransformation: (String) -> String = String::uppercase,
)

@Composable
public fun SubtitleBar(
    subtitle: Subtitle,
    modifier: Modifier = subtitle.modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
    ) {
        Icon(
            painter = subtitle.icon,
            contentDescription = subtitle.iconContentDescription,
            size = 14.dp,
            tint = KepkoTheme.colors.content,
        )
        Text(
            text = subtitle.textTransformation(subtitle.text),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@PreviewWithTest
@Composable
internal fun SubtitleBarLightPreview() {
    KepkoTheme(palette = LIGHT) { SubtitleBarPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SubtitleBarDarkPreview() {
    KepkoTheme(palette = DARK) { SubtitleBarPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SubtitleBarBlackPreview() {
    KepkoTheme(palette = BLACK) { SubtitleBarPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SubtitleBarSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { SubtitleBarPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SubtitleBarSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { SubtitleBarPreviewContent() }
}

@Composable
private fun SubtitleBarPreviewContent() {
    Midground {
        SubtitleBar(subtitle = Subtitle(text = "Work", icon = Icons.person))
    }
}
