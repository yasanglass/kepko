package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import org.jetbrains.compose.resources.painterResource

@Composable
public fun PreferenceRadioButton(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    leadingIcon: Painter,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
    annotation: PreferenceAnnotation? = null,
    reverse: Boolean = false,
) {
    PreferenceRadioButton(
        title = title,
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        description = description,
        enabled = enabled,
        annotation = annotation,
        reverse = reverse,
        leadingContent = {
            Icon(
                painter = leadingIcon,
                contentDescription = null,
            )
        },
    )
}

@Composable
public fun PreferenceRadioButton(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
    leadingContent: @Composable () -> Unit = {},
    annotation: PreferenceAnnotation? = null,
    reverse: Boolean = false,
) {
    val radio: @Composable () -> Unit = {
        RadioButton(
            enabled = enabled,
            selected = selected,
            onClick = onClick,
            minimumInteractiveComponentSize = 0.dp,
            modifier = Modifier.padding(vertical = 12.dp),
        )
    }
    val leading = if (reverse) radio else leadingContent
    val trailing = if (reverse) leadingContent else radio

    PreferenceContainer(
        title = title,
        description = description,
        onClick = onClick,
        enabled = enabled,
        annotation = annotation,
        modifier = modifier,
        leadingContent = leading,
        trailingContent = trailing,
    )
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioButtonLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioButtonDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioButtonBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioButtonSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceRadioButtonSolarizedDarkPreview() {
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
        annotations.forEachIndexed { annotationIndex, annotation ->
            descriptions.forEachIndexed { descriptionIndex, description ->
                PreferenceRadioButton(
                    title = "PreferenceRadioButton",
                    selected = annotationIndex != 1 || descriptionIndex != 1,
                    onClick = {},
                    leadingIcon = painterResource(Res.drawable.ic_asterisk),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    description = description,
                    annotation = annotation,
                )
            }
        }
        PreferenceRadioButton(
            title = "Preference RadioButton",
            selected = false,
            onClick = {},
            enabled = false,
            leadingIcon = painterResource(Res.drawable.ic_asterisk),
            reverse = true,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}
