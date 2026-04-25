package glass.yasan.kepko.resource

import androidx.compose.ui.graphics.painter.Painter
import kotlin.reflect.full.memberProperties
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class NamedIconTest {

    @Test
    fun givenDeclaredEntryId_whenFromIdOrNull_thenReturnsMatchingEntry() {
        NamedIcon.entries.forEach { icon ->
            assertEquals(icon, NamedIcon.fromIdOrNull(icon.id))
        }
    }

    @Test
    fun givenUnknownId_whenFromIdOrNull_thenReturnsNull() {
        assertNull(NamedIcon.fromIdOrNull("unknown_icon_id"))
    }

    @Test
    fun givenIconsObject_whenComparedToNamedIconEntries_thenEveryPainterIsCovered() {
        val expectedIds = Icons::class.memberProperties
            .filter { it.returnType.classifier == Painter::class }
            .map { it.name.toSnakeCase() }
            .sorted()
        val actualIds = NamedIcon.entries.map { it.id }.sorted()

        assertEquals(expectedIds, actualIds)
    }

    @Test
    fun givenNamedIconEntries_whenIdsCollected_thenAllAreUnique() {
        val ids = NamedIcon.entries.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    private fun String.toSnakeCase(): String =
        replace(Regex("([a-z0-9])([A-Z])"), "$1_$2").lowercase()
}
