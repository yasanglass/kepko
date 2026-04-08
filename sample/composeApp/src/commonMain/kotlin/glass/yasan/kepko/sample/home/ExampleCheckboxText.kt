package glass.yasan.kepko.sample.home

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import glass.yasan.kepko.component.CheckboxText
import glass.yasan.kepko.component.HorizontalDivider

internal fun LazyListScope.exampleCheckboxText() {
    item {
        HorizontalDivider()
    }
    item {
        val checked = rememberSaveable { mutableStateOf(false) }

        CheckboxText(
            text = "CheckboxText",
            checked = checked.value,
            onCheckedChange = { checked.value = it },
        )
    }
    item {
        CheckboxText(
            text = "CheckboxText (Disabled)",
            checked = true,
            onCheckedChange = {},
            enabled = false,
        )
    }
}
