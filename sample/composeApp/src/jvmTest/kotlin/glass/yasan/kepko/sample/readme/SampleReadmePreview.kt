package glass.yasan.kepko.sample.readme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import glass.yasan.kepko.persistence.PreviewPersistentKepkoTheme
import glass.yasan.kepko.sample.HomeScreen
import glass.yasan.kepko.sample.ReadmeScreenshotTests.Companion.previewHeight
import glass.yasan.kepko.sample.ReadmeScreenshotTests.Companion.previewWidth

@ReadmePreview
@Composable
internal fun SampleLightReadmePreview() {
    PreviewPersistentKepkoTheme(isSystemInDarkTheme = false) {
        Box(modifier = Modifier.width(previewWidth).height(previewHeight)) {
            HomeScreen(
                onNavigateToTheme = {},
                onNavigateToProfiles = {},
                onNavigateToIcons = {},
                onNavigateToSerialization = {},
                onNavigateToTitleBar = {},
            )
        }
    }
}

@ReadmePreview
@Composable
internal fun SampleDarkReadmePreview() {
    PreviewPersistentKepkoTheme(isSystemInDarkTheme = true) {
        Box(modifier = Modifier.width(previewWidth).height(previewHeight)) {
            HomeScreen(
                onNavigateToTheme = {},
                onNavigateToProfiles = {},
                onNavigateToIcons = {},
                onNavigateToSerialization = {},
                onNavigateToTitleBar = {},
            )
        }
    }
}
