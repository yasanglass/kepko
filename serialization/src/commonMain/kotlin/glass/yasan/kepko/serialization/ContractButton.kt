package glass.yasan.kepko.serialization

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.component.Button
import glass.yasan.kepko.component.Badge
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.color.NamedColor
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.serialization.contract.ButtonContract
import glass.yasan.kepko.serialization.contract.PreferenceAnnotationContract

/**
 * Constructs a [Button] from a [ButtonContract].
 *
 * @param onClick invokes the provided function with the action string provided in the [contract].
 */
@ExperimentalKepkoApi
@Composable
public fun ContractButton(
    contract: ButtonContract,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: ((String) -> Unit)? = null,
    onDoubleClick: ((String) -> Unit)? = null,
) {
    val containerColor = contract.containerColor.resolveColor()
    val contentColor = contract.contentColor.resolveContentColor(containerColor)

    Button(
        text = contract.text,
        onClick = { onClick(contract.onClick) },
        modifier = modifier,
        onClickLabel = contract.onClickLabel,
        onLongClick = contract.onLongClick?.let { action ->
            onLongClick?.let { handler ->
                { handler(action) }
            }
        },
        onLongClickLabel = contract.onLongClickLabel?.takeIf { onLongClick != null },
        onDoubleClick = contract.onDoubleClick?.let { action ->
            onDoubleClick?.let { handler ->
                { handler(action) }
            }
        },
        containerColor = containerColor,
        contentColor = contentColor,
        enabled = contract.enabled,
        fillWidth = contract.fillWidth,
        badge = contract.badge?.toBadge(),
        leadingIcon = contract.leadingIcon?.painter?.invoke(),
        trailingIcon = contract.trailingIcon?.painter?.invoke(),
    )
}

@Composable
private fun NamedColor?.resolveColor(): Color =
    this?.resolve(KepkoTheme.colors) ?: KepkoTheme.colors.foreground

@Composable
private fun NamedColor?.resolveContentColor(containerColor: Color): Color =
    this?.resolve(KepkoTheme.colors) ?: contentColorFor(containerColor)

@Composable
private fun PreferenceAnnotationContract.toBadge(): Badge =
    Badge(
        text = { text },
        containerColor = {
            containerColor.resolveColor()
        },
        contentColor = {
            contentColor.resolveContentColor(containerColor.resolveColor())
        },
        leadingIcon = leadingIcon?.let { icon ->
            {
                icon.painter()
            }
        },
        trailingIcon = trailingIcon?.let { icon ->
            {
                icon.painter()
            }
        },
    )
