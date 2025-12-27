package glass.yasan.kepko.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun AppIdentity(
    title: String,
    versionName: String,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    iconTint: Color? = KepkoTheme.colors.content,
    extras: Array<String> = emptyArray(),
    contentPadding: PaddingValues = PaddingValues(all = 16.dp),
) {
    val extrasString = remember {
        extras.joinToString(separator = " ") { it }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(contentPadding),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            icon?.let {
                Image(
                    painter = icon,
                    contentDescription = null,
                    colorFilter = iconTint?.let { color -> ColorFilter.tint(color) },
                    modifier = Modifier.requiredHeight(height = 24.dp),
                )
            }

            Text(
                text = "$title $versionName",
                fontSize = 14.sp,
                color = KepkoTheme.colors.content,
            )
        }

        if (extras.isNotEmpty()) {
            TextPill(
                text = extrasString,
                containerColor = KepkoTheme.colors.background,
            )
        }
    }
}

@Preview
@Composable
private fun AppVersionBannerPreview() {
    KepkoTheme {
        Foreground {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AppIdentity(
                    title = "Kepko",
                    icon = painterResource(resource = Res.drawable.ic_asterisk),
                    versionName = "1.0.0",
                    extras = arrayOf(100.toString(), "play", "flavor"),
                )
                HorizontalDivider()
                AppIdentity(
                    title = "Kepko",
                    icon = painterResource(resource = Res.drawable.ic_asterisk),
                    versionName = "1.0.0",
                )
                HorizontalDivider()
                AppIdentity(
                    title = "Kepko",
                    versionName = "1.0.0",
                    extras = arrayOf(100.toString(), "flavor"),
                )
            }
        }
    }
}
