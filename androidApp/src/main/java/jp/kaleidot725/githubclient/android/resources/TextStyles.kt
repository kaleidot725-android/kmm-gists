package jp.kaleidot725.githubclient.android.resources

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalUnitApi::class)
object TextStyles {
    val h1
        get() = TextStyle(
            fontSize = 96.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = TextUnit(-1.5f, TextUnitType.Sp)
        )

    val h2
        get() = TextStyle(
            fontSize = 60.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = TextUnit(-0.5f, TextUnitType.Sp)
        )

    val h3
        get() = TextStyle(
            fontSize = 48.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = TextUnit(0f, TextUnitType.Sp)
        )

    val h4
        get() = TextStyle(
            fontSize = 34.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = TextUnit(0.25f, TextUnitType.Sp)
        )

    val h5
        get() = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = TextUnit(0f, TextUnitType.Sp)
        )

    val h6
        get() = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = TextUnit(0.15f, TextUnitType.Sp)
        )

    val subTitle1
        get() = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = TextUnit(0.15f, TextUnitType.Sp)
        )

    val subTitle2
        get() = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = TextUnit(0.1f, TextUnitType.Sp)
        )

    val body1
        get() = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = TextUnit(0.5f, TextUnitType.Sp)
        )

    val body2
        get() = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = TextUnit(0.25f, TextUnitType.Sp)
        )

    val button
        get() = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = TextUnit(1.25f, TextUnitType.Sp)
        )

    val caption
        get() = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = TextUnit(0.4f, TextUnitType.Sp)
        )

    val overline
        get() = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = TextUnit(1.5f, TextUnitType.Sp)
        )
}