package glass.yasan.kepko.persistence

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.ui.unit.IntSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.Badge
import glass.yasan.kepko.component.IconButton
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.PreferenceRadioGroupPicker
import glass.yasan.kepko.component.PreferenceSlider
import glass.yasan.kepko.component.PreferenceSwitch
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.component.SegmentedPicker
import glass.yasan.kepko.component.SegmentedPickerItem
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.ColorPalette
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultDark
import glass.yasan.kepko.foundation.theme.ColorPalette.Companion.defaultLight
import glass.yasan.kepko.foundation.theme.isSystemInDarkTheme
import glass.yasan.kepko.resource.Icons
import glass.yasan.kepko.resource.Strings

/**
 * A theme preferences screen which allows easy integration when used with [PersistentKepkoTheme].
 *
 * This preference screen allows the user to customize the appearance of the theme using [PersistenceManager].
 */
@ExperimentalKepkoApi
@Composable
public fun PersistentPreferenceThemeScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    persistence: PersistenceManager = LocalKepkoPersistenceManager.current,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
) {
    AnimatedPersistentKepkoTheme(
        persistence = persistence,
        isSystemInDarkTheme = isSystemInDarkTheme,
    ) {
        Scaffold(
            title = Strings.preferenceThemeScreenTitle,
            onBackClick = onBackClick,
            trailingContent = { PersistentPreferenceThemeResetButton(persistence) },
            modifier = modifier
                .testTag(PersistentPreferenceThemeScreenSemantics.SCREEN)
        ) { contentPadding ->
            PersistentPreferenceThemeContent(
                persistence = persistence,
                isSystemInDarkTheme = isSystemInDarkTheme,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(contentPadding),
            )
        }
    }
}

@ExperimentalKepkoApi
@Composable
public fun PersistentPreferenceThemeContent(
    persistence: PersistenceManager = LocalKepkoPersistenceManager.current,
    isSystemInDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    AnimatedPersistentKepkoTheme(
        persistence = persistence,
        isSystemInDarkTheme = isSystemInDarkTheme,
    ) {
        val sizeSpring = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = IntSize.VisibilityThreshold,
        )
        val fadeSpring = spring<Float>(stiffness = Spring.StiffnessMedium)
        val enterTransition = expandVertically(animationSpec = sizeSpring) +
            fadeIn(animationSpec = fadeSpring)
        val exitTransition = shrinkVertically(animationSpec = sizeSpring) +
            fadeOut(animationSpec = fadeSpring)

        Column(
            modifier = modifier,
        ) {
            PersistentPreferenceThemeMode(persistence)
            AnimatedVisibility(
                visible = persistence.palettePrimary == null,
                enter = enterTransition,
                exit = exitTransition,
            ) {
                PersistentPreferenceThemeSystem(
                    persistence = persistence,
                    isSystemInDarkTheme = isSystemInDarkTheme,
                )
            }
            AnimatedVisibility(
                visible = persistence.palettePrimary != null,
                enter = enterTransition,
                exit = exitTransition,
            ) {
                PersistentPreferenceThemeStatic(persistence)
            }
            Spacer(Modifier.height(8.dp))
            PersistentPreferenceThemeGrayscale(persistence)
            Spacer(Modifier.height(8.dp))
            PersistentPreferenceThemeOutline(persistence)
            Spacer(Modifier.height(8.dp))
            PersistentPreferenceThemeRoundness(persistence)
        }
    }
}

@Composable
private fun PersistentPreferenceThemeResetButton(persistence: PersistenceManager) {
    Crossfade(targetState = !persistence.isDefault) { showResetButton ->
        if (showResetButton) {
            IconButton(
                painter = Icons.restartAlt,
                contentDescription = Strings.reset,
                onClick = persistence::reset,
            )
        }
    }
}

@Composable
private fun PersistentPreferenceThemeMode(
    persistence: PersistenceManager,
) {
    val primaryPalette = persistence.palettePrimary
    var lastPrimaryPalette by remember { mutableStateOf(primaryPalette) }
    if (primaryPalette != null) lastPrimaryPalette = primaryPalette
    val fallbackPrimaryPalette = persistence.activePalette()

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
    ) {
        SegmentedPicker(
            items = listOf(
                SegmentedPickerItem(
                    value = PersistentPreferenceThemeScreenSemantics.PALETTE_MODE_DYNAMIC,
                    text = Strings.preferencePaletteModeDynamic,
                ),
                SegmentedPickerItem(
                    value = PersistentPreferenceThemeScreenSemantics.PALETTE_MODE_STATIC,
                    text = Strings.preferencePaletteModeStatic,
                ),
            ),
            selected = if (primaryPalette == null) {
                PersistentPreferenceThemeScreenSemantics.PALETTE_MODE_DYNAMIC
            } else {
                PersistentPreferenceThemeScreenSemantics.PALETTE_MODE_STATIC
            },
            onSelect = { selectedId ->
                val useDynamicPalette = selectedId == PersistentPreferenceThemeScreenSemantics.PALETTE_MODE_DYNAMIC
                persistence.palettePrimary = if (useDynamicPalette) {
                    null
                } else {
                    lastPrimaryPalette ?: fallbackPrimaryPalette
                }
            },
            modifier = Modifier.testTag(PersistentPreferenceThemeScreenSemantics.PALETTE_MODE),
        )
    }
}

@Composable
private fun PersistentPreferenceThemeSystem(
    persistence: PersistenceManager,
    isSystemInDarkTheme: Boolean,
) {
    Column {
        Spacer(Modifier.height(8.dp))
        PersistentPreferenceThemePaletteRow(
            persistence = persistence,
            isSystemInDarkTheme = isSystemInDarkTheme,
            isLight = true,
        )
        Spacer(Modifier.height(8.dp))
        PersistentPreferenceThemePaletteRow(
            persistence = persistence,
            isSystemInDarkTheme = isSystemInDarkTheme,
            isLight = false,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersistentPreferenceThemeStatic(
    persistence: PersistenceManager,
) {
    Column {
        Spacer(Modifier.height(8.dp))
        PreferenceRadioGroupPicker(
            title = Strings.preferenceStaticPaletteTitle,
            selectedId = persistence.palettePrimary?.id,
            items = ColorPalette.entries.map { palette ->
                palette.asPreferenceRadioGroupItem(segment = palette.category.ordinal)
            },
            onSelectId = { id ->
                ColorPalette.fromIdOrNull(id)?.let { persistence.palettePrimary = it }
            },
            modifier = Modifier
                .testTag(PersistentPreferenceThemeScreenSemantics.PALETTE_PICKER)
        )
    }
}

@Composable
private fun PersistentPreferenceThemePaletteRow(
    persistence: PersistenceManager,
    isSystemInDarkTheme: Boolean,
    isLight: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isLight) {
            PersistentPreferenceThemeLight(
                persistence = persistence,
                isSystemInDarkTheme = isSystemInDarkTheme,
                modifier = Modifier.weight(1f),
            )
        } else {
            PersistentPreferenceThemeDark(
                persistence = persistence,
                isSystemInDarkTheme = isSystemInDarkTheme,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersistentPreferenceThemeLight(
    persistence: PersistenceManager,
    isSystemInDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    PreferenceRadioGroupPicker(
        title = Strings.preferenceLightPaletteTitle,
        selectedId = persistence.paletteLight.id,
        items = ColorPalette.entries.map { palette ->
            palette.asPreferenceRadioGroupItem(
                segment = if (palette.isDark) 1 else 0,
                isDefault = palette == defaultLight,
            )
        },
        onSelectId = { id -> ColorPalette.fromIdOrNull(id)?.let { persistence.paletteLight = it } },
        description = Strings.preferenceLightPaletteDescription,
        badge = Badge.active.takeIf { !isSystemInDarkTheme },
        modifier = modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.LIGHT_PICKER)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersistentPreferenceThemeDark(
    persistence: PersistenceManager,
    isSystemInDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    PreferenceRadioGroupPicker(
        title = Strings.preferenceDarkPaletteTitle,
        selectedId = persistence.paletteDark.id,
        items = ColorPalette.entries.map { palette ->
            palette.asPreferenceRadioGroupItem(
                segment = if (palette.isDark) 0 else 1,
                isDefault = palette == defaultDark,
            )
        },
        onSelectId = { id -> ColorPalette.fromIdOrNull(id)?.let { persistence.paletteDark = it } },
        description = Strings.preferenceDarkPaletteDescription,
        badge = Badge.active.takeIf { isSystemInDarkTheme },
        modifier = modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.DARK_PICKER)
    )
}

@Composable
private fun PersistentPreferenceThemeGrayscale(
    persistence: PersistenceManager,
) {
    PreferenceSwitch(
        title = Strings.preferenceGrayscaleTitle,
        description = Strings.preferenceGrayscaleDescription,
        checked = persistence.grayscale,
        onCheckedChange = { persistence.grayscale = it },
        leadingIcon = Icons.filterBw,
        modifier = Modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.GRAYSCALE)
    )
}

@Composable
private fun PersistentPreferenceThemeOutline(
    persistence: PersistenceManager,
) {
    var value by remember(persistence.outline) { mutableStateOf(persistence.outline.value) }
    PreferenceSlider(
        title = Strings.preferenceOutlineTitle,
        value = value,
        onValueChange = { value = it },
        onValueChangeFinished = { persistence.outline = value.dp },
        valueLabelSuffix = "x",
        valueRange = 1f..5f,
        steps = 3,
        modifier = Modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.OUTLINE)
    )
}

@Composable
private fun PersistentPreferenceThemeRoundness(
    persistence: PersistenceManager,
) {
    var value by remember(persistence.roundness) { mutableStateOf(persistence.roundness) }
    PreferenceSlider(
        title = Strings.preferenceRoundnessTitle,
        value = value,
        onValueChange = { value = it },
        onValueChangeFinished = { persistence.roundness = value },
        valueLabelSuffix = "x",
        valueRange = 0f..1.6f,
        steps = 15,
        modifier = Modifier
            .testTag(PersistentPreferenceThemeScreenSemantics.ROUNDNESS)
    )
}

private fun ColorPalette.asPreferenceRadioGroupItem(
    segment: Int = 0,
    isDefault: Boolean = false,
): PreferenceRadioGroupItem = PreferenceRadioGroupItem(
    id = id,
    segment = segment,
    badge = when {
        isDefault -> Badge.default
        category == ColorPalette.Category.CATPPUCCIN -> Badge.experimental
        category == ColorPalette.Category.GRUVBOX -> Badge.experimental
        else -> null
    },
) {
    title()
}

@ExperimentalKepkoApi
@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeScreenSystemLightPreview() {
    PreviewPersistentKepkoTheme(configure = {
        paletteLight = defaultLight; paletteDark = defaultLight
    }) {
        PersistentPreferenceThemeScreen(onBackClick = {}, isSystemInDarkTheme = false)
    }
}

@ExperimentalKepkoApi
@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeScreenSystemDarkPreview() {
    PreviewPersistentKepkoTheme(
        isSystemInDarkTheme = true,
        configure = { paletteLight = defaultDark; paletteDark = defaultDark }
    ) {
        PersistentPreferenceThemeScreen(onBackClick = {}, isSystemInDarkTheme = true)
    }
}

@ExperimentalKepkoApi
@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeScreenStyleSelectedPreview() {
    PreviewPersistentKepkoTheme(configure = { palettePrimary = ColorPalette.LIGHT }) {
        PersistentPreferenceThemeScreen(onBackClick = {})
    }
}

@ExperimentalKepkoApi
@PreviewWithTest
@Composable
internal fun PersistentPreferenceThemeScreenGrayscalePreview() {
    PreviewPersistentKepkoTheme(configure = { grayscale = true }) {
        PersistentPreferenceThemeScreen(onBackClick = {})
    }
}

@VisibleForTesting
internal object PersistentPreferenceThemeScreenSemantics {
    const val SCREEN = "theme_screen"
    const val PALETTE_MODE = "theme_palette_mode"
    const val PALETTE_MODE_DYNAMIC = "dynamic"
    const val PALETTE_MODE_STATIC = "static"
    const val PALETTE_PICKER = "theme_palette_picker"
    const val LIGHT_PICKER = "theme_light_picker"
    const val DARK_PICKER = "theme_dark_picker"
    const val GRAYSCALE = "theme_grayscale"
    const val OUTLINE = "theme_outline"
    const val ROUNDNESS = "theme_roundness"
}
