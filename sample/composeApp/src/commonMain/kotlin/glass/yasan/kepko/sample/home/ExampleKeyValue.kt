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
            key = "KeyValue Row Clickable",
            value = clickCount.intValue.toString(),
            onClick = { clickCount.intValue++ },
        )
    }
    item {
        val entries = listOf(
            "S",
            "Medium",
            "Longer text" ,
            "This is a much longer text pill that keeps on going and going" ,
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
            value = entries[selectedIndex.intValue],
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
            key = "KeyValue Leading Info Icon",
            value = "Heads up",
            leadingIcon = Icons.info,
        )
    }
    item {
        val clickCount = remember { mutableIntStateOf(0) }
        KeyValue(
            key = "KeyValue Leading Warning Icon",
            value = clickCount.intValue.toString(),
            leadingIcon = Icons.warning,
            onClick = { clickCount.intValue++ },
        )
    }
    item {
        KeyValue(
            key = "KeyValue Leading and Trailing Icons",
            value = "Verified",
            leadingIcon = Icons.verified,
            trailingIcon = Icons.chevronForward,
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
