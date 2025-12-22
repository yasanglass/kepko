package glass.yasan.kepko.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.border.border
import glass.yasan.kepko.foundation.color.ProvideLocalContentColor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun PreferenceContainer(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    description: String? = null,
    enabled: Boolean = true,
    annotation: PreferenceAnnotation? = null,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    val titleContentColor = if (enabled) KepkoTheme.colors.content else KepkoTheme.colors.contentDisabled
    val descriptionContentColor = if (enabled) KepkoTheme.colors.contentSubtle else KepkoTheme.colors.contentDisabled
    val shape = RoundedCornerShape(32.dp)
    val contentPadding = PaddingValues(horizontal = 24.dp)

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(color = KepkoTheme.colors.outline, shape)
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = indication,
                enabled = enabled,
                onClick = onClick,
            )
            .background(KepkoTheme.colors.foreground)
            .padding(vertical = 16.dp),
    ) {
        Text(
            text = title,
            color = titleContentColor,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            modifier = Modifier.padding(contentPadding),
        )
        description?.let {
            Text(
                text = it,
                color = descriptionContentColor,
                lineHeight = 16.sp,
                fontSize = 12.sp,
                modifier = Modifier.padding(contentPadding),
            )
        }
        ProvideLocalContentColor(titleContentColor) {
            content(contentPadding)
        }
        annotation?.let {
            TextPill(
                text = it.text(),
                containerColor = it.containerColor(),
                contentColor = it.contentColor(),
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(vertical = 4.dp),
            )
        }
    }
}

@Composable
public fun PreferenceContainer(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    description: String? = null,
    enabled: Boolean = true,
    leadingContent: @Composable () -> Unit = {},
    trailingContent: @Composable () -> Unit = {},
    annotation: PreferenceAnnotation? = null,
    interactionSource: MutableInteractionSource? = null,
) {
    val titleContentColor = if (enabled) KepkoTheme.colors.content else KepkoTheme.colors.contentDisabled
    val descriptionContentColor = if (enabled) KepkoTheme.colors.contentSubtle else KepkoTheme.colors.contentDisabled
    val shape = RoundedCornerShape(32.dp)

    Column(
        modifier = modifier
            .border(color = KepkoTheme.colors.outline, shape)
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                enabled = enabled,
                onClick = onClick,
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
            ProvideLocalContentColor(titleContentColor) {
                leadingContent()
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f).padding(vertical = 4.dp),
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
                    TextPill(
                        text = it.text(),
                        containerColor = it.containerColor(),
                        contentColor = it.contentColor(),
                        modifier = Modifier.padding(vertical = 4.dp),
                    )
                }
            }
            trailingContent()
        }
    }
}

@Preview
@Composable
private fun PreferenceContainerHorizontalPreview() {
    val annotations = arrayOf(PreferenceAnnotation.experimental, null)
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
                    PreferenceContainer(
                        title = "Preference Container",
                        description = description,
                        enabled = true,
                        annotation = annotation,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        content = {
                            Spacer(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(KepkoTheme.colors.information),
                            )
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreferenceContainerVerticalPreview() {
    val annotations = arrayOf(PreferenceAnnotation.experimental, null)
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
                    PreferenceContainer(
                        title = "Preference Container",
                        description = description,
                        enabled = true,
                        annotation = annotation,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        leadingContent = {
                            Spacer(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(KepkoTheme.colors.information),
                            )
                        },
                        trailingContent = {
                            Spacer(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(KepkoTheme.colors.caution),
                            )
                        },
                    )
                }
            }
        }
    }
}
