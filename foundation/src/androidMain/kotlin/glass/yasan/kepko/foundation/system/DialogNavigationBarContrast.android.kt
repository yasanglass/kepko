package glass.yasan.kepko.foundation.system

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi

@ExperimentalKepkoApi
@Composable
public actual fun DialogNavigationBarContrastEffect() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return

    val dialogWindow = currentDialogWindow()

    LaunchedEffect(dialogWindow) {
        dialogWindow ?: return@LaunchedEffect
        dialogWindow.isNavigationBarContrastEnforced = false
    }
}
