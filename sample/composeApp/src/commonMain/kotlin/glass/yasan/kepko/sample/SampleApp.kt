package glass.yasan.kepko.sample

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.foundation.system.SystemBarColorsEffect
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.persistence.PersistentKepkoTheme
import glass.yasan.kepko.persistence.PersistentOnboardingThemeContent
import glass.yasan.kepko.persistence.PersistentPreferenceThemeScreen
import org.jetbrains.compose.resources.stringResource
import glass.yasan.kepko.composeapp.generated.resources.Res
import glass.yasan.kepko.composeapp.generated.resources.onboarding
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
                    onNavigateToOnboarding = {
                        navController.navigate(Route.Onboarding.path)
                    },
                    onNavigateToSerialization = {
                        navController.navigate(Route.Serialization.path)
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
            composable(Route.Onboarding.path) {
                Scaffold(
                    title = stringResource(Res.string.onboarding),
                    onBackClick = { navController.popBackStack() },
                ) { contentPadding ->
                    PersistentOnboardingThemeContent(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(contentPadding),
                    )
                }
            }
            composable(Route.Serialization.path) {
                SerializationScreen(
                    onBackClick = { navController.popBackStack() },
                )
            }
        }
}
