package glass.yasan.concrete.foundation.color

import android.os.Build
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun rememberDynamicAccent(isDark: Boolean): DynamicAccent? {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
        return null
    }

    val context = LocalContext.current
    val scheme = if (isDark) {
        dynamicDarkColorScheme(context)
    } else {
        dynamicLightColorScheme(context)
    }

    return remember(scheme.primary, scheme.secondary, scheme.tertiary) {
        DynamicAccent(
            primary = scheme.primary,
            secondary = scheme.secondary,
            tertiary = scheme.tertiary,
        )
    }
}
