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
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalKepkoApi::class)
@Composable
public fun PreferenceCheckbox(
    title: String,
    checked: Boolean,
    leadingIcon: Painter,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    enabled: Boolean = true,
    annotation: PreferenceAnnotation? = null,
) {
    PreferenceCheckbox(
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
public fun PreferenceCheckbox(
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
            Checkbox(
                enabled = enabled,
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
    )
}

@OptIn(ExperimentalKepkoApi::class)
@Preview
@Composable
private fun PreferenceCheckboxPreview() {
    val annotations = arrayOf(PreferenceAnnotation.new, null)
    val descriptions = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        null
    )

    KepkoTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .background(KepkoTheme.colors.midground)
                .padding(vertical = 16.dp),
        ) {
            annotations.forEach { annotation ->
                descriptions.forEach { description ->
                    PreferenceCheckbox(
                        title = "Preference Checkbox",
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
}
