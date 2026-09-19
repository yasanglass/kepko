package glass.yasan.kepko.foundation.system

import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi

@ExperimentalKepkoApi
public actual fun DialogProperties.withScrimColor(color: Color): DialogProperties = this

@ExperimentalKepkoApi
@Composable
public actual fun DialogScrimEffect(color: Color) {
    val dialogWindow = currentDialogWindow()

    LaunchedEffect(dialogWindow, color) {
        dialogWindow ?: return@LaunchedEffect
        dialogWindow.setDimAmount(color.alpha)
        dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }
}
