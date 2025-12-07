package glass.yasan.concrete.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import glass.yasan.concrete.composeapp.generated.resources.Res
import glass.yasan.concrete.composeapp.generated.resources.app_icon
import glass.yasan.concrete.composeapp.generated.resources.app_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun main() {
    application {
        val icon by rememberUpdatedState(painterResource(Res.drawable.app_icon))

        Window(
            onCloseRequest = ::exitApplication,
            title = stringResource(Res.string.app_title),
            icon = icon,
        ) {
            SampleApp()
        }
    }
}
