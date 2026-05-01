package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons

@Composable
public fun KeyValue(
    key: String,
    value: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    onClickLabel: String? = null,
    onClickInteractionSource: MutableInteractionSource? = null,
    onClickIndication: Indication? = null,
    leadingIcon: Painter? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingIcon: Painter? = null,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(shape = KepkoTheme.shapes.extraLarge)
            .clickableIfPresent(
                onClick = onClick,
                onClickLabel = onClickLabel,
                interactionSource = onClickInteractionSource,
                indication = onClickIndication,
            )
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(horizontal = 20.dp),
    ) {
        leadingContent?.invoke()
        if (leadingIcon != null) {
            Icon(
                painter = leadingIcon,
                contentDescription = null,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = key,
                color = KepkoTheme.colors.contentSubtle,
                fontSize = 14.sp,
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
            )
        }
        if (trailingIcon != null) {
            Icon(
                painter = trailingIcon,
                contentDescription = null,
            )
        }
        trailingContent?.invoke()
    }
}

private fun Modifier.clickableIfPresent(
    onClick: (() -> Unit)?,
    onClickLabel: String?,
    interactionSource: MutableInteractionSource?,
    indication: Indication?,
): Modifier = when {
    onClick == null -> this
    interactionSource != null || indication != null -> clickable(
        interactionSource = interactionSource,
        indication = indication,
        onClickLabel = onClickLabel,
        onClick = onClick,
    )
    else -> clickable(
        onClickLabel = onClickLabel,
        onClick = onClick,
    )
}

@PreviewWithTest
@Composable
internal fun KeyValueLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun KeyValueDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun KeyValueBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun KeyValueSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun KeyValueSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(4.dp),
    ) {
        KeyValue(
            key = "Key",
            value = "Value",
        )
        KeyValue(
            key = "With Row Trailing Icon",
            value = "Editable",
            trailingIcon = Icons.edit,
            onClick = {},
        )
        KeyValue(
            key = "With Row Trailing Icon",
            value = "Editable",
            leadingIcon = Icons.edit,
            onClick = {},
        )
    }
}
