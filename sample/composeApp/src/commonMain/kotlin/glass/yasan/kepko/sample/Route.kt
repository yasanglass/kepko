package glass.yasan.kepko.sample

internal sealed interface Route {

    val path: String

    data object Home : Route {
        override val path = "home"
    }

    data object Theme : Route {
        override val path = "theme"
    }

    data object Icons : Route {
        override val path = "icons"
    }

    data object Onboarding : Route {
        override val path = "onboarding"
    }

    data object Serialization : Route {
        override val path = "serialization"
    }

    data object TitleBar : Route {
        override val path = "title-bar"
    }

}
