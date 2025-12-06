package glass.yasan.concrete.foundation.color

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun rememberDynamicAccent(isDark: Boolean): Accent? {
    if (!isDynamicAccentSupported()) return null

    val context = LocalContext.current
    val scheme = if (isDark) {
        dynamicDarkColorScheme(context)
    } else {
        dynamicLightColorScheme(context)
    }

    return remember(scheme.primary, scheme.secondary, scheme.tertiary) {
        Accent(
            primary = scheme.primary,
            secondary = scheme.secondary,
            tertiary = scheme.tertiary,
        )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
@Composable
public actual fun isDynamicAccentSupported(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
