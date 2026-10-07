package glass.yasan.kepko.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester

/**
 * Focuses the element it is attached to, so previews can show the focused state.
 */
@Composable
internal fun rememberPreviewFocusRequester(): FocusRequester {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(focusRequester) { focusRequester.requestFocus() }
    return focusRequester
}
