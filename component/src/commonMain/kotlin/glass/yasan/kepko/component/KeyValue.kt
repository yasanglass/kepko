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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.foundation.color.getSemanticColors
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons

@Composable
public fun KeyValue(
    key: String,
    value: String,
    modifier: Modifier = Modifier,
    containerColor: Color = KepkoTheme.colors.foreground,
    leadingValueIcon: Painter? = null,
    trailingValueIcon: Painter? = null,
    onValueClick: (() -> Unit)? = null,
    onValueClickInteractionSource: MutableInteractionSource? = null,
    onValueClickIndication: Indication? = null,
    onClick: (() -> Unit)? = null,
    onClickLabel: String? = null,
    onClickInteractionSource: MutableInteractionSource? = null,
    onClickIndication: Indication? = null,
    trailingIcon: Painter? = null,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(
                shape = KepkoTheme.shapes.extraLarge,
            )
            .then(
                if (onClick != null) {
                    if (onClickInteractionSource != null || onClickIndication != null) {
                        Modifier.clickable(
                            interactionSource = onClickInteractionSource,
                            indication = onClickIndication,
                            onClickLabel = onClickLabel,
                            onClick = onClick,
                        )
                    } else {
                        Modifier.clickable(
                            onClickLabel = onClickLabel,
                            onClick = onClick,
                        )
                    }
                } else {
                    Modifier
                },
            )
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(horizontal = 16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = key,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
            TextPill(
                text = value,
                containerColor = containerColor,
                leadingIcon = leadingValueIcon,
                trailingIcon = trailingValueIcon,
                onClick = onValueClick,
                onClickInteractionSource = onValueClickInteractionSource,
                onClickIndication = onValueClickIndication,
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
    val containerColors = KepkoTheme.colors.getSemanticColors() +
            KepkoTheme.colors.foreground +
            KepkoTheme.colors.content

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(4.dp),
    ) {
        containerColors.forEach { containerColor ->
            KeyValue(
                key = "Key",
                value = "Value",
                containerColor = containerColor,
            )
        }
        KeyValue(
            key = "With Leading Icon",
            value = "Star",
            containerColor = KepkoTheme.colors.caution,
            leadingValueIcon = Icons.star,
        )
        KeyValue(
            key = "With Trailing Icon",
            value = "Check",
            containerColor = KepkoTheme.colors.success,
            trailingValueIcon = Icons.check,
        )
        KeyValue(
            key = "With Both Icons",
            value = "Info",
            containerColor = KepkoTheme.colors.information,
            leadingValueIcon = Icons.info,
            trailingValueIcon = Icons.chevronForward,
        )
        KeyValue(
            key = "With Row Trailing Icon",
            value = "Editable",
            containerColor = KepkoTheme.colors.foreground,
            trailingIcon = Icons.edit,
            onClick = {},
        )
    }
}
