package glass.yasan.kepko.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import glass.yasan.kepko.component.SegmentedPickerDisplayMode.ICON
import glass.yasan.kepko.component.SegmentedPickerDisplayMode.ICON_WITH_TEXT
import glass.yasan.kepko.component.SegmentedPickerDisplayMode.ICON_WITH_TEXT_EXPAND
import glass.yasan.kepko.component.SegmentedPickerDisplayMode.ICON_WITH_TEXT_REVEAL
import glass.yasan.kepko.foundation.border.border
import glass.yasan.kepko.foundation.theme.KepkoTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration

@Composable
public fun <T> SegmentedPicker(
    items: List<SegmentedPickerItem<T>>,
    selected: T,
    onSelect: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = SegmentedPickerDefaults.shape(),
    contentPadding: PaddingValues = SegmentedPickerDefaults.contentPadding(),
    indicatorInset: Dp = SegmentedPickerDefaults.IndicatorInset,
    displayMode: SegmentedPickerDisplayMode = ICON_WITH_TEXT,
    revealDuration: Duration = SegmentedPickerDefaults.RevealDuration,
    colors: SegmentedPickerColors = SegmentedPickerDefaults.colors(),
) {
    if (items.isEmpty()) return

    val selectedIndex = items.indexOfFirst { it.value == selected }.coerceAtLeast(0)
    val animatedIndex = animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        label = "SegmentedPickerIndicatorIndex",
    )
    val revealText = rememberRevealTextState(selectedIndex, displayMode, revealDuration)
    val expandProgress = animateFloatAsState(
        targetValue = if (revealText && displayMode == ICON_WITH_TEXT_EXPAND) 1f else 0f,
        label = "SegmentedPickerExpandProgress",
    )

    Layout(
        modifier = modifier
            .clip(shape)
            .background(colors.containerColor)
            .border(color = colors.outlineColor, shape = shape),
        content = {
            SegmentedPickerIndicator(shape = shape, colors = colors)
            SegmentedPickerItems(
                items = items,
                selectedIndex = selectedIndex,
                enabled = enabled,
                displayMode = displayMode,
                revealText = revealText,
                expandProgress = expandProgress.value,
                onSelect = onSelect,
                contentPadding = contentPadding,
                colors = colors,
            )
        },
        measurePolicy = rememberSegmentedPickerMeasurePolicy(
            displayMode = displayMode,
            selectedIndex = selectedIndex,
            animatedIndex = animatedIndex,
            expandProgress = expandProgress,
            indicatorInset = indicatorInset,
        ),
    )
}

@Composable
private fun rememberRevealTextState(
    selectedIndex: Int,
    displayMode: SegmentedPickerDisplayMode,
    revealDuration: Duration,
): Boolean {
    var revealText by remember { mutableStateOf(false) }
    var isInitialComposition by remember { mutableStateOf(true) }

    LaunchedEffect(selectedIndex) {
        if (isInitialComposition) {
            isInitialComposition = false
            return@LaunchedEffect
        }
        if (displayMode == ICON_WITH_TEXT_REVEAL || displayMode == ICON_WITH_TEXT_EXPAND) {
            revealText = true
        }
    }

    LaunchedEffect(selectedIndex, revealText) {
        if (!revealText) return@LaunchedEffect
        delay(revealDuration)
        revealText = false
    }

    return revealText
}

private fun <T> SegmentedPickerItem<T>.shouldShowText(
    displayMode: SegmentedPickerDisplayMode,
    isSelected: Boolean,
    revealText: Boolean,
): Boolean {
    if (text == null) return false
    if (icon == null) return true
    return when (displayMode) {
        ICON_WITH_TEXT -> true
        ICON -> false
        ICON_WITH_TEXT_REVEAL, ICON_WITH_TEXT_EXPAND -> isSelected && revealText
    }
}

private sealed interface WidthMode {
    data object Natural : WidthMode
    data object Equalize : WidthMode
    data class Expand(val selectedIndex: Int, val progress: Float) : WidthMode
}

private class MeasuredItems(
    val placeables: List<Placeable>,
    val widths: IntArray,
    val height: Int,
) {
    operator fun component1(): List<Placeable> = placeables
    operator fun component2(): IntArray = widths
    operator fun component3(): Int = height
}

private fun measureItems(
    itemMeasurables: List<Measurable>,
    constraints: Constraints,
    widthMode: WidthMode,
): MeasuredItems {
    val itemCount = itemMeasurables.size
    val intrinsicHeight = itemMeasurables.maxOf {
        it.maxIntrinsicHeight(Constraints.Infinity)
    }
    val targetHeight = intrinsicHeight.coerceIn(constraints.minHeight, constraints.maxHeight)

    if (widthMode is WidthMode.Natural) {
        val perItemMinWidth = if (itemCount == 0) 0 else constraints.minWidth / itemCount
        val perItemMaxWidth = if (constraints.maxWidth == Constraints.Infinity || itemCount == 0) {
            constraints.maxWidth
        } else {
            constraints.maxWidth / itemCount
        }
        val placeables = itemMeasurables.map { m ->
            m.measure(
                Constraints(
                    minWidth = perItemMinWidth,
                    maxWidth = perItemMaxWidth.coerceAtLeast(perItemMinWidth),
                    minHeight = targetHeight,
                    maxHeight = targetHeight,
                )
            )
        }
        val widths = IntArray(itemCount) { placeables[it].width }
        return MeasuredItems(placeables = placeables, widths = widths, height = targetHeight)
    }

    val excludedIndex = (widthMode as? WidthMode.Expand)?.selectedIndex ?: -1
    val maxIntrinsic = (0 until itemCount)
        .filter { it != excludedIndex }
        .maxOfOrNull { itemMeasurables[it].maxIntrinsicWidth(Constraints.Infinity) }
        ?: itemMeasurables[0].maxIntrinsicWidth(Constraints.Infinity)
    val baseTotal = maxIntrinsic * itemCount
    val targetTotalWidth = baseTotal.coerceIn(constraints.minWidth, constraints.maxWidth)
    val equalWidth = if (itemCount > 0) targetTotalWidth / itemCount else 0

    val widths = when (widthMode) {
        WidthMode.Equalize, WidthMode.Natural -> IntArray(itemCount) { equalWidth }
        is WidthMode.Expand -> IntArray(itemCount) { i ->
            if (i == widthMode.selectedIndex) {
                lerp(equalWidth, targetTotalWidth, widthMode.progress)
            } else {
                lerp(equalWidth, 0, widthMode.progress)
            }
        }
    }
    val correction = targetTotalWidth - widths.sum()
    if (correction != 0) {
        val correctionIndex = (widthMode as? WidthMode.Expand)?.selectedIndex ?: (itemCount - 1)
        widths[correctionIndex] += correction
    }

    val placeables = itemMeasurables.mapIndexed { i, m ->
        m.measure(Constraints.fixed(widths[i].coerceAtLeast(0), targetHeight))
    }
    return MeasuredItems(placeables = placeables, widths = widths, height = targetHeight)
}

@Composable
private fun <T> SegmentedPickerItemContent(
    item: SegmentedPickerItem<T>,
    selected: Boolean,
    enabled: Boolean,
    showText: Boolean,
    contentAlpha: Float,
    onClick: () -> Unit,
    contentPadding: PaddingValues,
    colors: SegmentedPickerColors,
    modifier: Modifier = Modifier,
) {
    val targetContentColor = when {
        !enabled -> colors.disabledContentColor
        selected -> colors.selectedContentColor
        else -> colors.unselectedContentColor
    }
    val contentColor by animateColorAsState(
        targetValue = targetContentColor,
        label = "SegmentedPickerItemContentColor",
    )
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick,
            )
            .alpha(contentAlpha)
            .padding(contentPadding),
    ) {
        AnimatedContent(
            targetState = showText,
            transitionSpec = {
                fadeIn() togetherWith fadeOut() using SizeTransform(clip = false)
            },
            label = "SegmentedPickerItemReveal",
        ) { textVisible ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                item.icon?.let { painter ->
                    Icon(
                        painter = painter,
                        contentDescription = if (textVisible) null else (item.contentDescription ?: item.text),
                        tint = contentColor,
                    )
                }
                if (textVisible && item.text != null) {
                    Text(
                        text = item.text.uppercase(),
                        color = contentColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}

@Composable
private fun SegmentedPickerIndicator(
    shape: Shape,
    colors: SegmentedPickerColors,
) {
    Box(
        modifier = Modifier
            .clip(shape)
            .background(colors.indicatorColor)
            .border(color = colors.outlineColor, shape = shape),
    )
}

@Composable
private fun <T> SegmentedPickerItems(
    items: List<SegmentedPickerItem<T>>,
    selectedIndex: Int,
    enabled: Boolean,
    displayMode: SegmentedPickerDisplayMode,
    revealText: Boolean,
    expandProgress: Float,
    onSelect: (T) -> Unit,
    contentPadding: PaddingValues,
    colors: SegmentedPickerColors,
) {
    items.forEachIndexed { index, item ->
        val isSelected = index == selectedIndex
        SegmentedPickerItemContent(
            item = item,
            selected = isSelected,
            enabled = enabled && item.enabled,
            showText = item.shouldShowText(displayMode, isSelected, revealText),
            contentAlpha = if (displayMode == ICON_WITH_TEXT_EXPAND && !isSelected) {
                1f - expandProgress
            } else 1f,
            onClick = { onSelect(item.value) },
            contentPadding = contentPadding,
            colors = colors,
        )
    }
}

@Composable
private fun rememberSegmentedPickerMeasurePolicy(
    displayMode: SegmentedPickerDisplayMode,
    selectedIndex: Int,
    animatedIndex: State<Float>,
    expandProgress: State<Float>,
    indicatorInset: Dp,
): MeasurePolicy = remember(displayMode, selectedIndex, indicatorInset) {
    MeasurePolicy { measurables, constraints ->
        val indicatorMeasurable = measurables.first()
        val itemMeasurables = measurables.drop(1)
        val itemCount = itemMeasurables.size

        val widthMode = when (displayMode) {
            ICON_WITH_TEXT_REVEAL -> WidthMode.Natural
            ICON_WITH_TEXT_EXPAND -> WidthMode.Expand(selectedIndex, expandProgress.value)
            ICON_WITH_TEXT, ICON -> WidthMode.Equalize
        }
        val (itemPlaceables, itemWidths, height) = measureItems(
            itemMeasurables = itemMeasurables,
            constraints = constraints,
            widthMode = widthMode,
        )
        val totalWidth = itemWidths.sum().coerceIn(constraints.minWidth, constraints.maxWidth)

        val itemOffsets = IntArray(itemCount + 1).also { offsets ->
            for (i in 0 until itemCount) offsets[i + 1] = offsets[i] + itemWidths[i]
        }

        val position = animatedIndex.value.coerceIn(0f, (itemCount - 1).toFloat())
        val fromSegment = position.toInt().coerceIn(0, itemCount - 1)
        val toSegment = (fromSegment + 1).coerceAtMost(itemCount - 1)
        val progress = position - fromSegment
        val indicatorX = lerp(itemOffsets[fromSegment], itemOffsets[toSegment], progress)
        val indicatorWidth = lerp(itemWidths[fromSegment], itemWidths[toSegment], progress)
        val insetPx = indicatorInset.roundToPx()
        val indicatorPlaceable = indicatorMeasurable.measure(
            Constraints.fixed(
                (indicatorWidth - 2 * insetPx).coerceAtLeast(1),
                (height - 2 * insetPx).coerceAtLeast(1),
            )
        )

        layout(totalWidth, height) {
            indicatorPlaceable.placeRelative(x = indicatorX + insetPx, y = insetPx)
            itemPlaceables.forEachIndexed { i, placeable ->
                placeable.placeRelative(x = itemOffsets[i], y = 0)
            }
        }
    }
}

@PreviewWithTest
@Composable
internal fun SegmentedPickerLightPreview() {
    KepkoTheme(palette = LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SegmentedPickerDarkPreview() {
    KepkoTheme(palette = DARK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SegmentedPickerBlackPreview() {
    KepkoTheme(palette = BLACK) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SegmentedPickerSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { PreviewContent() }
}

@PreviewWithTest
@Composable
internal fun SegmentedPickerSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { PreviewContent() }
}

@Composable
private fun PreviewContent() {
    val textItems = listOf(
        SegmentedPickerItem(value = "day", text = "Day"),
        SegmentedPickerItem(value = "week", text = "Week"),
        SegmentedPickerItem(value = "month", text = "Month"),
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.midground)
            .padding(16.dp),
    ) {
        SegmentedPicker(
            items = textItems,
            selected = "week",
            onSelect = {},
        )
        SegmentedPicker(
            items = textItems,
            selected = "day",
            onSelect = {},
            enabled = false,
        )
    }
}
