package glass.yasan.kepko.foundation.system

import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider

@Composable
internal fun currentDialogWindow(): Window? {
    val view = LocalView.current
    if (view.isInEditMode) return null

    return (view.parent as? DialogWindowProvider)?.window
}
