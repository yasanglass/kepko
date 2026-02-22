package glass.yasan.kepko.persistence

import com.russhwolf.settings.MapSettings
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.kepko.persistence.internal.PersistenceManagerImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

@Suppress("VisibleForTests")
internal class PersistenceManagerTest {

    private fun createManager(
        vararg keyValues: Pair<String, Any>,
    ): PersistenceManager {
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
        assertEquals(ThemeStyle.defaultLight, manager.styleLight)
        assertEquals(ThemeStyle.defaultDark, manager.styleDark)
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
        assertEquals(
            ThemeStyle.SOLARIZED_DARK.id,
            settings.getString(PersistenceManagerImpl.KEY_STYLE, "")
        )
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
        assertEquals(
            PersistenceManager.STYLE_ID_SYSTEM,
            settings.getString(PersistenceManagerImpl.KEY_STYLE, "")
        )
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
        assertEquals(
            ThemeStyle.SOLARIZED_LIGHT.id,
            settings.getString(PersistenceManagerImpl.KEY_LIGHT_STYLE, "")
        )
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
        assertEquals(
            ThemeStyle.BLACK.id,
            settings.getString(PersistenceManagerImpl.KEY_DARK_STYLE, "")
        )
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
        assertEquals(
            true,
            settings.getBoolean(PersistenceManagerImpl.KEY_GRAYSCALE, false)
        )
    }

    @Test
    fun givenPrePopulatedSettings_whenCreated_thenRestoresValues() {
        // Given
        val manager = createManager(
            PersistenceManagerImpl.KEY_STYLE to ThemeStyle.BLACK.id,
            PersistenceManagerImpl.KEY_LIGHT_STYLE to ThemeStyle.SOLARIZED_LIGHT.id,
            PersistenceManagerImpl.KEY_DARK_STYLE to ThemeStyle.SOLARIZED_DARK.id,
            PersistenceManagerImpl.KEY_GRAYSCALE to true,
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
            PersistenceManagerImpl.KEY_STYLE to "nonexistent",
            PersistenceManagerImpl.KEY_LIGHT_STYLE to "invalid",
            PersistenceManagerImpl.KEY_DARK_STYLE to "unknown",
        )

        // Then
        assertNull(manager.stylePrimary)
        assertEquals(ThemeStyle.defaultLight, manager.styleLight)
        assertEquals(ThemeStyle.defaultDark, manager.styleDark)
    }

    @Test
    fun givenSystemPrimaryStyleId_whenCreated_thenRestoresNullPrimaryStyle() {
        // Given
        val manager =
            createManager(PersistenceManagerImpl.KEY_STYLE to PersistenceManager.STYLE_ID_SYSTEM)

        // Then
        assertNull(manager.stylePrimary)
    }

    @Test
    fun givenGrayscaleTrue_whenSetToFalse_thenPersistsAndReadsBack() {
        // Given
        val settings = MapSettings(mutableMapOf(PersistenceManagerImpl.KEY_GRAYSCALE to true))
        val manager = PersistenceManagerImpl(settings)

        // When
        manager.grayscale = false

        // Then
        assertFalse(manager.grayscale)
        assertEquals(
            false,
            settings.getBoolean(PersistenceManagerImpl.KEY_GRAYSCALE, true)
        )
    }

    @Test
    fun givenPrimaryStyleSet_whenResolvedStyle_thenAlwaysUsesPrimaryStyle() {
        // Given
        val manager = createManager() as PersistenceManagerImpl
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
        val manager = createManager() as PersistenceManagerImpl
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
