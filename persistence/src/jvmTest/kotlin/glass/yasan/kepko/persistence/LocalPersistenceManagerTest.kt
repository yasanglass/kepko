package glass.yasan.kepko.persistence

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runDesktopComposeUiTest
import com.russhwolf.settings.MapSettings
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertSame

@OptIn(ExperimentalTestApi::class)
internal class LocalPersistenceManagerTest {

    @Test
    fun givenNoProvider_whenAccessingLocalPersistenceManager_thenThrowsHelpfulError() {
        // When
        val exception = assertFailsWith<IllegalStateException> {
            runDesktopComposeUiTest {
                setContent {
                    LocalPersistenceManager.current
                }
            }
        }

        // Then
        assertContains(exception.message.orEmpty(), "PersistenceManager is not provided")
    }

    @Test
    fun givenProvidedManager_whenReadingLocalPersistenceManager_thenReturnsSameInstance() {
        // Given
        var provided: PersistenceManager? = null
        var current: PersistenceManager? = null

        // When
        runDesktopComposeUiTest {
            setContent {
                val manager = rememberPersistenceManager(MapSettings())
                provided = manager

                CompositionLocalProvider(LocalPersistenceManager provides manager) {
                    current = LocalPersistenceManager.current
                }
            }

            waitForIdle()
        }

        // Then
        assertNotNull(provided)
        assertSame(provided, current)
    }
}
