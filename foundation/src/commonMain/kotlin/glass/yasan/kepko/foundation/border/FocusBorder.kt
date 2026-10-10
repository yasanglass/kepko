package glass.yasan.kepko.foundation.border

import androidx.compose.foundation.focusable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusEventModifierNode
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.InputMode
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalInputModeManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.dimension.DimensionTokens
import glass.yasan.kepko.foundation.theme.KepkoTheme

/**
 * Outlines the element while it has keyboard or remote focus, like CSS `:focus-visible`.
 */
@ExperimentalKepkoApi
@Composable
public fun Modifier.focusBorder(
    shape: Shape = RectangleShape,
    color: Color = KepkoTheme.colors.content,
    contrastColor: Color = KepkoTheme.colors.inverseContent,
    width: Dp = DimensionTokens.focusBorderThickness,
    outset: Dp = 0.dp,
): Modifier = this then FocusBorderElement(
    shape = shape,
    color = color,
    contrastColor = contrastColor,
    width = width,
    outset = outset,
)

/**
 * Makes a non-interactive element focusable and outlines it with [focusBorder].
 */
@ExperimentalKepkoApi
@Composable
public fun Modifier.focusableWithBorder(
    shape: Shape = RectangleShape,
    color: Color = KepkoTheme.colors.content,
    contrastColor: Color = KepkoTheme.colors.inverseContent,
    width: Dp = DimensionTokens.focusBorderThickness,
    outset: Dp = 0.dp,
    enabled: Boolean = true,
): Modifier = if (enabled) {
    focusBorder(shape = shape, color = color, contrastColor = contrastColor, width = width, outset = outset).focusable()
} else {
    this
}

private data class FocusBorderElement(
    val shape: Shape,
    val color: Color,
    val contrastColor: Color,
    val width: Dp,
    val outset: Dp,
) : ModifierNodeElement<FocusBorderNode>() {

    override fun create(): FocusBorderNode =
        FocusBorderNode(shape = shape, color = color, contrastColor = contrastColor, width = width, outset = outset)

    override fun update(node: FocusBorderNode) {
        node.shape = shape
        node.color = color
        node.contrastColor = contrastColor
        node.width = width
        node.outset = outset
        node.invalidateDraw()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "focusBorder"
        properties["shape"] = shape
        properties["color"] = color
        properties["contrastColor"] = contrastColor
        properties["width"] = width
        properties["outset"] = outset
    }
}

private class FocusBorderNode(
    var shape: Shape,
    var color: Color,
    var contrastColor: Color,
    var width: Dp,
    var outset: Dp,
) : Modifier.Node(), FocusEventModifierNode, CompositionLocalConsumerModifierNode, DrawModifierNode {

    private var isFocused = false

    override fun onFocusEvent(focusState: FocusState) {
        if (isFocused == focusState.isFocused) return

        isFocused = focusState.isFocused
        invalidateDraw()
    }

    override fun ContentDrawScope.draw() {
        drawContent()
        if (!isFocused || currentValueOf(LocalInputModeManager).inputMode != InputMode.Keyboard) return

        val outerWidth = width.toPx()
        val outsetPx = outset.toPx()
        drawRing(inset = -outsetPx, strokeWidth = outerWidth, color = color)
        drawRing(inset = outerWidth - outsetPx, strokeWidth = outerWidth / 2, color = contrastColor)
    }

    private fun DrawScope.drawRing(inset: Float, strokeWidth: Float, color: Color) {
        val edge = inset + strokeWidth / 2
        val outline = shape.createOutline(
            size = Size(width = size.width - 2 * edge, height = size.height - 2 * edge),
            layoutDirection = layoutDirection,
            density = this,
        )
        translate(left = edge, top = edge) {
            drawOutline(outline = outline, color = color, style = Stroke(width = strokeWidth))
        }
    }
}
