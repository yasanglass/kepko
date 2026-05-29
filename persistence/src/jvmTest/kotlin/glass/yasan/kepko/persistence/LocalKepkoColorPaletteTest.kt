package glass.yasan.kepko.persistence

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import glass.yasan.kepko.foundation.theme.ColorPalette
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalTestApi::class)
internal class LocalKepkoColorPaletteTest {

    @Test
    fun givenNoProvider_whenAccessingLocalKepkoColorPalette_thenThrowsHelpfulError() {
        // When
        val exception = assertFailsWith<IllegalStateException> {
            runDesktopComposeUiTest {
                setContent {
                    LocalKepkoColorPalette.current
                }
            }
        }

        // Then
        assertContains(exception.message.orEmpty(), "ColorPalette is not provided")
    }

    @Test
    fun givenProvidedPalette_whenReadingLocalKepkoColorPalette_thenReturnsSamePalette() {
        // Given
        var current: ColorPalette? = null

        // When
        runDesktopComposeUiTest {
            setContent {
                CompositionLocalProvider(LocalKepkoColorPalette provides ColorPalette.BLACK) {
                    current = LocalKepkoColorPalette.current
                }
            }

            waitForIdle()
        }

        // Then
        assertEquals(ColorPalette.BLACK, current)
    }
}
