package glass.yasan.kepko.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.gestures.BringIntoViewSpec
import androidx.compose.foundation.gestures.LocalBringIntoViewSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.abs

/**
 * Moves focus from the top bar down into the content it overlaps.
 */
internal fun Modifier.downIntoContent(
    contentFocusRequester: FocusRequester,
    focusManager: FocusManager,
): Modifier = this
    .focusProperties {
        onExit = {
            if (requestedFocusDirection == FocusDirection.Down) contentFocusRequester.requestFocus()
        }
    }
    .focusGroup()
    .onKeyEvent { event ->
        event.type == KeyEventType.KeyDown &&
            event.key == Key.DirectionDown &&
            (focusManager.moveFocus(FocusDirection.Down) || contentFocusRequester.requestFocus())
    }

/**
 * Hosts the scaffold content and keeps focused items out from under the bars.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ScaffoldContent(
    contentPadding: PaddingValues,
    contentFocusRequester: FocusRequester,
    content: @Composable (contentPadding: PaddingValues) -> Unit,
) {
    val density = LocalDensity.current
    val maxWidth = LocalScaffoldContentMaxWidth.current
    val parentBringIntoViewSpec = LocalBringIntoViewSpec.current
    val contentHeight = remember { mutableIntStateOf(0) }
    val topCovered = with(density) { contentPadding.calculateTopPadding().toPx() }
    val bottomCovered = with(density) { contentPadding.calculateBottomPadding().toPx() }
    val bringIntoViewSpec = remember(parentBringIntoViewSpec, topCovered, bottomCovered) {
        BarsCoveredBringIntoViewSpec(
            parent = parentBringIntoViewSpec,
            topCovered = topCovered,
            bottomCovered = bottomCovered,
            contentHeight = { contentHeight.intValue },
        )
    }

    CompositionLocalProvider(LocalBringIntoViewSpec provides bringIntoViewSpec) {
        Box(
            propagateMinConstraints = true,
            modifier = Modifier
                .onSizeChanged { size -> contentHeight.intValue = size.height }
                .centeredMaxWidth(maxWidth)
                .focusRequester(contentFocusRequester)
                .focusGroup(),
        ) {
            content(contentPadding)
        }
    }
}

/**
 * Scrolls focused items into the area left visible between the bars.
 */
private class BarsCoveredBringIntoViewSpec(
    private val parent: BringIntoViewSpec,
    private val topCovered: Float,
    private val bottomCovered: Float,
    private val contentHeight: () -> Int,
) : BringIntoViewSpec {
    override fun calculateScrollDistance(offset: Float, size: Float, containerSize: Float): Float =
        if (abs(containerSize - contentHeight()) < 1f) {
            parent.calculateScrollDistance(
                offset = offset - topCovered,
                size = size,
                containerSize = containerSize - topCovered - bottomCovered,
            )
        } else {
            parent.calculateScrollDistance(offset = offset, size = size, containerSize = containerSize)
        }
}
