package glass.yasan.kepko.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import glass.yasan.kepko.persistence.PersistentPreferenceThemeScreen
import glass.yasan.kepko.persistence.UserVisibleProfile
import glass.yasan.kepko.sample.home.serialization.SerializationScreen

@Composable
internal fun SampleNavDisplay(
    backStack: SnapshotStateList<Route>,
    activeProfileId: String,
    onProfileSelect: (String) -> Unit,
) {
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()),
        entryProvider = { route ->
            sampleEntry(
                route = route,
                backStack = backStack,
                activeProfileId = activeProfileId,
                onProfileSelect = onProfileSelect,
            )
        },
    )
}

private fun sampleEntry(
    route: Route,
    backStack: SnapshotStateList<Route>,
    activeProfileId: String,
    onProfileSelect: (String) -> Unit,
): NavEntry<Route> {
    val onBackClick: () -> Unit = { backStack.removeLastOrNull() }

    return NavEntry(key = route) {
        when (route) {
            Route.Home -> HomeScreen(onNavigate = { destination -> backStack.add(destination) })

            Route.Theme -> PersistentPreferenceThemeScreen(
                onBackClick = onBackClick,
                activeProfile = getSampleProfileById(activeProfileId),
            )

            Route.Profiles -> ProfilesScreen(
                activeProfileId = activeProfileId,
                onProfileSelect = onProfileSelect,
                onProfileThemeClick = { profile -> backStack.add(Route.ProfileTheme(profile.id)) },
                onBackClick = onBackClick,
            )

            is Route.ProfileTheme -> PersistentPreferenceThemeScreen(
                onBackClick = onBackClick,
                activeProfile = getSampleProfileById(activeProfileId),
                targetProfile = getSampleProfileById(route.profileId),
            )

            Route.Icons -> IconScreen(onBackClick = onBackClick)

            Route.Serialization -> SerializationScreen(onBackClick = onBackClick)

            Route.TitleBar -> TitleBarScreen(onBackClick = onBackClick)
        }
    }
}

private fun getSampleProfileById(id: String?): UserVisibleProfile =
    sampleProfiles.firstOrNull { it.id == id } ?: sampleProfiles.first()
