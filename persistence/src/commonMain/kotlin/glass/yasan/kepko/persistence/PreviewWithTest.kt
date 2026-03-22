package glass.yasan.kepko.persistence

import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class PreviewWithTest
