package glass.yasan.kepko.foundation.color

import androidx.compose.ui.graphics.Color
import kotlin.reflect.full.memberProperties
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class NamedColorTest {

    @Test
    fun givenDeclaredEntryId_whenFromIdOrNull_thenReturnsMatchingEntry() {
        NamedColor.entries.forEach { namedColor ->
            assertEquals(namedColor, NamedColor.fromIdOrNull(namedColor.id))
        }
    }

    @Test
    fun givenUnknownId_whenFromIdOrNull_thenReturnsNull() {
        assertNull(NamedColor.fromIdOrNull("unknown_color_id"))
    }

    @Test
    fun givenColorsClass_whenComparedToNamedColorEntries_thenEveryColorIsCovered() {
        val expectedIds = Colors::class.memberProperties
            .filter { it.returnType.classifier == Color::class }
            .map { it.name.toSnakeCase() }
            .sorted()
        val actualIds = NamedColor.entries.map { it.id }.sorted()

        assertEquals(expectedIds, actualIds)
    }

    @Test
    fun givenNamedColorEntries_whenIdsCollected_thenAllAreUnique() {
        val ids = NamedColor.entries.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    private fun String.toSnakeCase(): String =
        replace(Regex("([a-z0-9])([A-Z])"), "$1_$2").lowercase()
}
