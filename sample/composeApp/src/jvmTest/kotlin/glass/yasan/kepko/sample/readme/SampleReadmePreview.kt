package glass.yasan.kepko.sample.readme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.sample.HomeScreen
import glass.yasan.kepko.sample.ReadmeScreenshotTests.Companion.previewHeight
import glass.yasan.kepko.sample.ReadmeScreenshotTests.Companion.previewWidth

@ReadmePreview
@Composable
internal fun SampleLightReadmePreview() {
    KepkoTheme(style = ThemeStyle.defaultLight) {
        Box(modifier = Modifier.width(previewWidth).height(previewHeight)) {
            HomeScreen(onNavigateToTheme = {})
        }
    }
}

@ReadmePreview
@Composable
internal fun SampleDarkReadmePreview() {
    KepkoTheme(style = ThemeStyle.defaultDark) {
        Box(modifier = Modifier.width(previewWidth).height(previewHeight)) {
            HomeScreen(onNavigateToTheme = {})
        }
    }
}
