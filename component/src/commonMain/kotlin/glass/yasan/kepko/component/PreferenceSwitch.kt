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
import glass.yasan.kepko.foundation.theme.ThemeStyle
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
    annotation: PreferenceAnnotation? = null,
) {
    PreferenceSwitch(
        title = title,
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        description = description,
        enabled = enabled,
        annotation = annotation,
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
    annotation: PreferenceAnnotation? = null,
) {
    PreferenceContainer(
        title = title,
        description = description,
        onClick = { onCheckedChange(!checked) },
        enabled = enabled,
        annotation = annotation,
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
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSwitchDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSwitchBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSwitchSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceSwitchSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val annotations = arrayOf(PreferenceAnnotation.new, null)
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
        annotations.forEach { annotation ->
            descriptions.forEach { description ->
                PreferenceSwitch(
                    title = "Preference Switch",
                    description = description,
                    checked = true,
                    enabled = true,
                    onCheckedChange = {},
                    annotation = annotation,
                    leadingIcon = painterResource(Res.drawable.ic_asterisk),
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
        }
    }
}
