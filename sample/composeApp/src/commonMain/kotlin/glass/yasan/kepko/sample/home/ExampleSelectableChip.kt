package glass.yasan.kepko.sample.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.HorizontalDivider
import glass.yasan.kepko.component.SelectableChip
import glass.yasan.kepko.component.SelectableChipDefaults
import glass.yasan.kepko.composeapp.generated.resources.Res
import glass.yasan.kepko.composeapp.generated.resources.ic_bolt
import glass.yasan.kepko.composeapp.generated.resources.ic_eco
import glass.yasan.kepko.composeapp.generated.resources.ic_heart_smile
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons
import org.jetbrains.compose.resources.painterResource

@Suppress("LongMethod")
internal fun LazyListScope.exampleSelectableChip() {
    item { HorizontalDivider() }
    item {
        val selected = rememberSaveable { mutableStateOf(false) }

        SelectableChip(
            title = "SelectableChip",
            selected = selected.value,
            onSelectedChange = { selected.value = it },
            leadingIcon = painterResource(Res.drawable.ic_bolt),
        )
    }
    item {
        val selected = rememberSaveable { mutableStateOf(true) }

        SelectableChip(
            title = "Initially Selected",
            selected = selected.value,
            onSelectedChange = { selected.value = it },
            leadingIcon = painterResource(Res.drawable.ic_heart_smile),
        )
    }
    item {
        val selected = rememberSaveable { mutableStateOf(false) }

        SelectableChip(
            title = "No Leading Icon",
            selected = selected.value,
            onSelectedChange = { selected.value = it },
        )
    }
    item {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            val filters = listOf("All", "Active", "Archived")
            val selected = rememberSaveable { mutableStateOf(filters.first()) }

            filters.forEach { filter ->
                SelectableChip(
                    title = filter,
                    selected = selected.value == filter,
                    onSelectedChange = { selected.value = filter },
                )
            }
        }
    }
    item {
        val selected = rememberSaveable { mutableStateOf(true) }

        SelectableChip(
            title = "Custom Colors",
            selected = selected.value,
            onSelectedChange = { selected.value = it },
            leadingIcon = painterResource(Res.drawable.ic_eco),
            colors = SelectableChipDefaults.colors(
                unselectedContainerColor = KepkoTheme.colors.midground,
                selectedContainerColor = KepkoTheme.colors.information,
                selectedContentColor = KepkoTheme.colors.onInformation,
            ),
        )
    }
    item {
        SelectableChip(
            title = "Disabled (Selected)",
            selected = true,
            onSelectedChange = {},
            leadingIcon = Icons.lock,
            enabled = false,
        )
    }
    item {
        SelectableChip(
            title = "Disabled (Unselected)",
            selected = false,
            onSelectedChange = {},
            leadingIcon = Icons.lock,
            enabled = false,
        )
    }
}
