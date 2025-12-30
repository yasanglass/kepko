package glass.yasan.kepko.foundation.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import glass.yasan.kepko.resource.Res
import glass.yasan.kepko.resource.rubik_black
import glass.yasan.kepko.resource.rubik_blackitalic
import glass.yasan.kepko.resource.rubik_bold
import glass.yasan.kepko.resource.rubik_bolditalic
import glass.yasan.kepko.resource.rubik_italic
import glass.yasan.kepko.resource.rubik_light
import glass.yasan.kepko.resource.rubik_lightitalic
import glass.yasan.kepko.resource.rubik_medium
import glass.yasan.kepko.resource.rubik_mediumitalic
import glass.yasan.kepko.resource.rubik_regular
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
public fun rubikBlackFont(): Font = Font(Res.font.rubik_black, FontWeight.Black, FontStyle.Normal)

@Composable
public fun rubikBlackItalicFont(): Font = Font(Res.font.rubik_blackitalic, FontWeight.Black, FontStyle.Italic)

@Composable
public fun rubikBoldFont(): Font = Font(Res.font.rubik_bold, FontWeight.Bold, FontStyle.Normal)

@Composable
public fun rubikBoldItalicFont(): Font = Font(Res.font.rubik_bolditalic, FontWeight.Bold, FontStyle.Italic)

@Composable
public fun rubikLightFont(): Font = Font(Res.font.rubik_light, FontWeight.Light, FontStyle.Normal)

@Composable
public fun rubikLightItalicFont(): Font = Font(Res.font.rubik_lightitalic, FontWeight.Light, FontStyle.Italic)

@Composable
public fun rubikMediumFont(): Font = Font(Res.font.rubik_medium, FontWeight.Medium, FontStyle.Normal)

@Composable
public fun rubikMediumItalicFont(): Font = Font(Res.font.rubik_mediumitalic, FontWeight.Medium, FontStyle.Italic)

@Composable
public fun rubikRegularFont(): Font = Font(Res.font.rubik_regular, FontWeight.Normal, FontStyle.Normal)

@Composable
public fun rubikItalicFont(): Font = Font(Res.font.rubik_italic, FontWeight.Normal, FontStyle.Italic)
