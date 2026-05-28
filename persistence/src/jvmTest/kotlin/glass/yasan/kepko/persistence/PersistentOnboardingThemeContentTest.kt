package glass.yasan.kepko.persistence

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runDesktopComposeUiTest
import com.russhwolf.settings.MapSettings
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl
import glass.yasan.kepko.resource.Strings
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class, ExperimentalKepkoApi::class)
internal class PersistentOnboardingThemeContentTest {

    @Test
    fun givenDefaultSettings_whenRendered_thenShowsSimplifiedOptions() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            lateinit var defaultString: String
            lateinit var amoledString: String
            lateinit var catppuccinString: String
            lateinit var gruvboxString: String
            lateinit var solarizedString: String
            lateinit var blackString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    defaultString = Strings.preferenceOnboardingPaletteDefault
                    amoledString = Strings.preferenceOnboardingPaletteAmoled
                    catppuccinString = Strings.preferenceOnboardingPaletteCatppuccin
                    gruvboxString = Strings.preferenceOnboardingPaletteGruvbox
                    solarizedString = Strings.preferenceOnboardingPaletteSolarized
                    blackString = Strings.colorPaletteBlack
                    PersistentOnboardingThemeContent()
                }
            }

            waitForIdle()

            onNodeWithText(defaultString, ignoreCase = true).assertExists()
            onNodeWithText(amoledString, ignoreCase = true).assertExists()
            onNodeWithText(catppuccinString, ignoreCase = true).assertExists()
            onNodeWithText(gruvboxString, ignoreCase = true).assertExists()
            onNodeWithText(solarizedString, ignoreCase = true).assertExists()
            onNodeWithText(blackString, ignoreCase = true).assertDoesNotExist()
            val optionTopPositions = listOf(
                defaultString,
                amoledString,
                solarizedString,
                catppuccinString,
                gruvboxString,
            ).map { title ->
                onNodeWithText(title, ignoreCase = true).fetchSemanticsNode().boundsInRoot.top
            }
            assertTrue(optionTopPositions.zipWithNext().all { (before, after) -> before < after })
            assertNull(persistenceManager.palettePrimary)
            assertEquals(ColorPalette.LIGHT, persistenceManager.paletteLight)
            assertEquals(ColorPalette.DARK, persistenceManager.paletteDark)
        }
    }

    @Test
    fun givenUnsupportedSettings_whenRendered_thenShowsOneSelectionWithoutChangingSettings() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.palettePrimary = null
            persistenceManager.paletteLight = ColorPalette.LIGHT
            persistenceManager.paletteDark = ColorPalette.CATPPUCCIN_MOCHA
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    PersistentOnboardingThemeContent()
                }
            }

            waitForIdle()

            onAllNodes(isSelected()).assertCountEquals(1)
            assertNull(persistenceManager.palettePrimary)
            assertEquals(ColorPalette.LIGHT, persistenceManager.paletteLight)
            assertEquals(ColorPalette.CATPPUCCIN_MOCHA, persistenceManager.paletteDark)
        }
    }

    @Test
    fun givenDefaultSettings_whenAmoledSelected_thenUsesBlackStaticPalette() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            lateinit var amoledString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    amoledString = Strings.preferenceOnboardingPaletteAmoled
                    PersistentOnboardingThemeContent()
                }
            }

            waitForIdle()
            onNodeWithText(amoledString, ignoreCase = true).performClick()
            waitForIdle()

            assertEquals(ColorPalette.BLACK, persistenceManager.palettePrimary)
            assertEquals(ColorPalette.LIGHT, persistenceManager.paletteLight)
            assertEquals(ColorPalette.DARK, persistenceManager.paletteDark)
        }
    }

    @Test
    fun givenDefaultSettings_whenCatppuccinSelected_thenUsesCatppuccinDynamicPalettes() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            lateinit var catppuccinString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    catppuccinString = Strings.preferenceOnboardingPaletteCatppuccin
                    PersistentOnboardingThemeContent()
                }
            }

            waitForIdle()
            onNodeWithText(catppuccinString, ignoreCase = true).performClick()
            waitForIdle()

            assertNull(persistenceManager.palettePrimary)
            assertEquals(ColorPalette.CATPPUCCIN_LATTE, persistenceManager.paletteLight)
            assertEquals(ColorPalette.CATPPUCCIN_MOCHA, persistenceManager.paletteDark)
        }
    }

    @Test
    fun givenDefaultSettings_whenGruvboxSelected_thenUsesGruvboxDynamicPalettes() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            lateinit var gruvboxString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    gruvboxString = Strings.preferenceOnboardingPaletteGruvbox
                    PersistentOnboardingThemeContent()
                }
            }

            waitForIdle()
            onNodeWithText(gruvboxString, ignoreCase = true).performClick()
            waitForIdle()

            assertNull(persistenceManager.palettePrimary)
            assertEquals(ColorPalette.GRUVBOX_LIGHT, persistenceManager.paletteLight)
            assertEquals(ColorPalette.GRUVBOX_DARK, persistenceManager.paletteDark)
        }
    }

    @Test
    fun givenDefaultSettings_whenSolarizedSelected_thenUsesSolarizedDynamicPalettes() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            lateinit var solarizedString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    solarizedString = Strings.preferenceOnboardingPaletteSolarized
                    PersistentOnboardingThemeContent()
                }
            }

            waitForIdle()
            onNodeWithText(solarizedString, ignoreCase = true).performClick()
            waitForIdle()

            assertNull(persistenceManager.palettePrimary)
            assertEquals(ColorPalette.SOLARIZED_LIGHT, persistenceManager.paletteLight)
            assertEquals(ColorPalette.SOLARIZED_DARK, persistenceManager.paletteDark)
        }
    }

    @Test
    fun givenCustomSettings_whenDefaultSelected_thenRestoresDefaultDynamicPalettes() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.palettePrimary = ColorPalette.BLACK
            persistenceManager.paletteLight = ColorPalette.CATPPUCCIN_LATTE
            persistenceManager.paletteDark = ColorPalette.CATPPUCCIN_MOCHA
            lateinit var defaultString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    defaultString = Strings.preferenceOnboardingPaletteDefault
                    PersistentOnboardingThemeContent()
                }
            }

            waitForIdle()
            onNodeWithText(defaultString, ignoreCase = true).performClick()
            waitForIdle()

            assertNull(persistenceManager.palettePrimary)
            assertEquals(ColorPalette.LIGHT, persistenceManager.paletteLight)
            assertEquals(ColorPalette.DARK, persistenceManager.paletteDark)
        }
    }
}
