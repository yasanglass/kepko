package glass.yasan.kepko.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import glass.yasan.kepko.foundation.system.SystemBarColorsEffect
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.persistence.PersistentKepkoTheme

@Preview
@Composable
fun SampleApp() {
    var activeProfileId by rememberSaveable { mutableStateOf(sampleProfiles.first().id) }

    PersistentKepkoTheme(profileId = activeProfileId) {
        val backStack = rememberSaveable(saver = SampleNavDisplayDefaults.BackStackSaver) {
            listOf<Route>(Route.Home).toMutableStateList()
        }

        SystemBarColorsEffect(
            statusBarBackgroundColor = KepkoTheme.colors.foreground,
            navigationBarBackgroundColor = KepkoTheme.colors.midground,
        )

        SampleNavDisplay(
            backStack = backStack,
            activeProfileId = activeProfileId,
            onProfileSelect = { activeProfileId = it },
        )
    }
}
