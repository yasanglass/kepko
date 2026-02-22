package glass.yasan.kepko.persistence

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runDesktopComposeUiTest
import glass.yasan.kepko.foundation.theme.ThemeStyle
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalTestApi::class)
internal class LocalKepkoThemeStyleTest {

    @Test
    fun givenNoProvider_whenAccessingLocalKepkoThemeStyle_thenThrowsHelpfulError() {
        // When
        val exception = assertFailsWith<IllegalStateException> {
            runDesktopComposeUiTest {
                setContent {
                    LocalKepkoThemeStyle.current
                }
            }
        }

        // Then
        assertContains(exception.message.orEmpty(), "ThemeStyle is not provided")
    }

    @Test
    fun givenProvidedStyle_whenReadingLocalKepkoThemeStyle_thenReturnsSameStyle() {
        // Given
        var current: ThemeStyle? = null

        // When
        runDesktopComposeUiTest {
            setContent {
                CompositionLocalProvider(LocalKepkoThemeStyle provides ThemeStyle.BLACK) {
                    current = LocalKepkoThemeStyle.current
                }
            }

            waitForIdle()
        }

        // Then
        assertEquals(ThemeStyle.BLACK, current)
    }
}
