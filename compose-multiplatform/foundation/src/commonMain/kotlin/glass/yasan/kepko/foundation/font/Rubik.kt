package glass.yasan.kepko.foundation.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import glass.yasan.kepko.resource.Fonts
import org.jetbrains.compose.resources.Font

@Composable
public fun rubikFontFamily(): FontFamily = FontFamily(
    rubikBlackFont(),
    rubikBlackItalicFont(),
    rubikBoldFont(),
    rubikBoldItalicFont(),
    rubikLightFont(),
    rubikLightItalicFont(),
    rubikMediumFont(),
    rubikMediumItalicFont(),
    rubikRegularFont(),
    rubikItalicFont(),
)

@Composable
public fun rubikBlackFont(): Font = Font(Fonts.rubikBlack, FontWeight.Black, FontStyle.Normal)

@Composable
public fun rubikBlackItalicFont(): Font = Font(Fonts.rubikBlackItalic, FontWeight.Black, FontStyle.Italic)

@Composable
public fun rubikBoldFont(): Font = Font(Fonts.rubikBold, FontWeight.Bold, FontStyle.Normal)

@Composable
public fun rubikBoldItalicFont(): Font = Font(Fonts.rubikBoldItalic, FontWeight.Bold, FontStyle.Italic)

@Composable
public fun rubikLightFont(): Font = Font(Fonts.rubikLight, FontWeight.Light, FontStyle.Normal)

@Composable
public fun rubikLightItalicFont(): Font = Font(Fonts.rubikLightItalic, FontWeight.Light, FontStyle.Italic)

@Composable
public fun rubikMediumFont(): Font = Font(Fonts.rubikMedium, FontWeight.Medium, FontStyle.Normal)

@Composable
public fun rubikMediumItalicFont(): Font = Font(Fonts.rubikMediumItalic, FontWeight.Medium, FontStyle.Italic)

@Composable
public fun rubikRegularFont(): Font = Font(Fonts.rubikRegular, FontWeight.Normal, FontStyle.Normal)

@Composable
public fun rubikItalicFont(): Font = Font(Fonts.rubikItalic, FontWeight.Normal, FontStyle.Italic)
