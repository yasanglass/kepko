package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.LocalTextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.border.border
import glass.yasan.kepko.foundation.color.ProvideLocalContentColor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.TextField as Material3TextField
import androidx.compose.material3.TextFieldDefaults as Material3TextFieldDefaults

@ExperimentalKepkoApi
@Composable
public fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    label: String? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textStyle: TextStyle = LocalTextStyle.current,
    shape: Shape = KepkoTheme.shapes.extraLarge,
    interactionSource: MutableInteractionSource? = null,
) {
    val contentColor = if (enabled) KepkoTheme.colors.content else KepkoTheme.colors.contentDisabled

    ProvideLocalContentColor(color = contentColor) {
        Material3TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.border(shape = shape),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            isError = isError,
            label = label?.let {
                {
                    Text(
                        text = it,
                        fontWeight = Medium,
                    )
                }
            },
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = shape,
            interactionSource = interactionSource,
            colors = textFieldColors(),
        )
    }
}

@Composable
private fun textFieldColors() = Material3TextFieldDefaults.colors(
    // Container
    focusedContainerColor = KepkoTheme.colors.foreground,
    unfocusedContainerColor = KepkoTheme.colors.foreground,
    disabledContainerColor = KepkoTheme.colors.foreground.copy(alpha = 0.50f),
    errorContainerColor = KepkoTheme.colors.foreground,
    // Text
    focusedTextColor = KepkoTheme.colors.content,
    unfocusedTextColor = KepkoTheme.colors.content,
    disabledTextColor = KepkoTheme.colors.contentDisabled,
    errorTextColor = KepkoTheme.colors.content,
    // Cursor
    cursorColor = KepkoTheme.colors.content,
    errorCursorColor = KepkoTheme.colors.danger,
    // Indicator
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    errorIndicatorColor = Color.Transparent,
    // Label
    focusedLabelColor = KepkoTheme.colors.content,
    unfocusedLabelColor = KepkoTheme.colors.contentSubtle,
    disabledLabelColor = KepkoTheme.colors.contentDisabled,
    errorLabelColor = KepkoTheme.colors.danger,
    // Placeholder
    focusedPlaceholderColor = KepkoTheme.colors.contentSubtle,
    unfocusedPlaceholderColor = KepkoTheme.colors.contentSubtle,
    disabledPlaceholderColor = KepkoTheme.colors.contentDisabled,
    errorPlaceholderColor = KepkoTheme.colors.contentSubtle,
    // Leading icon
    focusedLeadingIconColor = KepkoTheme.colors.content,
    unfocusedLeadingIconColor = KepkoTheme.colors.contentSubtle,
    disabledLeadingIconColor = KepkoTheme.colors.contentDisabled,
    errorLeadingIconColor = KepkoTheme.colors.danger,
    // Trailing icon
    focusedTrailingIconColor = KepkoTheme.colors.content,
    unfocusedTrailingIconColor = KepkoTheme.colors.contentSubtle,
    disabledTrailingIconColor = KepkoTheme.colors.contentDisabled,
    errorTrailingIconColor = KepkoTheme.colors.danger,
    // Prefix
    focusedPrefixColor = KepkoTheme.colors.content,
    unfocusedPrefixColor = KepkoTheme.colors.contentSubtle,
    disabledPrefixColor = KepkoTheme.colors.contentDisabled,
    errorPrefixColor = KepkoTheme.colors.content,
    // Suffix
    focusedSuffixColor = KepkoTheme.colors.content,
    unfocusedSuffixColor = KepkoTheme.colors.contentSubtle,
    disabledSuffixColor = KepkoTheme.colors.contentDisabled,
    errorSuffixColor = KepkoTheme.colors.content,
)

@PreviewWithTest
@Composable
internal fun TextFieldLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextFieldDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextFieldBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextFieldSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun TextFieldSolarizedDarkPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_DARK) { PreviewContent() }
}

@Suppress("LongMethod")
@Composable
private fun PreviewContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.midground)
            .padding(16.dp),
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Placeholder") },
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = "Filled text",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = "With label",
            onValueChange = {},
            label = "Label",
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = "Disabled",
            onValueChange = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = "Read-only",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = "Error state",
            onValueChange = {},
            isError = true,
            label = "Error",
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = "With leading icon",
            onValueChange = {},
            leadingIcon = { Icon(painter = painterResource(Res.drawable.ic_asterisk), contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = "With trailing icon",
            onValueChange = {},
            trailingIcon = { Icon(painter = painterResource(Res.drawable.ic_asterisk), contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = "Multiline text field\nwith multiple lines\nof content",
            onValueChange = {},
            minLines = 3,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
