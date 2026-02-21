package glass.yasan.kepko.sample

internal sealed interface Route {

    val path: String

    data object Home : Route {
        override val path = "home"
    }

    data object Theme : Route {
        override val path = "theme"
    }

}
