package glass.yasan.kepko.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.Badge
import glass.yasan.kepko.component.Icon
import glass.yasan.kepko.component.IconButton
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.component.TitleBar
import glass.yasan.kepko.resource.Icons

@Suppress("LongMethod")
@Composable
internal fun TitleBarScreen(onBackClick: () -> Unit) {
    Scaffold(
        title = "TitleBar",
        onBackClick = onBackClick,
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp),
        ) {
            TitleBar(title = "Basic")

            TitleBar(
                title = "With back",
                onBackClick = {},
            )

            TitleBar(
                title = "Centered",
                textAlign = TextAlign.Center,
            )

            TitleBar(
                title = "Reversed",
                reverse = true,
            )

            TitleBar(
                title = "Reversed back",
                onBackClick = {},
                reverse = true,
            )

            TitleBar(
                title = "Badge",
                badge = Badge.new,
            )

            TitleBar(
                title = "Trailing",
                trailingContent = {
                    IconButton(
                        painter = Icons.settings,
                        contentDescription = null,
                        onClick = {},
                    )
                },
            )

            TitleBar(
                title = "Trailing & badge",
                badge = Badge.experimental,
                trailingContent = {
                    IconButton(
                        painter = Icons.settings,
                        contentDescription = null,
                        onClick = {},
                    )
                },
            )

            TitleBar(
                title = "Back, trailing & badge",
                onBackClick = {},
                badge = Badge.beta,
                trailingContent = {
                    IconButton(
                        painter = Icons.settings,
                        contentDescription = null,
                        onClick = {},
                    )
                },
            )

            TitleBar {
                Icon(
                    painter = Icons.shapes,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 16.dp),
                )
                Text(
                    text = "Custom slot content",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}
