package glass.yasan.kepko.component

import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Marks a preview composable for automatic screenshot testing.
 *
 * Use alongside @Composable. @Preview is included automatically.
 */
@Preview
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class PreviewWithTest
