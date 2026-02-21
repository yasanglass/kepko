package glass.yasan.kepko.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.extensions.groupBySegment

@Composable
internal fun SegmentedColumn(
    items: List<PreferenceRadioGroupItem>,
    modifier: Modifier = Modifier,
    segmentContent: @Composable (segment: List<PreferenceRadioGroupItem>) -> Unit,
) {
    Column(modifier = modifier) {
        val segments = remember(items) { items.groupBySegment() }

        segments.entries.forEachIndexed { index, (_, segmentItems) ->
            if (index > 0) {
                Spacer(Modifier.height(16.dp))
                HorizontalDivider(Modifier.padding(horizontal = 16.dp))
                Spacer(Modifier.height(16.dp))
            }
            segmentContent(segmentItems)
        }
    }
}
