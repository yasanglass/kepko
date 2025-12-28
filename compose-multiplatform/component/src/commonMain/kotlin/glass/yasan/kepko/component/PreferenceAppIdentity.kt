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
import glass.yasan.kepko.foundation.theme.ThemeStyle
import org.jetbrains.compose.resources.painterResource

@Composable
public fun PreferenceAppIdentity(
    title: String,
    versionName: String,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    iconTint: Color? = KepkoTheme.colors.content,
    extras: Array<String> = emptyArray(),
    annotation: PreferenceAnnotation? = null,
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

        if (extras.isNotEmpty() || annotation != null) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                if (extras.isNotEmpty()) {
                    TextPill(
                        text = extrasString,
                        containerColor = KepkoTheme.colors.background,
                    )
                }

                annotation?.let {
                    TextPill(it)
                }
            }
        }
    }
}

@PreviewWithTest
@Composable
internal fun PreferenceAppIdentityLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceAppIdentityDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceAppIdentityBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceAppIdentitySolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceAppIdentitySolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val title = "Kepko"
    val versionName = "1.0.0"

    Foreground {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PreferenceAppIdentity(
                title = title,
                versionName = versionName,
                icon = painterResource(resource = Res.drawable.ic_asterisk),
                extras = arrayOf(100.toString(), "flavor"),
                annotation = PreferenceAnnotation.beta,
            )
            HorizontalDivider()
            PreferenceAppIdentity(
                title = title,
                icon = painterResource(resource = Res.drawable.ic_asterisk),
                versionName = versionName,
                extras = arrayOf("sega", "bodega", "2023"),
            )
            HorizontalDivider()
            PreferenceAppIdentity(
                title = title,
                icon = painterResource(resource = Res.drawable.ic_asterisk),
                versionName = "1.0.0",
            )
            HorizontalDivider()
            PreferenceAppIdentity(
                title = title,
                versionName = versionName,
                extras = arrayOf(100.toString(), "flavor"),
            )
            HorizontalDivider()
            PreferenceAppIdentity(
                title = title,
                versionName = versionName,
            )
        }
    }
}
