package glass.yasan.kepko.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.foundation.system.SystemBarColorsEffect
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.persistence.PersistentKepkoTheme
import glass.yasan.kepko.persistence.PersistentPreferenceThemeScreen
import glass.yasan.kepko.sample.home.serialization.SerializationScreen

@Preview
@Composable
fun SampleApp() {
    PersistentKepkoTheme {
        val navController = rememberNavController()

        SystemBarColorsEffect(
            statusBarBackgroundColor = KepkoTheme.colors.foreground,
            navigationBarBackgroundColor = KepkoTheme.colors.midground,
        )

        SampleNavHost(navController = navController)
    }
}

@Suppress("LongMethod")
@Composable
private fun SampleNavHost(
    navController: NavHostController,
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
