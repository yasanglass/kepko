package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Strings
import androidx.compose.material3.LinearProgressIndicator as Material3LinearProgressIndicator
import androidx.compose.material3.CircularProgressIndicator as Material3CircularProgressIndicator

@Composable
public fun CircularProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.color,
    trackColor: Color = ProgressIndicatorDefaults.trackColor,
) {
    Material3CircularProgressIndicator(
        progress = progress,
        modifier = modifier,
        color = color,
        trackColor = trackColor,
    )
}

@Composable
public fun CircularProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.color,
    trackColor: Color = ProgressIndicatorDefaults.trackColor,
) {
    Material3CircularProgressIndicator(
        modifier = modifier,
        color = color,
        trackColor = trackColor,
    )
}

@Composable
public fun LinearProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.color,
    trackColor: Color = ProgressIndicatorDefaults.trackColor,
) {
    Material3LinearProgressIndicator(
        progress = progress,
        modifier = modifier,
        color = color,
        trackColor = trackColor,
    )
}

@Composable
public fun LinearProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.color,
    trackColor: Color = ProgressIndicatorDefaults.trackColor,
) {
    Material3LinearProgressIndicator(
        modifier = modifier,
        color = color,
        trackColor = trackColor,
    )
}

public object ProgressIndicatorDefaults {

    public val color: Color
        @Composable get() = KepkoTheme.colors.content

    public val trackColor: Color
        @Composable get() = KepkoTheme.colors.contentSubtle

}

@PreviewWithTest
@Composable
internal fun ProgressIndicatorLightPreview() {
    KepkoTheme(palette = LIGHT) { ProgressIndicatorPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ProgressIndicatorDarkPreview() {
    KepkoTheme(palette = DARK) { ProgressIndicatorPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ProgressIndicatorBlackPreview() {
    KepkoTheme(palette = BLACK) { ProgressIndicatorPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ProgressIndicatorSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { ProgressIndicatorPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ProgressIndicatorSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { ProgressIndicatorPreviewContent() }
}

@Composable
private fun ProgressIndicatorPreviewContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(16.dp),
    ) {
        Text(text = Strings.preferencePaletteTitle)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CircularProgressIndicator()
            CircularProgressIndicator(progress = { 0.5f })
        }
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        LinearProgressIndicator(
            progress = { 0.5f },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
