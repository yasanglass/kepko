package glass.yasan.kepko.sample.readme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.component.TextPill
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle


@ReadmePreview
@Composable
internal fun UsageLightReadmePreview() {
    KepkoTheme(style = ThemeStyle.defaultLight) {
        UsageReadmeContent()
    }
}

@Composable
private fun UsageReadmeContent() {
    Column(
        modifier = Modifier
            .width(200.dp)
            .background(KepkoTheme.colors.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = "Hello, Kepko!")
        TextPill(
            text = "Yasan Glass",
            containerColor = KepkoTheme.colors.information,
        )
    }
}
