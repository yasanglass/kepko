package glass.yasan.kepko.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.color.contentColorFor
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons
import glass.yasan.kepko.resource.Strings
import androidx.compose.material3.Scaffold as Material3Scaffold

@ExperimentalKepkoApi
@Composable
public fun Scaffold(
    title: String,
    modifier: Modifier = Modifier,
    leadingContent: @Composable (RowScope.() -> Unit)? = null,
    trailingContent: @Composable (RowScope.() -> Unit)? = null,
    bottomBar: @Composable RowScope.() -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = KepkoTheme.colors.midground,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    annotation: PreferenceAnnotation? = null,
    content: @Composable (contentPadding: PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                leadingContent?.let { it() }
                Text(
                    text = title.uppercase(),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier
                        .let { if (leadingContent == null) it.padding(start = 16.dp) else it }
                        .padding(vertical = 16.dp)
                        .let { if (trailingContent == null) it.padding(end = 16.dp) else it }
                        .weight(1f)
                )
                annotation?.let {
                    TextPill(
                        annotation = it,
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
                trailingContent?.let { it() }
            }
        },
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content,
    )
}

@ExperimentalKepkoApi
@Composable
public fun Scaffold(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    backIcon: Painter = Icons.chevronBackward,
    trailingContent: @Composable RowScope.() -> Unit = {},
    bottomBar: @Composable RowScope.() -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = KepkoTheme.colors.midground,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    annotation: PreferenceAnnotation? = null,
    content: @Composable (contentPadding: PaddingValues) -> Unit,
) {
    Scaffold(
        title = title,
        modifier = modifier,
        leadingContent = {
            Icon(
                painter = backIcon,
                contentDescription = Strings.back,
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .clickable(
                        onClick = onBackClick,
                        onClickLabel = Strings.goBack,
                    )
                    .padding(12.dp),
            )
        },
        trailingContent = trailingContent,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        annotation = annotation,
        content = content,
    )
}

@ExperimentalKepkoApi
@Composable
public fun Scaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable RowScope.() -> Unit,
    bottomBar: @Composable (RowScope.() -> Unit) = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = KepkoTheme.colors.midground,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (contentPadding: PaddingValues) -> Unit,
) {
    Material3Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                Row(
                    content = topBar,
                    modifier = Modifier
                        .background(KepkoTheme.colors.foreground)
                        .fillMaxWidth()
                        .statusBarsPadding(),
                )
                HorizontalDivider()
            }
        },
        bottomBar = {
            Column {
                HorizontalDivider()
                Row(
                    content = bottomBar,
                    modifier = Modifier
                        .background(KepkoTheme.colors.midground)
                        .fillMaxWidth()
                        .navigationBarsPadding(),
                )
            }
        },
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content,
    )
}

@PreviewWithTest
@Composable
internal fun ScaffoldBasicPreview() {
    KepkoTheme {
        Scaffold(
            title = "Title",
        ) { paddingValues ->
            PreviewScaffoldContent(paddingValues)
        }
    }
}

@PreviewWithTest
@Composable
internal fun ScaffoldWithBackIconPreview() {
    KepkoTheme {
        Scaffold(
            title = "Title",
            onBackClick = {},
        ) { paddingValues ->
            PreviewScaffoldContent(paddingValues)
        }
    }
}

@PreviewWithTest
@Composable
internal fun ScaffoldWithAnnotationPreview() {
    KepkoTheme {
        Scaffold(
            title = "Title",
            annotation = PreferenceAnnotation.new,
        ) { paddingValues ->
            PreviewScaffoldContent(paddingValues)
        }
    }
}

@PreviewWithTest
@Composable
internal fun ScaffoldWithTrailingContentPreview() {
    KepkoTheme {
        Scaffold(
            title = "Title",
            trailingContent = {
                Icon(
                    painter = Icons.chevronForward,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp),
                )
            },
        ) { paddingValues ->
            PreviewScaffoldContent(paddingValues)
        }
    }
}

@PreviewWithTest
@Composable
internal fun ScaffoldWithTrailingContentAndAnnotationPreview() {
    KepkoTheme {
        Scaffold(
            title = "Title",
            annotation = PreferenceAnnotation.experimental,
            trailingContent = {
                Icon(
                    painter = Icons.chevronForward,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp),
                )
            },
        ) { paddingValues ->
            PreviewScaffoldContent(paddingValues)
        }
    }
}

@PreviewWithTest
@Composable
internal fun ScaffoldWithBottomBarPreview() {
    KepkoTheme {
        Scaffold(
            title = "Title",
            bottomBar = {
                Text(
                    text = "Bottom Bar",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                )
            },
        ) { paddingValues ->
            PreviewScaffoldContent(paddingValues)
        }
    }
}

@PreviewWithTest
@Composable
internal fun ScaffoldWithBackIconAndBottomBarPreview() {
    KepkoTheme {
        Scaffold(
            title = "Title",
            onBackClick = {},
            bottomBar = {
                Text(
                    text = "Bottom Bar",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                )
            },
        ) { paddingValues ->
            PreviewScaffoldContent(paddingValues)
        }
    }
}

@PreviewWithTest
@Composable
internal fun ScaffoldWithCustomTopBarPreview() {
    KepkoTheme {
        Scaffold(
            topBar = {
                Text(
                    text = "Top Bar",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                )
            },
        ) { paddingValues ->
            PreviewScaffoldContent(paddingValues)
        }
    }
}

@PreviewWithTest
@Composable
internal fun ScaffoldWithCustomTopBarAndBottomBarPreview() {
    KepkoTheme {
        Scaffold(
            topBar = {
                Text(
                    text = "Top Bar",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                )
            },
            bottomBar = {
                Text(
                    text = "Bottom Bar",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                )
            },
        ) { paddingValues ->
            PreviewScaffoldContent(paddingValues)
        }
    }
}

@Composable
private fun PreviewScaffoldContent(paddingValues: PaddingValues) {
    Text(
        text = "Content",
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp),
    )
}
