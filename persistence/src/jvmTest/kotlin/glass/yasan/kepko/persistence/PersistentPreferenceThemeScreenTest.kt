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
            val persistence = PersistenceManagerImpl(MapSettings())
            lateinit var systemString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistence) {
                    systemString = Strings.themeStyleSystem
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.SCREEN).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
                .assertExists()
                .assertTextContains(systemString)
            assertNull(persistence.stylePrimary)
        }
    }

    @Test
    fun givenSystemSetting_whenRendered_thenLightAndDarkOptionsAreVisible() {
        runDesktopComposeUiTest {
            val persistence = PersistenceManagerImpl(MapSettings())
            persistence.stylePrimary = null
            setContent {
                PersistentKepkoTheme(persistenceManager = persistence) {
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertNull(persistence.stylePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertExists()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertExists()
        }
    }

    @Test
    fun givenNonSystemSetting_whenRendered_thenMatchingPrimaryStyleIsVisibleAndSubPickersHidden() {
        runDesktopComposeUiTest {
            val persistence = PersistenceManagerImpl(MapSettings())
            persistence.stylePrimary = ThemeStyle.SOLARIZED_DARK
            lateinit var solarizedDarkString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistence) {
                    solarizedDarkString = Strings.themeStyleDarkSolarized
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertEquals(ThemeStyle.SOLARIZED_DARK, persistence.stylePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
                .assertTextContains(solarizedDarkString)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertDoesNotExist()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertDoesNotExist()
        }
    }

    @Test
    fun givenSystemSetting_whenPrimaryPickerOpenedAndStyleSelected_thenSelectionReflectedOnScreen() {
        runDesktopComposeUiTest {
            val persistence = PersistenceManagerImpl(MapSettings())
            persistence.stylePrimary = null
            persistence.styleLight = ThemeStyle.LIGHT
            persistence.styleDark = ThemeStyle.DARK
            lateinit var systemString: String
            lateinit var blackString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistence) {
                    systemString = Strings.themeStyleSystem
                    blackString = Strings.themeStyleBlack
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertNull(persistence.stylePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
                .assertTextContains(systemString)
            onNodeWithText(blackString).assertDoesNotExist()

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER).performClick()
            waitForIdle()
            onNodeWithText(blackString).assertExists()
            onNodeWithText(blackString).performClick()
            waitForIdle()

            assertEquals(ThemeStyle.BLACK, persistence.stylePrimary)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.STYLE_PICKER)
                .assertTextContains(blackString)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER).assertDoesNotExist()
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).assertDoesNotExist()
        }
    }

    @Test
    fun givenSystemSetting_whenLightAndDarkPickersOpenedAndStylesSelected_thenSelectionsReflectedOnScreen() {
        runDesktopComposeUiTest {
            val persistence = PersistenceManagerImpl(MapSettings())
            persistence.stylePrimary = null
            persistence.styleLight = ThemeStyle.BLACK
            persistence.styleDark = ThemeStyle.SOLARIZED_LIGHT
            lateinit var blackString: String
            lateinit var darkString: String
            lateinit var solarizedLightString: String
            setContent {
                PersistentKepkoTheme(persistenceManager = persistence) {
                    blackString = Strings.themeStyleBlack
                    darkString = Strings.themeStyleDark
                    solarizedLightString = Strings.themeStyleLightSolarized
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertEquals(ThemeStyle.BLACK, persistence.styleLight)
            assertEquals(ThemeStyle.SOLARIZED_LIGHT, persistence.styleDark)
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

            assertEquals(ThemeStyle.DARK, persistence.styleLight)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER)
                .assertTextContains(darkString)

            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER).performClick()
            waitForIdle()
            onNodeWithText(blackString).assertExists()
            onNodeWithText(blackString).performClick()
            waitForIdle()

            assertEquals(ThemeStyle.BLACK, persistence.styleDark)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER)
                .assertTextContains(blackString)
        }
    }

    @Test
    fun givenGrayscaleOff_whenToggled_thenPersistenceUpdated() {
        runDesktopComposeUiTest {
            val persistence = PersistenceManagerImpl(MapSettings())
            setContent {
                PersistentKepkoTheme(persistenceManager = persistence) {
                    PersistentPreferenceThemeScreen(onBackClick = {})
                }
            }

            waitForIdle()
            assertEquals(false, persistence.grayscale)
            onNodeWithTag(PersistentPreferenceThemeScreenSemantics.GRAYSCALE).performClick()
            waitForIdle()

            assertTrue(persistence.grayscale)
        }
    }
}
