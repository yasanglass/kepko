package glass.yasan.kepko.persistence

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runDesktopComposeUiTest
import com.russhwolf.settings.MapSettings
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl.Companion.KEY_GRAYSCALE
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl.Companion.KEY_STYLE
import kotlin.test.Test
import androidx.compose.ui.graphics.Color
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertSame

@Suppress("VisibleForTests")
@OptIn(ExperimentalTestApi::class, ExperimentalKepkoApi::class)
internal class PersistentKepkoThemeTest {

    @Test
    fun givenDefaultSettings_whenRendered_thenUsesDefaultLightStyle() {
        // When
        var style: ThemeStyle? = null
        runDesktopComposeUiTest {
            setContent {
                PersistentKepkoTheme(settings = MapSettings()) {
                    style = KepkoTheme.colors.style
                }
            }

            waitForIdle()
        }

        // Then
        assertEquals(ThemeStyle.defaultLight, style)
    }

    @Test
    fun givenPrimaryStyleInSettings_whenRendered_thenAppliesThatStyle() {
        // When
        var style: ThemeStyle? = null
        runDesktopComposeUiTest {
            setContent {
                PersistentKepkoTheme(
                    settings = MapSettings(mutableMapOf(KEY_STYLE to ThemeStyle.BLACK.id)),
                ) {
                    style = KepkoTheme.colors.style
                }
            }

            waitForIdle()
        }

        // Then
        assertEquals(ThemeStyle.BLACK, style)
    }

    @Test
    fun givenGrayscaleEnabled_whenRendered_thenColorsAreGrayscale() {
        // When
        var grayscaleSuccess: Color? = null
        var normalSuccess: Color? = null
        runDesktopComposeUiTest {
            setContent {
                PersistentKepkoTheme(
                    settings = MapSettings(
                        mutableMapOf(
                            KEY_STYLE to ThemeStyle.LIGHT.id,
                            KEY_GRAYSCALE to true,
                        ),
                    ),
                ) {
                    grayscaleSuccess = KepkoTheme.colors.success
                }
            }

            waitForIdle()
        }
        runDesktopComposeUiTest {
            setContent {
                PersistentKepkoTheme(
                    settings = MapSettings(
                        mutableMapOf(KEY_STYLE to ThemeStyle.LIGHT.id),
                    ),
                ) {
                    normalSuccess = KepkoTheme.colors.success
                }
            }

            waitForIdle()
        }

        // Then
        assertNotEquals(normalSuccess, grayscaleSuccess)
    }

    @Test
    fun givenPersistenceManagerOverload_whenRendered_thenUsesProvidedManager() {
        // Given
        var manager: PersistenceManager? = null
        val settings = MapSettings(mutableMapOf(KEY_STYLE to ThemeStyle.SOLARIZED_DARK.id))
        val provided = PersistenceManagerImpl(settings)

        // When
        runDesktopComposeUiTest {
            setContent {
                PersistentKepkoTheme(persistenceManager = provided) {
                    manager = LocalPersistenceManager.current
                }
            }

            waitForIdle()
        }

        // Then
        assertSame(provided, manager)
    }

    @Test
    fun givenPersistenceManagerOverload_whenRendered_thenAppliesManagerStyle() {
        // Given
        var style: ThemeStyle? = null
        val settings = MapSettings(mutableMapOf(KEY_STYLE to ThemeStyle.SOLARIZED_DARK.id))
        val provided = PersistenceManagerImpl(settings)

        // When
        runDesktopComposeUiTest {
            setContent {
                PersistentKepkoTheme(persistenceManager = provided) {
                    style = KepkoTheme.colors.style
                }
            }

            waitForIdle()
        }

        // Then
        assertEquals(ThemeStyle.SOLARIZED_DARK, style)
    }
}
