package glass.yasan.kepko.navigation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class ListDetailPaneStateTest {
    @Test
    fun givenADrag_whenTheListIsResized_thenNothingIsSavedYet() {
        // given
        var saved: Dp? = null
        val state = ListDetailPaneState(listWidth = 360.dp, onSave = { width -> saved = width })

        // when
        state.resizeList(by = 40.dp, range = RANGE)

        // then
        assertEquals(400.dp, state.listWidth)
        assertNull(saved)
    }

    @Test
    fun givenADrag_whenItStops_thenTheWidthIsSaved() {
        // given
        var saved: Dp? = null
        val state = ListDetailPaneState(listWidth = 360.dp, onSave = { width -> saved = width })
        state.resizeList(by = 40.dp, range = RANGE)

        // when
        state.saveListWidth()

        // then
        assertEquals(400.dp, saved)
    }

    @Test
    fun givenADragPastTheRange_whenTheListIsResized_thenItStopsAtTheEdge() {
        // given
        val state = ListDetailPaneState(listWidth = 360.dp)

        // when
        state.resizeList(by = 500.dp, range = RANGE)

        // then
        assertEquals(RANGE.endInclusive, state.listWidth)
    }

    private companion object {
        val RANGE = 280.dp..600.dp
    }
}
