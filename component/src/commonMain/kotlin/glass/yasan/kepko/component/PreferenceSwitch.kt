package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalKepkoApi::class)
@Composable
public fun PreferenceSwitch(
    title: String,
    checked: Boolean,
    leadingIcon: Painter,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
    badge: Badge? = null,
) {
    PreferenceSwitch(
        title = title,
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        description = description,
        enabled = enabled,
        badge = badge,
        leadingContent = {
            Icon(
                painter = leadingIcon,
                contentDescription = null,
            )
        },
    )
}

@OptIn(ExperimentalKepkoApi::class)
@Composable
public fun PreferenceSwitch(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
    leadingContent: @Composable () -> Unit = {},
    badge: Badge? = null,
) {
    PreferenceContainer(
        title = title,
        description = description,
        onClick = { onCheckedChange(!checked) },
        enabled = enabled,
        badge = badge,
        modifier = modifier,
        leadingContent = leadingContent,
        trailingContent = {
            Switch(
                enabled = enabled,
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
    )
}

@PreviewWithTest
@Composable
internal fun PreferenceSwitchLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSwitchDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSwitchBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSwitchSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSwitchSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val badges = arrayOf(Badge.new, null)
    val descriptions = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        null
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.midground)
            .padding(vertical = 16.dp),
    ) {
        badges.forEach { badge ->
            descriptions.forEach { description ->
                PreferenceSwitch(
                    title = "Preference Switch",
                    description = description,
                    checked = true,
                    enabled = true,
                    onCheckedChange = {},
                    badge = badge,
                    leadingIcon = painterResource(Res.drawable.ic_asterisk),
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
        }
    }
}
