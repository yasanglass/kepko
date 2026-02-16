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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.color.ProvideLocalContentColor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.OutlinedTextField as Material3OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults as Material3OutlinedTextFieldDefaults

@Composable
public fun OutlinedTextField(
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
    shape: Shape = KepkoTheme.shapes.extraLarge,
    interactionSource: MutableInteractionSource? = null,
) {
    val contentColor = if (enabled) KepkoTheme.colors.content else KepkoTheme.colors.contentDisabled

    ProvideLocalContentColor(color = contentColor) {
        Material3OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
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
            colors = outlinedTextFieldColors(),
        )
    }
}

@Composable
private fun outlinedTextFieldColors() = Material3OutlinedTextFieldDefaults.colors(
    // Container
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    errorContainerColor = Color.Transparent,
    // Text
    focusedTextColor = KepkoTheme.colors.content,
    unfocusedTextColor = KepkoTheme.colors.content,
    disabledTextColor = KepkoTheme.colors.contentDisabled,
    errorTextColor = KepkoTheme.colors.content,
    // Cursor
    cursorColor = KepkoTheme.colors.content,
    errorCursorColor = KepkoTheme.colors.danger,
    // Border
    focusedBorderColor = KepkoTheme.colors.content,
    unfocusedBorderColor = KepkoTheme.colors.contentSubtle,
    disabledBorderColor = KepkoTheme.colors.contentDisabled,
    errorBorderColor = KepkoTheme.colors.danger,
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
internal fun OutlinedTextFieldLightPreview() {
    KepkoTheme(style = ThemeStyle.LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun OutlinedTextFieldDarkPreview() {
    KepkoTheme(style = ThemeStyle.DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun OutlinedTextFieldBlackPreview() {
    KepkoTheme(style = ThemeStyle.BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun OutlinedTextFieldSolarizedLightPreview() {
    KepkoTheme(style = ThemeStyle.SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun OutlinedTextFieldSolarizedDarkPreview() {
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
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(text = "Placeholder") },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = "Filled text",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = "With label",
            onValueChange = {},
            label = "Label",
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = "Disabled",
            onValueChange = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = "Read-only",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = "Error state",
            onValueChange = {},
            isError = true,
            label = "Error",
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = "With leading icon",
            onValueChange = {},
            leadingIcon = { Icon(painter = painterResource(Res.drawable.ic_asterisk), contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = "With trailing icon",
            onValueChange = {},
            trailingIcon = { Icon(painter = painterResource(Res.drawable.ic_asterisk), contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = "Multiline text field\nwith multiple lines\nof content",
            onValueChange = {},
            minLines = 3,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
