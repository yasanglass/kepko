package glass.yasan.kepko.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.savedstate.read
import glass.yasan.kepko.foundation.system.SystemBarColorsEffect
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.persistence.PersistentKepkoTheme
import glass.yasan.kepko.persistence.PersistentPreferenceThemeScreen
import glass.yasan.kepko.persistence.UserVisibleProfile
import glass.yasan.kepko.sample.home.serialization.SerializationScreen

@Preview
@Composable
fun SampleApp() {
    var activeProfileId by rememberSaveable { mutableStateOf(sampleProfiles.first().id) }

    PersistentKepkoTheme(profileId = activeProfileId) {
        val navController = rememberNavController()

        SystemBarColorsEffect(
            statusBarBackgroundColor = KepkoTheme.colors.foreground,
            navigationBarBackgroundColor = KepkoTheme.colors.midground,
        )

        SampleNavHost(
            navController = navController,
            activeProfileId = activeProfileId,
            onProfileSelect = { activeProfileId = it },
        )
    }
}

@Suppress("LongMethod")
@Composable
private fun SampleNavHost(
    navController: NavHostController,
    activeProfileId: String,
    onProfileSelect: (String) -> Unit,
) {
        NavHost(
            navController = navController,
            startDestination = Route.Home.path,
        ) {
            composable(Route.Home.path) {
                HomeScreen(
                    onNavigateToTheme = {
                        navController.navigate(Route.Theme.path)
                    },
                    onNavigateToProfiles = {
                        navController.navigate(Route.Profiles.path)
                    },
                    onNavigateToIcons = {
                        navController.navigate(Route.Icons.path)
                    },
                    onNavigateToSerialization = {
                        navController.navigate(Route.Serialization.path)
                    },
                    onNavigateToTitleBar = {
                        navController.navigate(Route.TitleBar.path)
                    },
                )
            }
            composable(Route.Theme.path) {
                PersistentPreferenceThemeScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    activeProfile = getSampleProfileById(activeProfileId),
                )
            }
            composable(Route.Profiles.path) {
                ProfilesScreen(
                    activeProfileId = activeProfileId,
                    onProfileSelect = onProfileSelect,
                    onProfileThemeClick = { profile ->
                        navController.navigate(Route.ProfileTheme.pathFor(profile.id))
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
            composable(Route.ProfileTheme.path) { backStackEntry ->
                val profileId = backStackEntry.arguments?.read { getStringOrNull("profileId") }

                PersistentPreferenceThemeScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    activeProfile = getSampleProfileById(activeProfileId),
                    targetProfile = getSampleProfileById(profileId),
                )
            }
            composable(Route.Icons.path) {
                IconScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }
            composable(Route.Serialization.path) {
                SerializationScreen(
                    onBackClick = { navController.popBackStack() },
                )
            }
            composable(Route.TitleBar.path) {
                TitleBarScreen(
                    onBackClick = { navController.popBackStack() },
                )
            }
        }
}

private fun getSampleProfileById(id: String?): UserVisibleProfile =
    sampleProfiles.firstOrNull { it.id == id } ?: sampleProfiles.first()
