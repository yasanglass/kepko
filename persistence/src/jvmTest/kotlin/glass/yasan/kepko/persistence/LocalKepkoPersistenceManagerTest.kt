package glass.yasan.kepko.persistence

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import com.russhwolf.settings.MapSettings
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl
import glass.yasan.kepko.persistence.internal.SingletonPersistenceManager
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertSame

@OptIn(ExperimentalTestApi::class, ExperimentalKepkoApi::class)
internal class LocalKepkoPersistenceManagerTest {

    @Test
    fun givenDefaultPersistentTheme_whenReadingManager_thenReturnsPublicDefaultInstance() {
        // Given
        var current: PersistenceManager? = null

        // When
        runDesktopComposeUiTest {
            setContent {
                PersistentKepkoTheme {
                    current = LocalKepkoPersistenceManager.current
                }
            }

            waitForIdle()
        }

        // Then
        assertSame(SingletonPersistenceManager.instance, current)
    }

    @Test
    fun givenNoProvider_whenAccessingLocalKepkoPersistenceManager_thenThrowsHelpfulError() {
        // When
        val exception = assertFailsWith<IllegalStateException> {
            runDesktopComposeUiTest {
                setContent {
                    LocalKepkoPersistenceManager.current
                }
            }
        }

        // Then
        assertContains(exception.message.orEmpty(), "PersistenceManager is not provided")
    }

    @Test
    fun givenProvidedManager_whenReadingLocalKepkoPersistenceManager_thenReturnsSameInstance() {
        // Given
        var provided: PersistenceManager? = null
        var current: PersistenceManager? = null

        // When
        runDesktopComposeUiTest {
            setContent {
                val manager = PersistenceManagerImpl(MapSettings())
                provided = manager

                CompositionLocalProvider(LocalKepkoPersistenceManager provides manager) {
                    current = LocalKepkoPersistenceManager.current
                }
            }

            waitForIdle()
        }

        // Then
        assertNotNull(provided)
        assertSame(provided, current)
    }
}
