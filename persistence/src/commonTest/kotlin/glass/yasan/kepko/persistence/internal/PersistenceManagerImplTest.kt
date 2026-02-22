package glass.yasan.kepko.persistence.internal

import com.russhwolf.settings.MapSettings
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.persistence.PersistenceManager.Companion.STYLE_ID_SYSTEM
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl.Companion.KEY_DARK_STYLE
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl.Companion.KEY_GRAYSCALE
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl.Companion.KEY_LIGHT_STYLE
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl.Companion.KEY_STYLE
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

@Suppress("VisibleForTests")
internal class PersistenceManagerImplTest {

    private fun createManager(
        vararg keyValues: Pair<String, Any>,
    ): PersistenceManagerImpl {
        val map = mutableMapOf<String, Any>()
        keyValues.forEach { map[it.first] = it.second }

        return PersistenceManagerImpl(MapSettings(map))
    }

    @Test
    fun givenEmptySettings_whenCreated_thenReturnsDefaults() {
        // Given
        val manager = createManager()

        // Then
        assertNull(manager.stylePrimary)
        assertEquals(ThemeStyle.LIGHT, manager.styleLight)
        assertEquals(ThemeStyle.DARK, manager.styleDark)
        assertFalse(manager.grayscale)
    }

    @Test
    fun givenEmptySettings_whenStylePrimarySet_thenPersistsAndReadsBack() {
        // Given
        val settings = MapSettings()
        val manager = PersistenceManagerImpl(settings)

        // When
        manager.stylePrimary = ThemeStyle.SOLARIZED_DARK

        // Then
        assertEquals(ThemeStyle.SOLARIZED_DARK, manager.stylePrimary)
        assertEquals("solarized-dark", settings.getString(KEY_STYLE, ""))
    }

    @Test
    fun givenStylePrimarySet_whenSetToNull_thenPersistsSystemId() {
        // Given
        val settings = MapSettings()
        val manager = PersistenceManagerImpl(settings)
        manager.stylePrimary = ThemeStyle.BLACK

        // When
        manager.stylePrimary = null

        // Then
        assertNull(manager.stylePrimary)
        assertEquals(STYLE_ID_SYSTEM, settings.getString(KEY_STYLE, ""))
    }

    @Test
    fun givenEmptySettings_whenStyleLightSet_thenPersistsAndReadsBack() {
        // Given
        val settings = MapSettings()
        val manager = PersistenceManagerImpl(settings)

        // When
        manager.styleLight = ThemeStyle.SOLARIZED_LIGHT

        // Then
        assertEquals(ThemeStyle.SOLARIZED_LIGHT, manager.styleLight)
        assertEquals("solarized-light", settings.getString(KEY_LIGHT_STYLE, ""))
    }

    @Test
    fun givenEmptySettings_whenStyleDarkSet_thenPersistsAndReadsBack() {
        // Given
        val settings = MapSettings()
        val manager = PersistenceManagerImpl(settings)

        // When
        manager.styleDark = ThemeStyle.BLACK

        // Then
        assertEquals(ThemeStyle.BLACK, manager.styleDark)
        assertEquals("black", settings.getString(KEY_DARK_STYLE, ""))
    }

    @Test
    fun givenEmptySettings_whenGrayscaleSet_thenPersistsAndReadsBack() {
        // Given
        val settings = MapSettings()
        val manager = PersistenceManagerImpl(settings)

        // When
        manager.grayscale = true

        // Then
        assertEquals(true, manager.grayscale)
        assertEquals(true, settings.getBoolean(KEY_GRAYSCALE, false))
    }

    @Test
    fun givenPrePopulatedSettings_whenCreated_thenRestoresValues() {
        // Given
        val manager = createManager(
            KEY_STYLE to "black",
            KEY_LIGHT_STYLE to "solarized-light",
            KEY_DARK_STYLE to "solarized-dark",
            KEY_GRAYSCALE to true,
        )

        // Then
        assertEquals(ThemeStyle.BLACK, manager.stylePrimary)
        assertEquals(ThemeStyle.SOLARIZED_LIGHT, manager.styleLight)
        assertEquals(ThemeStyle.SOLARIZED_DARK, manager.styleDark)
        assertEquals(true, manager.grayscale)
    }

    @Test
    fun givenInvalidStyleIds_whenCreated_thenFallsBackToDefaults() {
        // Given
        val manager = createManager(
            KEY_STYLE to "nonexistent",
            KEY_LIGHT_STYLE to "invalid",
            KEY_DARK_STYLE to "unknown",
        )

        // Then
        assertNull(manager.stylePrimary)
        assertEquals(ThemeStyle.LIGHT, manager.styleLight)
        assertEquals(ThemeStyle.DARK, manager.styleDark)
    }

    @Test
    fun givenSystemPrimaryStyleId_whenCreated_thenRestoresNullPrimaryStyle() {
        // Given
        val manager = createManager(KEY_STYLE to STYLE_ID_SYSTEM)

        // Then
        assertNull(manager.stylePrimary)
    }

    @Test
    fun givenGrayscaleTrue_whenSetToFalse_thenPersistsAndReadsBack() {
        // Given
        val settings = MapSettings(mutableMapOf(KEY_GRAYSCALE to true))
        val manager = PersistenceManagerImpl(settings)

        // When
        manager.grayscale = false

        // Then
        assertFalse(manager.grayscale)
        assertEquals(false, settings.getBoolean(KEY_GRAYSCALE, true))
    }

    @Test
    fun givenPrimaryStyleSet_whenResolvedStyle_thenAlwaysUsesPrimaryStyle() {
        // Given
        val manager = createManager()
        manager.stylePrimary = ThemeStyle.BLACK
        manager.styleLight = ThemeStyle.SOLARIZED_LIGHT
        manager.styleDark = ThemeStyle.SOLARIZED_DARK

        // When
        val lightResolved = manager.resolvedStyle(isDark = false)
        val darkResolved = manager.resolvedStyle(isDark = true)

        // Then
        assertEquals(ThemeStyle.BLACK, lightResolved)
        assertEquals(ThemeStyle.BLACK, darkResolved)
    }

    @Test
    fun givenNoPrimaryStyle_whenResolvedStyle_thenUsesLightOrDarkStyleByDarkFlag() {
        // Given
        val manager = createManager()
        manager.stylePrimary = null
        manager.styleLight = ThemeStyle.SOLARIZED_LIGHT
        manager.styleDark = ThemeStyle.SOLARIZED_DARK

        // When
        val lightResolved = manager.resolvedStyle(isDark = false)
        val darkResolved = manager.resolvedStyle(isDark = true)

        // Then
        assertEquals(ThemeStyle.SOLARIZED_LIGHT, lightResolved)
        assertEquals(ThemeStyle.SOLARIZED_DARK, darkResolved)
    }
}
