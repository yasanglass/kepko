package glass.yasan.kepko.component

import androidx.compose.material3.ExperimentalMaterial3Api
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
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.resource.Strings
import androidx.compose.material3.LinearProgressIndicator as Material3LinearProgressIndicator
import androidx.compose.material3.CircularProgressIndicator as Material3CircularProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
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

@OptIn(ExperimentalMaterial3Api::class)
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
    KepkoTheme(style = ThemeStyle.LIGHT) { ProgressIndicatorPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ProgressIndicatorDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { ProgressIndicatorPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ProgressIndicatorBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { ProgressIndicatorPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ProgressIndicatorSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { ProgressIndicatorPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ProgressIndicatorSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { ProgressIndicatorPreviewContent() }
}

@Composable
private fun ProgressIndicatorPreviewContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(16.dp),
    ) {
        Text(text = Strings.persistenceThemeTitle)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CircularProgressIndicator()
        }
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
}
