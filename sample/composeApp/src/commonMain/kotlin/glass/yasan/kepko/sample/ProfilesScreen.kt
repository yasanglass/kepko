package glass.yasan.kepko.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.HorizontalDivider
import glass.yasan.kepko.component.PreferenceRadioGroup
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.persistence.PersistentPreferenceThemeButton
import glass.yasan.kepko.persistence.UserVisibleProfile
import glass.yasan.kepko.resource.Icons

@Composable
internal fun ProfilesScreen(
    activeProfileId: String,
    onProfileSelect: (String) -> Unit,
    onProfileThemeClick: (UserVisibleProfile) -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        title = "Profiles",
        onBackClick = onBackClick,
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp),
        ) {
            val items = sampleProfiles.map { profile ->
                PreferenceRadioGroupItem(
                    id = profile.id,
                    icon = profile.icon(),
                    title = profile.name,
                )
            }

            PreferenceRadioGroup(
                title = "Active Profile",
                description = "The app is themed with the selected profile.",
                selectedId = activeProfileId,
                items = items,
                onSelectId = onProfileSelect,
            )

            HorizontalDivider()

            sampleProfiles.forEach { profile ->
                PersistentPreferenceThemeButton(
                    targetProfile = profile,
                    onClick = { onProfileThemeClick(profile) },
                )
            }
        }
    }
}

internal val sampleProfiles = listOf(
    UserVisibleProfile(id = "personal", name = { "Personal" }, icon = { Icons.person }),
    UserVisibleProfile(id = "work", name = { "Work" }, icon = { Icons.build }),
    UserVisibleProfile(id = "gaming", name = { "Gaming" }, icon = { Icons.rocket }),
)
