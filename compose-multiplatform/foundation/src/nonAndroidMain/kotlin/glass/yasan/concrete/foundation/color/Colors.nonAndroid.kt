package glass.yasan.concrete.foundation.color

import androidx.compose.runtime.Composable

@Composable
internal actual fun rememberDynamicAccent(isDark: Boolean): Accent? = null

@Composable
public actual fun isDynamicAccentSupported(): Boolean = false
