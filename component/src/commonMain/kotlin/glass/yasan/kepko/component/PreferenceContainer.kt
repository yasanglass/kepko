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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.border.border
import glass.yasan.kepko.foundation.color.ProvideLocalContentColor
import glass.yasan.kepko.foundation.theme.KepkoTheme

@Composable
public fun PreferenceContainer(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    description: String? = null,
    enabled: Boolean = true,
    badge: Badge? = null,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null,
    shape: Shape = KepkoTheme.shapes.extraLarge,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp),
    colors: PreferenceContainerColors = PreferenceContainerDefaults.colors(),
    content: @Composable (PaddingValues) -> Unit,
) {
    val animated = colors.animated(enabled)

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
            .background(animated.containerColor)
            .padding(vertical = 16.dp),
    ) {
        Column {
            Text(
                text = title.uppercase(),
                color = animated.contentColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(contentPadding),
            )
            description?.let {
                Text(
                    text = it,
                    color = animated.descriptionColor,
                    lineHeight = 16.sp,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(contentPadding),
                )
            }
        }
        ProvideLocalContentColor(animated.contentColor) {
            content(contentPadding)
        }
        badge?.let {
            TextPill(
                badge = it,
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
    additionalContent: (@Composable () -> Unit)? = null,
    leadingContent: @Composable () -> Unit = {},
    trailingContent: @Composable () -> Unit = {},
    badge: Badge? = null,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = KepkoTheme.shapes.extraLarge,
    colors: PreferenceContainerColors = PreferenceContainerDefaults.colors(),
) {
    val animated = colors.animated(enabled)

    Column(
        modifier = modifier
            .border(color = KepkoTheme.colors.outline, shape)
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                enabled = enabled,
                onClick = onClick,
            )
            .background(animated.containerColor)
            .padding(
                vertical = 8.dp,
                horizontal = 20.dp,
            ),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            ProvideLocalContentColor(animated.contentColor) {
                leadingContent()
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f).padding(vertical = 4.dp),
            ) {
                Column {
                    Text(
                        text = title.uppercase(),
                        color = animated.contentColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                    description?.let {
                        Text(
                            text = it,
                            color = animated.descriptionColor,
                            lineHeight = 16.sp,
                            fontSize = 12.sp,
                        )
                    }
                }
                additionalContent?.invoke()
                badge?.let {
                    TextPill(
                        badge = it,
                        modifier = Modifier.padding(vertical = 4.dp),
                    )
                }
            }
            trailingContent()
        }
    }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerHorizontalLightPreview() {
    KepkoTheme(palette = LIGHT) { HorizontalPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerHorizontalDarkPreview() {
    KepkoTheme(palette = DARK) { HorizontalPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerHorizontalBlackPreview() {
    KepkoTheme(palette = BLACK) { HorizontalPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerHorizontalSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { HorizontalPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerHorizontalSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { HorizontalPreviewContent() }
}

@Composable
private fun HorizontalPreviewContent() {
    val badges = arrayOf(Badge.alpha, null)
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
                PreferenceContainer(
                    title = "PreferenceContainer",
                    description = description,
                    enabled = true,
                    badge = badge,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    content = {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(32.dp)
                                .background(KepkoTheme.colors.information),
                        )
                    },
                )
            }
        }
    }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerVerticalLightPreview() {
    KepkoTheme(palette = LIGHT) { VerticalPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerVerticalDarkPreview() {
    KepkoTheme(palette = DARK) { VerticalPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerVerticalBlackPreview() {
    KepkoTheme(palette = BLACK) { VerticalPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerVerticalSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { VerticalPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun PreferenceContainerVerticalSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { VerticalPreviewContent() }
}

@Composable
private fun VerticalPreviewContent() {
    val badges = arrayOf(Badge.legacy, null)
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
                PreferenceContainer(
                    title = "PreferenceContainer",
                    description = description,
                    enabled = true,
                    badge = badge,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    additionalContent = {
                        Spacer(
                            modifier = Modifier
                                .clip(KepkoTheme.shapes.small)
                                .height(32.dp)
                                .fillMaxWidth()
                                .background(KepkoTheme.colors.success),
                        )
                    },
                    leadingContent = {
                        Spacer(
                            modifier = Modifier
                                .clip(KepkoTheme.shapes.extraLarge)
                                .size(32.dp)
                                .background(KepkoTheme.colors.information),
                        )
                    },
                    trailingContent = {
                        Spacer(
                            modifier = Modifier
                                .clip(KepkoTheme.shapes.extraLarge)
                                .size(32.dp)
                                .background(KepkoTheme.colors.caution),
                        )
                    },
                )
            }
        }
    }
}
