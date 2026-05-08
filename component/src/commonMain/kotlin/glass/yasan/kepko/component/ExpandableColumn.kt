package glass.yasan.kepko.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons

@Composable
public fun ExpandableColumn(
    title: String,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape(),
    colors: ExpandableColumnColors = ExpandableColumnDefaults.colors(),
    border: BorderStroke? = ExpandableColumnDefaults.border,
    contentSpacing: Dp = 8.dp,
    contentEnterTransition: EnterTransition = ExpandableColumnDefaults.contentEnterTransition(),
    contentExitTransition: ExitTransition = ExpandableColumnDefaults.contentExitTransition(),
    chevronAnimationSpec: AnimationSpec<Float> = ExpandableColumnDefaults.chevronAnimationSpec(),
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Button(
            text = title,
            onClick = { onExpandChange(expanded.not()) },
            leadingContent = {
                ExpandableColumnIcon(icon = icon)
            },
            trailingContent = {
                ExpandableColumnChevron(
                    expanded = expanded,
                    animationSpec = chevronAnimationSpec,
                )
            },
            enabled = enabled,
            shape = shape,
            containerColor = colors.containerColor,
            contentColor = colors.contentColor,
            border = border,
        )
        ExpandableColumnContent(
            expanded = expanded,
            contentSpacing = contentSpacing,
            enterTransition = contentEnterTransition,
            exitTransition = contentExitTransition,
            content = content,
        )
    }
}

@Composable
private fun ExpandableColumnContent(
    expanded: Boolean,
    contentSpacing: Dp,
    enterTransition: EnterTransition,
    exitTransition: ExitTransition,
    content: @Composable ColumnScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = expanded,
        enter = enterTransition,
        exit = exitTransition,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(Modifier.height(contentSpacing))
            content()
        }
    }
}

@Composable
private fun ExpandableColumnIcon(
    icon: Painter?,
) {
    if (icon != null) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 12.dp),
        )
    }
}

@Composable
private fun ExpandableColumnChevron(
    expanded: Boolean,
    animationSpec: AnimationSpec<Float>,
) {
    val chevronScaleY by animateFloatAsState(
        targetValue = if (expanded) -1f else 1f,
        animationSpec = animationSpec,
        label = "expandableColumnChevronScaleY",
    )

    Icon(
        painter = Icons.chevronDown,
        contentDescription = null,
        modifier = Modifier
            .padding(start = 12.dp)
            .graphicsLayer { scaleY = chevronScaleY },
    )
}

@PreviewWithTest
@Composable
internal fun ExpandableColumnLightPreview() {
    KepkoTheme(palette = LIGHT) { ExpandableColumnPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ExpandableColumnDarkPreview() {
    KepkoTheme(palette = DARK) { ExpandableColumnPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ExpandableColumnBlackPreview() {
    KepkoTheme(palette = BLACK) { ExpandableColumnPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ExpandableColumnSolarizedLightPreview() {
    KepkoTheme(palette = SOLARIZED_LIGHT) { ExpandableColumnPreviewContent() }
}

@PreviewWithTest
@Composable
internal fun ExpandableColumnSolarizedDarkPreview() {
    KepkoTheme(palette = SOLARIZED_DARK) { ExpandableColumnPreviewContent() }
}

@Composable
private fun ExpandableColumnPreviewContent() {
    var expanded by rememberSaveable { mutableStateOf(true) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(KepkoTheme.colors.foreground)
            .padding(16.dp),
    ) {
        ExpandableColumn(
            title = "Advanced",
            expanded = expanded,
            onExpandChange = { expanded = it },
            icon = Icons.build,
        ) {
            Button(
                text = "Manage folders",
                leadingIcon = Icons.folder,
                trailingIcon = Icons.chevronForward,
                onClick = {},
            )
            Button(
                text = "Edit name",
                leadingIcon = Icons.edit,
                onClick = {},
            )
            Button(
                text = "Edit icon",
                leadingIcon = Icons.palette,
                onClick = {},
            )
        }
    }
}
