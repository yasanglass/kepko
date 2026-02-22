package glass.yasan.kepko.persistence

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

@Composable
public actual fun rememberKepkoSettings(name: String): Settings {
    val context = LocalContext.current

    return remember(name) {
        SharedPreferencesSettings(
            context.getSharedPreferences(name, Context.MODE_PRIVATE),
        )
    }
}
