package glass.yasan.kepko.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.material3.ModalBottomSheet as Material3ModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun ModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    colors: ModalBottomSheetColors = ModalBottomSheetDefaults.colors(),
    shape: Shape = ModalBottomSheetDefaults.shape,
    dragHandle: @Composable (() -> Unit)? = null,
    header: @Composable ColumnScope.() -> Unit = { HorizontalDivider(Modifier.fillMaxWidth()) },
    content: @Composable ColumnScope.() -> Unit,
) {
    Material3ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = colors.containerColor,
        contentColor = colors.contentColor,
        shape = shape,
        dragHandle = dragHandle,
        modifier = modifier,
    ) {
        header()
        content()
    }
}
