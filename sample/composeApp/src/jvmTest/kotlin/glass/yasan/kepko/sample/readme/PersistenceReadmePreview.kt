package glass.yasan.kepko.sample.readme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.persistence.PersistentPreferenceThemeScreen
import glass.yasan.kepko.persistence.PreviewPersistentKepkoTheme
import glass.yasan.kepko.sample.ReadmeScreenshotTests.Companion.previewHeight
import glass.yasan.kepko.sample.ReadmeScreenshotTests.Companion.previewWidth

@ReadmePreview
@Composable
internal fun PersistentPreferenceThemeScreenLightReadmePreview() {
    PreviewPersistentKepkoTheme(configure = { stylePrimary = ThemeStyle.LIGHT }) {
        Box(modifier = Modifier.width(previewWidth).height(previewHeight)) {
            PersistentPreferenceThemeScreen(onBackClick = {})
        }
    }
}

@ReadmePreview
@Composable
internal fun PersistentPreferenceThemeScreenDarkReadmePreview() {
    PreviewPersistentKepkoTheme(
        isSystemInDarkTheme = true,
        configure = { stylePrimary = ThemeStyle.DARK },
    ) {
        Box(modifier = Modifier.width(previewWidth).height(previewHeight)) {
            PersistentPreferenceThemeScreen(onBackClick = {})
        }
    }
}
