package glass.yasan.kepko.sample

internal sealed interface Route {

    val path: String

    data object Home : Route {
        override val path = "home"
    }

    data object Theme : Route {
        override val path = "theme"
    }

    data object Profiles : Route {
        override val path = "profiles"
    }

    data object ProfileTheme : Route {
        override val path = "profile-theme/{profileId}"

        fun pathFor(profileId: String) = "profile-theme/$profileId"
    }

    data object Icons : Route {
        override val path = "icons"
    }

    data object Serialization : Route {
        override val path = "serialization"
    }

    data object TitleBar : Route {
        override val path = "title-bar"
    }

}
