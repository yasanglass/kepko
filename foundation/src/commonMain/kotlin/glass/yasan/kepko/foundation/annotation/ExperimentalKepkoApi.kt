package glass.yasan.kepko.foundation.annotation

@RequiresOptIn(
    level = WARNING,
    message = "This Kepko API is experimental and is likely to change or to be removed in the future.",
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
public annotation class ExperimentalKepkoApi
