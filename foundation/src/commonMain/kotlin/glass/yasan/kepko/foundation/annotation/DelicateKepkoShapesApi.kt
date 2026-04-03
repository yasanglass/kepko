package glass.yasan.kepko.foundation.annotation

@RequiresOptIn(
    level = ERROR,
    message = "This is a delicate Kepko shapes API that is not dynamically themed" +
            " and will not respond to shape preference changes. Use with caution.",
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
public annotation class DelicateKepkoShapesApi
