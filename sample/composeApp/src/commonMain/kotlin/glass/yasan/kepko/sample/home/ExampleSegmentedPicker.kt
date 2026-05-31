package glass.yasan.kepko.sample.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.HorizontalDivider
import glass.yasan.kepko.component.SegmentedPicker
import glass.yasan.kepko.component.SegmentedPickerDefaults
import glass.yasan.kepko.component.SegmentedPickerDisplayMode
import glass.yasan.kepko.component.SegmentedPickerItem
import glass.yasan.kepko.component.SelectableChip
import glass.yasan.kepko.composeapp.generated.resources.Res
import glass.yasan.kepko.composeapp.generated.resources.ic_bolt
import glass.yasan.kepko.composeapp.generated.resources.ic_eco
import glass.yasan.kepko.composeapp.generated.resources.ic_heart_smile
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons
import org.jetbrains.compose.resources.painterResource

@Suppress("LongMethod")
internal fun LazyListScope.exampleSegmentedPicker() {
    item { HorizontalDivider() }
    item {
        val items = listOf(
            SegmentedPickerItem(value = "day", text = "Day"),
            SegmentedPickerItem(value = "week", text = "Week"),
            SegmentedPickerItem(value = "month", text = "Month"),
        )
        var selected by rememberSaveable { mutableStateOf("week") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(value = "day", text = "Day"),
            SegmentedPickerItem(value = "week", text = "Week"),
            SegmentedPickerItem(value = "month", text = "Month"),
        )
        var selected by rememberSaveable { mutableStateOf("day") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
            modifier = Modifier.fillMaxWidth(),
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(value = "low", text = "Low"),
            SegmentedPickerItem(value = "medium", text = "Medium"),
            SegmentedPickerItem(value = "high", text = "High"),
            SegmentedPickerItem(value = "max", text = "Max"),
        )
        var selected by rememberSaveable { mutableStateOf("low") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(
                value = "hide",
                icon = Icons.visibilityOff,
                text = "Hide",
            ),
            SegmentedPickerItem(
                value = "auto",
                icon = Icons.autoAwesome,
                text = "Auto",
            ),
            SegmentedPickerItem(
                value = "pin",
                icon = Icons.star,
                text = "Pin",
            ),
        )
        var selected by rememberSaveable { mutableStateOf("auto") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(
                value = "bolt",
                icon = painterResource(Res.drawable.ic_bolt),
                contentDescription = "Bolt",
            ),
            SegmentedPickerItem(
                value = "eco",
                icon = painterResource(Res.drawable.ic_eco),
                contentDescription = "Eco",
            ),
            SegmentedPickerItem(
                value = "heart",
                icon = painterResource(Res.drawable.ic_heart_smile),
                contentDescription = "Heart",
            ),
        )
        var selected by rememberSaveable { mutableStateOf("eco") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(value = "all", text = "All"),
            SegmentedPickerItem(value = "active", text = "Active"),
            SegmentedPickerItem(value = "archived", text = "Archived", enabled = false),
        )
        var selected by rememberSaveable { mutableStateOf("all") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(value = "yes", text = "Yes"),
            SegmentedPickerItem(value = "no", text = "No"),
        )

        SegmentedPicker(
            items = items,
            selected = "yes",
            onSelect = {},
            enabled = false,
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(value = "on", text = "On"),
            SegmentedPickerItem(value = "auto", text = "Auto"),
            SegmentedPickerItem(value = "off", text = "Off"),
        )
        var selected by rememberSaveable { mutableStateOf("auto") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
            colors = SegmentedPickerDefaults.colors(
                containerColor = KepkoTheme.colors.midground,
                indicatorColor = KepkoTheme.colors.information,
                selectedContentColor = KepkoTheme.colors.onInformation,
            ),
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(
                value = "bolt",
                icon = painterResource(Res.drawable.ic_bolt),
                text = "Fast",
            ),
            SegmentedPickerItem(
                value = "eco",
                icon = painterResource(Res.drawable.ic_eco),
                text = "Eco",
            ),
            SegmentedPickerItem(
                value = "heart",
                icon = painterResource(Res.drawable.ic_heart_smile),
                text = "Cozy",
            ),
        )
        var selected by rememberSaveable { mutableStateOf("eco") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
            displayMode = SegmentedPickerDisplayMode.ICON_WITH_TEXT_REVEAL,
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(
                value = "bolt",
                icon = painterResource(Res.drawable.ic_bolt),
                text = "Fast",
            ),
            SegmentedPickerItem(
                value = "eco",
                icon = painterResource(Res.drawable.ic_eco),
                text = "Eco",
            ),
            SegmentedPickerItem(
                value = "heart",
                icon = painterResource(Res.drawable.ic_heart_smile),
                text = "Cozy",
            ),
        )
        var selected by rememberSaveable { mutableStateOf("eco") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
            displayMode = SegmentedPickerDisplayMode.ICON,
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(
                value = "bolt",
                icon = painterResource(Res.drawable.ic_bolt),
                text = "Fast",
            ),
            SegmentedPickerItem(
                value = "eco",
                icon = painterResource(Res.drawable.ic_eco),
                text = "Eco",
            ),
            SegmentedPickerItem(
                value = "heart",
                icon = painterResource(Res.drawable.ic_heart_smile),
                text = "Cozy",
            ),
        )
        var selected by rememberSaveable { mutableStateOf("eco") }

        SegmentedPicker(
            items = items,
            selected = selected,
            onSelect = { selected = it },
            displayMode = SegmentedPickerDisplayMode.ICON_WITH_TEXT_EXPAND,
        )
    }
    item {
        val items = listOf(
            SegmentedPickerItem(value = "day", text = "Day"),
            SegmentedPickerItem(value = "week", text = "Week"),
            SegmentedPickerItem(value = "month", text = "Month"),
        )
        var selected by rememberSaveable { mutableStateOf("week") }
        var filterSelected by rememberSaveable { mutableStateOf(false) }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SegmentedPicker(
                items = items,
                selected = selected,
                onSelect = { selected = it },
            )
            SelectableChip(
                title = "Filter",
                selected = filterSelected,
                onSelectedChange = { filterSelected = it },
            )
        }
    }
}
