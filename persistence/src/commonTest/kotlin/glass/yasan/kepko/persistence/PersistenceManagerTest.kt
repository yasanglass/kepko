package glass.yasan.kepko.persistence

import com.russhwolf.settings.MapSettings
import glass.yasan.kepko.foundation.theme.ColorPalette
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
        assertNull(manager.palettePrimary)
        assertEquals(ColorPalette.defaultLight, manager.paletteLight)
        assertEquals(ColorPalette.defaultDark, manager.paletteDark)
        assertFalse(manager.grayscale)
    }

    @Test
    fun givenEmptySettings_whenPalettePrimarySet_thenPersistsAndReadsBack() {
        // Given
        val settings = MapSettings()
        val manager = PersistenceManagerImpl(settings)

        // When
        manager.palettePrimary = SOLARIZED_DARK

        // Then
        assertEquals(ColorPalette.SOLARIZED_DARK, manager.palettePrimary)
        assertEquals(
            ColorPalette.SOLARIZED_DARK.id,
            settings.getString(PersistenceManagerImpl.KEY_STYLE, "")
        )
    }

    @Test
    fun givenPalettePrimarySet_whenSetToNull_thenPersistsSystemId() {
        // Given
        val settings = MapSettings()
        val manager = PersistenceManagerImpl(settings)
        manager.palettePrimary = BLACK

        // When
        manager.palettePrimary = null

        // Then
        assertNull(manager.palettePrimary)
        assertEquals(
            PersistenceManager.PALETTE_ID_SYSTEM,
            settings.getString(PersistenceManagerImpl.KEY_STYLE, "")
        )
    }

    @Test
    fun givenEmptySettings_whenPaletteLightSet_thenPersistsAndReadsBack() {
        // Given
        val settings = MapSettings()
        val manager = PersistenceManagerImpl(settings)

        // When
        manager.paletteLight = SOLARIZED_LIGHT

        // Then
        assertEquals(ColorPalette.SOLARIZED_LIGHT, manager.paletteLight)
        assertEquals(
            ColorPalette.SOLARIZED_LIGHT.id,
            settings.getString(PersistenceManagerImpl.KEY_LIGHT_STYLE, "")
        )
    }

    @Test
    fun givenEmptySettings_whenPaletteDarkSet_thenPersistsAndReadsBack() {
        // Given
        val settings = MapSettings()
        val manager = PersistenceManagerImpl(settings)

        // When
        manager.paletteDark = BLACK

        // Then
        assertEquals(ColorPalette.BLACK, manager.paletteDark)
        assertEquals(
            ColorPalette.BLACK.id,
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
            PersistenceManagerImpl.KEY_STYLE to ColorPalette.BLACK.id,
            PersistenceManagerImpl.KEY_LIGHT_STYLE to ColorPalette.SOLARIZED_LIGHT.id,
            PersistenceManagerImpl.KEY_DARK_STYLE to ColorPalette.SOLARIZED_DARK.id,
            PersistenceManagerImpl.KEY_GRAYSCALE to true,
        )

        // Then
        assertEquals(ColorPalette.BLACK, manager.palettePrimary)
        assertEquals(ColorPalette.SOLARIZED_LIGHT, manager.paletteLight)
        assertEquals(ColorPalette.SOLARIZED_DARK, manager.paletteDark)
        assertEquals(true, manager.grayscale)
    }

    @Test
    fun givenInvalidPaletteIds_whenCreated_thenFallsBackToDefaults() {
        // Given
        val manager = createManager(
            PersistenceManagerImpl.KEY_STYLE to "nonexistent",
            PersistenceManagerImpl.KEY_LIGHT_STYLE to "invalid",
            PersistenceManagerImpl.KEY_DARK_STYLE to "unknown",
        )

        // Then
        assertNull(manager.palettePrimary)
        assertEquals(ColorPalette.defaultLight, manager.paletteLight)
        assertEquals(ColorPalette.defaultDark, manager.paletteDark)
    }

    @Test
    fun givenSystemPrimaryPaletteId_whenCreated_thenRestoresNullPrimaryPalette() {
        // Given
        val manager =
            createManager(PersistenceManagerImpl.KEY_STYLE to PersistenceManager.PALETTE_ID_SYSTEM)

        // Then
        assertNull(manager.palettePrimary)
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
    fun givenPrimaryPaletteSet_whenResolvedPalette_thenAlwaysUsesPrimaryPalette() {
        // Given
        val manager = createManager() as PersistenceManagerImpl
        manager.palettePrimary = BLACK
        manager.paletteLight = SOLARIZED_LIGHT
        manager.paletteDark = SOLARIZED_DARK

        // When
        val lightResolved = manager.resolvedPalette(isSystemInDarkTheme = false)
        val darkResolved = manager.resolvedPalette(isSystemInDarkTheme = true)

        // Then
        assertEquals(ColorPalette.BLACK, lightResolved)
        assertEquals(ColorPalette.BLACK, darkResolved)
    }

    @Test
    fun givenNoPrimaryPalette_whenResolvedPalette_thenUsesLightOrDarkPaletteByDarkFlag() {
        // Given
        val manager = createManager() as PersistenceManagerImpl
        manager.palettePrimary = null
        manager.paletteLight = SOLARIZED_LIGHT
        manager.paletteDark = SOLARIZED_DARK

        // When
        val lightResolved = manager.resolvedPalette(isSystemInDarkTheme = false)
        val darkResolved = manager.resolvedPalette(isSystemInDarkTheme = true)

        // Then
        assertEquals(ColorPalette.SOLARIZED_LIGHT, lightResolved)
        assertEquals(ColorPalette.SOLARIZED_DARK, darkResolved)
    }
}
