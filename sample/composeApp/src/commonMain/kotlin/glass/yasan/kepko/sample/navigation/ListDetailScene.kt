package glass.yasan.kepko.sample.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.Scene
import glass.yasan.kepko.component.ProvideTitleBarBackButton
import glass.yasan.kepko.component.VerticalDivider
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme

internal data class ListDetailScene<T : Any>(
    private val listEntry: NavEntry<T>,
    private val detailEntry: NavEntry<T>?,
    private val isTwoPane: Boolean,
    private val showsDetailBackButton: Boolean,
    private val paneState: ListDetailPaneState,
    override val previousEntries: List<NavEntry<T>>,
) : Scene<T> {
    override val key: Any = listEntry.contentKey

    override val entries: List<NavEntry<T>> = listOfNotNull(listEntry, detailEntry)

    override val content: @Composable () -> Unit = {
        ListDetailPanes(
            listEntry = listEntry,
            detail = DetailPane(entry = detailEntry, showsBackButton = showsDetailBackButton),
            isTwoPane = isTwoPane,
            paneState = paneState,
        )
    }
}

private data class DetailPane<T : Any>(
    val entry: NavEntry<T>?,
    val showsBackButton: Boolean,
)

@Composable
private fun <T : Any> ListDetailPanes(
    listEntry: NavEntry<T>,
    detail: DetailPane<T>,
    isTwoPane: Boolean,
    paneState: ListDetailPaneState,
) {
    val windowWidth = LocalWindowInfo.current.containerDpSize.width
    val listWidthRange = ListDetailDefaults.listWidthRange(windowWidth = windowWidth)
    val panes = rememberPanePositions(
        layout = PaneLayout(isTwoPane = isTwoPane, hasDetail = detail.entry != null),
        paneWidth = paneState.listWidth.coerceIn(listWidthRange),
        windowWidth = windowWidth,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KepkoTheme.colors.midground)
            .clipToBounds(),
    ) {
        Box(modifier = Modifier.offset(x = panes.listX).width(panes.listWidth).fillMaxHeight()) {
            listEntry.Content()
        }
        if (panes.dividerX > 0.dp && panes.dividerX < windowWidth) {
            VerticalDivider(modifier = Modifier.offset(x = panes.dividerX))
        }
        // The right edge stays on the window's, so the pane widens and narrows as its left edge slides.
        Box(
            modifier = Modifier
                .offset(x = panes.detailX)
                .width((windowWidth - panes.detailX).coerceAtLeast(0.dp))
                .fillMaxHeight(),
        ) {
            DetailPaneContent(detail = detail)
        }
        if (isTwoPane) {
            DividerHandle(
                x = panes.dividerX,
                onDrag = { delta -> paneState.resizeList(by = delta, range = listWidthRange) },
            )
        }
    }
}

@OptIn(ExperimentalKepkoApi::class)
@Composable
private fun <T : Any> DetailPaneContent(detail: DetailPane<T>) {
    // Each pane keeps its own back button state, so an outgoing pane does not change while it fades out.
    AnimatedContent(
        targetState = detail,
        contentKey = { pane -> pane.entry?.contentKey },
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        modifier = Modifier.fillMaxSize(),
    ) { pane ->
        val entry = pane.entry ?: return@AnimatedContent
        ProvideTitleBarBackButton(visible = pane.showsBackButton) {
            entry.Content()
        }
    }
}

@Composable
private fun DividerHandle(
    x: Dp,
    onDrag: (Dp) -> Unit,
) {
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .offset(x = x - ListDetailDefaults.DividerHandleWidth / 2)
            .width(ListDetailDefaults.DividerHandleWidth)
            .fillMaxHeight()
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta -> onDrag(with(density) { delta.toDp() }) },
            ),
    )
}

private data class PaneLayout(
    val isTwoPane: Boolean,
    val hasDetail: Boolean,
) {
    val isListShown: Boolean = isTwoPane || !hasDetail
}

private data class PanePositions(
    val listX: Dp,
    val listWidth: Dp,
    val detailX: Dp,
) {
    val dividerX: Dp = listX + listWidth
}

/**
 * The list keeps the width it was last shown at while hidden, so it slides out and back in without reflowing.
 */
@Composable
private fun rememberPanePositions(
    layout: PaneLayout,
    paneWidth: Dp,
    windowWidth: Dp,
): PanePositions {
    val shownListWidth = if (layout.isTwoPane) paneWidth else windowWidth
    var listWidth by remember { mutableStateOf(shownListWidth) }
    if (layout.isListShown) listWidth = shownListWidth
    val detailX = when {
        layout.isTwoPane -> paneWidth + ListDetailDefaults.DividerWidth
        layout.hasDetail -> 0.dp
        else -> windowWidth
    }

    return PanePositions(
        listX = animatePaneDpAsState(target = if (layout.isListShown) 0.dp else -listWidth, layout = layout),
        listWidth = animatePaneDpAsState(target = listWidth, layout = layout),
        detailX = animatePaneDpAsState(target = detailX, layout = layout),
    )
}

/**
 * Springs to [target] when [layout] changes and keeps easing while that runs; otherwise follows it, as on a drag.
 */
@Composable
private fun animatePaneDpAsState(
    target: Dp,
    layout: PaneLayout,
): Dp {
    val value = remember { Animatable(target, Dp.VectorConverter) }
    var lastLayout by remember { mutableStateOf(layout) }
    var isEasing by remember { mutableStateOf(false) }

    LaunchedEffect(target, layout) {
        val isLayoutChange = layout != lastLayout
        lastLayout = layout
        if (isLayoutChange || isEasing) {
            isEasing = true
            value.animateTo(
                targetValue = target,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = ListDetailDefaults.PANE_STIFFNESS,
                ),
            )
            isEasing = false
        } else {
            value.snapTo(target)
        }
    }

    return value.value
}
