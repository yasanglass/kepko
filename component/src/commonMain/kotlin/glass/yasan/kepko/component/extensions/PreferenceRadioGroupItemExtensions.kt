package glass.yasan.kepko.component.extensions

import glass.yasan.kepko.component.PreferenceRadioGroupItem

public fun List<PreferenceRadioGroupItem>.groupBySegment(): Map<Int, List<PreferenceRadioGroupItem>> =
    groupBy { it.segment }.toSortedMap()
