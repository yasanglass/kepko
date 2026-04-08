package glass.yasan.kepko.sample.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.HorizontalDivider
import glass.yasan.kepko.component.KeyValue
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons
import kotlinx.coroutines.delay

@Suppress("LongMethod")
internal fun LazyListScope.exampleKeyValue() {
    item { HorizontalDivider() }
    item {
        KeyValue(
            key = "KeyValue Basic",
            value = "Value",
        )
    }
    item {
        val clickCount = remember { mutableIntStateOf(0) }
        KeyValue(
            key = "KeyValue Value Clickable",
            value = clickCount.intValue.toString(),
            onValueClick = { clickCount.intValue++ },
        )
    }
    item {
        val clickCount = remember { mutableIntStateOf(0) }
        KeyValue(
            key = "KeyValue Row Clickable",
            value = clickCount.intValue.toString(),
            onClick = { clickCount.intValue++ },
        )
    }
    item {
        val entries = listOf(
            "S" to KepkoTheme.colors.caution,
            "Medium" to KepkoTheme.colors.information,
            "Longer text" to KepkoTheme.colors.success,
            "This is a much longer text pill that keeps on going and going" to KepkoTheme.colors.danger,
        )
        val selectedIndex = remember { mutableIntStateOf(0) }

        LaunchedEffect(Unit) {
            while (true) {
                delay(2_000)
                selectedIndex.intValue = (selectedIndex.intValue + 1) % entries.size
            }
        }

        KeyValue(
            key = "KeyValue Animated",
            value = entries[selectedIndex.intValue].first,
            containerColor = entries[selectedIndex.intValue].second,
        )
    }
    item {
        KeyValue(
            key = "KeyValue Information",
            value = "Active",
            containerColor = KepkoTheme.colors.information,
        )
    }
    item {
        KeyValue(
            key = "KeyValue Danger",
            value = "Locked",
            containerColor = KepkoTheme.colors.danger,
            trailingValueIcon = Icons.lock,
        )
    }
    item {
        KeyValue(
            key = "KeyValue Success",
            value = "Success",
            containerColor = KepkoTheme.colors.success,
            leadingValueIcon = Icons.check,
        )
    }
    item {
        val clickCount = remember { mutableIntStateOf(0) }
        KeyValue(
            key = "KeyValue Editable",
            value = clickCount.intValue.toString(),
            trailingIcon = Icons.edit,
            onClick = { clickCount.intValue++ },
        )
    }
    item {
        KeyValue(
            key = "KeyValue Trailing Content",
            value = "Custom",
            trailingContent = {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = KepkoTheme.colors.information,
                            shape = KepkoTheme.shapes.medium,
                        ),
                )
            },
        )
    }
}
