package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

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
    val titleContentColor = if (enabled) KepkoTheme.colors.content else KepkoTheme.colors.contentDisabled
    val descriptionContentColor = if (enabled) KepkoTheme.colors.contentSubtle else KepkoTheme.colors.contentDisabled
    val shape = RoundedCornerShape(32.dp)

    Column(
        modifier = modifier
            .border(color = KepkoTheme.colors.background, shape)
            .clip(shape)
            .clickable(
                enabled = enabled,
                onClick = { onCheckedChange(!checked) },
            )
            .background(KepkoTheme.colors.foreground)
            .padding(
                vertical = 8.dp,
                horizontal = 24.dp,
            ),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            leadingContent()
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = title,
                    color = titleContentColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                )
                description?.let {
                    Text(
                        text = it,
                        color = descriptionContentColor,
                        lineHeight = 16.sp,
                        fontSize = 12.sp,
                    )
                }
                annotation?.let {
                    Pill(
                        text = it.text(),
                        containerColor = it.containerColor(),
                        contentColor = it.contentColor(),
                        modifier = Modifier.padding(vertical = 4.dp),
                    )
                }
            }
            Switch(
                enabled = enabled,
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
    }
}

@OptIn(ExperimentalKepkoApi::class)
@Preview
@Composable
private fun PreferenceSwitchPreview() {
    val checked = arrayOf(true, false)
    val enabled = arrayOf(true, false)
    val descriptions = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        null
    )

    Column {
        KepkoTheme {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.background(KepkoTheme.colors.midground),
            ) {
                descriptions.forEach { description ->
                    checked.forEach { isChecked ->
                        enabled.forEach { isEnabled ->
                            PreferenceSwitch(
                                title = if (isChecked) "Checked" else "Unchecked",
                                description = description,
                                checked = isChecked,
                                enabled = isEnabled,
                                onCheckedChange = {},
                                modifier = Modifier.padding(horizontal = 16.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalKepkoApi::class)
@Preview
@Composable
private fun PreferenceSwitchWithIconPreview() {
    val checked = arrayOf(true, false)
    val descriptions = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        null
    )

    Column {
        KepkoTheme {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.background(KepkoTheme.colors.midground),
            ) {
                descriptions.forEach { description ->
                    checked.forEach { isChecked ->
                        PreferenceSwitch(
                            title = if (isChecked) "Checked" else "Unchecked",
                            description = description,
                            checked = isChecked,
                            onCheckedChange = {},
                            leadingIcon = painterResource(Res.drawable.ic_asterisk),
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalKepkoApi::class)
@Preview
@Composable
private fun PreferenceSwitchWithLabelPreview() {
    val checked = arrayOf(true, false)
    val descriptions = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        null
    )

    KepkoTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.background(KepkoTheme.colors.midground),
        ) {
            descriptions.forEach { description ->
                checked.forEach { isChecked ->
                    PreferenceSwitch(
                        title = if (isChecked) "Checked" else "Unchecked",
                        description = description,
                        checked = isChecked,
                        onCheckedChange = {},
                        annotation = PreferenceAnnotation.experimental,
                        leadingIcon = painterResource(Res.drawable.ic_asterisk),
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }
            }
        }
    }
}
