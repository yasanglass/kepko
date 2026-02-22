package glass.yasan.kepko.sample

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.persistence.LocalKepkoPersistenceManager
import glass.yasan.kepko.persistence.PersistentKepkoTheme
import glass.yasan.kepko.persistence.PersistentPreferenceThemeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SampleApp() {
    PersistentKepkoTheme {
        val navController = rememberNavController()
        val persistenceManager = LocalKepkoPersistenceManager.current

        SystemBarColorsEffect(
            statusBarColor = KepkoTheme.colors.midground,
            navigationBarColor = KepkoTheme.colors.midground,
            isDark = persistenceManager.style().isDark,
        )

        NavHost(
            navController = navController,
            startDestination = Route.Home.path,
        ) {
            composable(Route.Home.path) {
                HomeScreen(
                    onNavigateToTheme = {
                        navController.navigate(Route.Theme.path)
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
        }
    }
}
