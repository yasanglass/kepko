package glass.yasan.kepko.persistence

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runDesktopComposeUiTest
import com.russhwolf.settings.MapSettings
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl
import glass.yasan.kepko.resource.Strings
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
internal class PersistentPreferenceThemeScreenTest {

    @Test
    fun givenDefaultSettings_whenRendered_thenSystemIsSelected() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            lateinit var systemString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    systemString = Strings.themeStyleSystem
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.SCREEN).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
                .assertExists()
                .assertTextContains(systemString)
            assertNull(persistenceManager.stylePrimary)
        }
    }

    @Test
    fun givenSystemSetting_whenRendered_thenLightAndDarkOptionsAreVisible() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.stylePrimary = null
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertNull(persistenceManager.stylePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertExists()
        }
    }

    @Test
    fun givenNonSystemSetting_whenRendered_thenMatchingPrimaryStyleIsVisibleAndSubPickersHidden() {
        runDesktopComposeUiTest {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.stylePrimary = ThemeStyle.SOLARIZED_DARK
            lateinit var solarizedDarkString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    solarizedDarkString = Strings.themeStyleDarkSolarized
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertEquals(ThemeStyle.SOLARIZED_DARK, persistenceManager.stylePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
                .assertTextContains(solarizedDarkString)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertDoesNotExist()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertDoesNotExist()
        }
    }

    @Test
    fun givenSystemSetting_whenPrimaryPickerOpenedAndStyleSelected_thenSelectionReflectedOnScreen() {
        runDesktopComposeUiTest(width = 1024, height = 1200) {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.stylePrimary = null
            persistenceManager.styleLight = ThemeStyle.LIGHT
            persistenceManager.styleDark = ThemeStyle.DARK
            lateinit var systemString: String
            lateinit var blackString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    systemString = Strings.themeStyleSystem
                    blackString = Strings.themeStyleBlack
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertNull(persistenceManager.stylePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
                .assertTextContains(systemString)
            onNodeWithText(blackString).assertDoesNotExist()

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER).performClick()
            waitForIdle()
            onNodeWithText(blackString).assertExists()
            onNodeWithText(blackString).performClick()
            waitForIdle()

            assertEquals(ThemeStyle.BLACK, persistenceManager.stylePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
                .assertTextContains(blackString)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertDoesNotExist()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertDoesNotExist()
        }
    }

    @Test
    fun givenSystemSetting_whenLightAndDarkPickersOpenedAndStylesSelected_thenSelectionsReflectedOnScreen() {
        runDesktopComposeUiTest(width = 1024, height = 1200) {
            val persistenceManager = PersistenceManagerImpl(MapSettings())
            persistenceManager.stylePrimary = null
            persistenceManager.styleLight = ThemeStyle.BLACK
            persistenceManager.styleDark = ThemeStyle.SOLARIZED_LIGHT
            lateinit var blackString: String
            lateinit var darkString: String
            lateinit var solarizedLightString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistenceManager) {
                    blackString = Strings.themeStyleBlack
                    darkString = Strings.themeStyleDark
                    solarizedLightString = Strings.themeStyleLightSolarized
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertEquals(ThemeStyle.BLACK, persistenceManager.styleLight)
            assertEquals(ThemeStyle.SOLARIZED_LIGHT, persistenceManager.styleDark)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER)
                .assertTextContains(blackString)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER)
                .assertTextContains(solarizedLightString)
            onNodeWithText(darkString).assertDoesNotExist()

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).performClick()
            waitForIdle()
            onNodeWithText(darkString).assertExists()
            onNodeWithText(darkString).performClick()
            waitForIdle()

            assertEquals(ThemeStyle.DARK, persistenceManager.styleLight)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER)
                .assertTextContains(darkString)

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).performClick()
            waitForIdle()
            onNodeWithText(blackString).assertExists()
            onNodeWithText(blackString).performClick()
            waitForIdle()

            assertEquals(ThemeStyle.BLACK, persistenceManager.styleDark)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER)
                .assertTextContains(blackString)
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
