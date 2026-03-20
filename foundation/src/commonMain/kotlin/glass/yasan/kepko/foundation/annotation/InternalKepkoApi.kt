package glass.yasan.kepko.foundation.annotation

@RequiresOptIn(
    level = ERROR,
    message = "This is an internal Kepko API" +
            " that should not be used from outside of Kepko.",
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
public annotation class InternalKepkoApi
