package glass.yasan.kepko.persistence

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
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
internal class PersistentPreferenceThemeScreenTest {

    @Test
    fun givenDefaultSettings_whenRendered_thenSystemIsSelected() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            lateinit var dynamicString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    dynamicString = Strings.preferencePaletteModeDynamic
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.SCREEN).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_MODE).assertExists()
            onNodeWithText(dynamicString, ignoreCase = true).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_PICKER).assertDoesNotExist()
            assertNull(persistenceManager.palettePrimary)
        }
    }

    @Test
    fun givenSystemSetting_whenRendered_thenLightAndDarkOptionsAreVisible() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.palettePrimary = null
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertNull(persistenceManager.palettePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertExists()
        }
    }

    @Test
    fun givenStaticSetting_whenRendered_thenMatchingPrimaryPaletteIsVisibleAndSubPickersHidden() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.palettePrimary = SOLARIZED_DARK
            lateinit var solarizedDarkString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    solarizedDarkString = Strings.colorPaletteDarkSolarized
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertEquals(ColorPalette.SOLARIZED_DARK, persistenceManager.palettePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_MODE).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_PICKER)
                .assertTextContains(solarizedDarkString, ignoreCase = true)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertDoesNotExist()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertDoesNotExist()
        }
    }

    @Test
    fun givenDynamicSetting_whenModeToggledAndPaletteSelected_thenStaticSelectionReflectedOnScreen() {
        runDesktopComposeUiTest(width = 1024, height = 1200) {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.palettePrimary = null
            persistenceManager.paletteLight = LIGHT
            persistenceManager.paletteDark = DARK
            lateinit var blackString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    blackString = Strings.colorPaletteBlack
                    PersistentPreferenceThemeScreen(
                        onBackClick = {},
                        isSystemInDarkTheme = true,
                    )
                }
            }

            waitForIdle()
            assertNull(persistenceManager.palettePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_PICKER).assertDoesNotExist()
            onNodeWithText(blackString, ignoreCase = true).assertDoesNotExist()

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_MODE).performClick()
            waitUntil { persistenceManager.palettePrimary != null }
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_PICKER).performClick()
            waitForIdle()
            onNodeWithText(blackString, ignoreCase = true).assertExists()
            onNodeWithText(blackString, ignoreCase = true).performClick()
            waitUntil { persistenceManager.palettePrimary == ColorPalette.BLACK }

            assertEquals(ColorPalette.BLACK, persistenceManager.palettePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_PICKER)
                .assertTextContains(blackString, ignoreCase = true)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertDoesNotExist()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertDoesNotExist()
        }
    }

    @Test
    fun givenStaticSetting_whenModeToggled_thenDynamicOptionsAreVisible() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.palettePrimary = SOLARIZED_DARK
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertEquals(ColorPalette.SOLARIZED_DARK, persistenceManager.palettePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_PICKER).assertExists()

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_MODE).performClick()
            waitForIdle()

            assertNull(persistenceManager.palettePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.PALETTE_PICKER).assertDoesNotExist()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertExists()
        }
    }

    @Test
    fun givenSystemSetting_whenLightAndDarkPickersOpenedAndPalettesSelected_thenSelectionsReflectedOnScreen() {
        runDesktopComposeUiTest(width = 1024, height = 1200) {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.palettePrimary = null
            persistenceManager.paletteLight = BLACK
            persistenceManager.paletteDark = SOLARIZED_LIGHT
            lateinit var blackString: String
            lateinit var darkString: String
            lateinit var solarizedLightString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    blackString = Strings.colorPaletteBlack
                    darkString = Strings.colorPaletteDark
                    solarizedLightString = Strings.colorPaletteLightSolarized
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertEquals(ColorPalette.BLACK, persistenceManager.paletteLight)
            assertEquals(ColorPalette.SOLARIZED_LIGHT, persistenceManager.paletteDark)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER)
                .assertTextContains(blackString, ignoreCase = true)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER)
                .assertTextContains(solarizedLightString, ignoreCase = true)
            onNodeWithText(darkString, ignoreCase = true).assertDoesNotExist()

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).performClick()
            waitForIdle()
            onNodeWithText(darkString, ignoreCase = true).assertExists()
            onNodeWithText(darkString, ignoreCase = true).performClick()
            waitForIdle()

            assertEquals(ColorPalette.DARK, persistenceManager.paletteLight)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER)
                .assertTextContains(darkString, ignoreCase = true)

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).performClick()
            waitForIdle()
            onNodeWithText(blackString, ignoreCase = true).assertExists()
            onNodeWithText(blackString, ignoreCase = true).performClick()
            waitForIdle()

            assertEquals(ColorPalette.BLACK, persistenceManager.paletteDark)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER)
                .assertTextContains(blackString, ignoreCase = true)
        }
    }

    @Test
    fun givenGrayscaleOff_whenToggled_thenPersistenceUpdated() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertEquals(false, persistenceManager.grayscale)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.GRAYSCALE).performClick()
            waitForIdle()

            assertTrue(persistenceManager.grayscale)
        }
    }
}
