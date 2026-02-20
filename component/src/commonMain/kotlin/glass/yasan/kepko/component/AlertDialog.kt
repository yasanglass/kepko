package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.DialogProperties
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.border.border
import androidx.compose.material3.AlertDialog as Material3AlertDialog

@ExperimentalKepkoApi
@Composable
public fun AlertDialog(
    onDismissRequest: () -> Unit,
    confirmButtonText: String,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
    dismissButtonText: String? = null,
    onDismissClick: (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: String? = null,
    text: String? = null,
    shape: Shape = AlertDialogDefaults.shape,
    colors: AlertDialogColors = AlertDialogDefaults.colors(),
    border: Brush = AlertDialogDefaults.border,
    borderThickness: Dp = AlertDialogDefaults.BorderThickness,
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
    properties: DialogProperties = DialogProperties(),
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            ButtonText(
                text = confirmButtonText,
                onClick = onConfirmClick,
                containerColor = colors.confirmButtonContainerColor,
                fillWidth = false,
            )
        },
        modifier = modifier,
        dismissButton = dismissButtonText?.let { buttonText ->
            {
                ButtonText(
                    text = buttonText,
                    onClick = onDismissClick ?: onDismissRequest,
                    fillWidth = false,
                )
            }
        },
        icon = icon,
        title = title?.let { titleText ->
            {
                Text(text = titleText)
            }
        },
        text = text?.let { bodyText ->
            {
                Text(text = bodyText)
            }
        },
        shape = shape,
        colors = colors,
        border = border,
        borderThickness = borderThickness,
        tonalElevation = tonalElevation,
        properties = properties,
    )
}

@ExperimentalKepkoApi
@Composable
public fun AlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    shape: Shape = AlertDialogDefaults.shape,
    colors: AlertDialogColors = AlertDialogDefaults.colors(),
    border: Brush = AlertDialogDefaults.border,
    borderThickness: Dp = AlertDialogDefaults.BorderThickness,
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
    properties: DialogProperties = DialogProperties(),
) {
    val outlinedModifier = modifier.border(
        brush = border,
        shape = shape,
        width = borderThickness,
    )

    Material3AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        modifier = outlinedModifier,
        dismissButton = dismissButton,
        icon = icon,
        title = title,
        text = text,
        shape = shape,
        containerColor = colors.containerColor,
        iconContentColor = colors.iconContentColor,
        titleContentColor = colors.titleContentColor,
        textContentColor = colors.textContentColor,
        tonalElevation = tonalElevation,
        properties = properties,
    )
}
