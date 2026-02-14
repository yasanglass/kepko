package glass.yasan.kepko.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.KepkoTheme
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
