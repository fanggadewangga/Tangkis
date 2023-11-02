package com.college.tangkis.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.college.tangkis.R

object Typography {
    fun displayLarge() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontSize = 57.sp,
        lineHeight = 64.sp
    )

    fun displayMedium() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontSize = 45.sp,
        lineHeight = 52.sp
    )

    fun displaySmall() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontSize = 36.sp,
        lineHeight = 44.sp
    )

    fun headlineLarge() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 32.sp,
        lineHeight = 40.sp
    )

    fun headlineMedium() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontSize = 28.sp,
        lineHeight = 36.sp
    )

    fun headlineSmall() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_black)),
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
// fontSize = 20; lineHeight = 28 (ini tadi tak ganti)
    fun titleLarge() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontSize = 20.sp,
        lineHeight = 28.sp
    )
// roboto_bold
    fun titleMedium() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontSize = 18.sp,
        lineHeight = 24.sp
    )

    fun titleBold() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
// fontSize = 16; lineHeight = 20, semibold
    fun titleSmall() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontSize = 16.sp,
        lineHeight = 20.sp
    )
// tambah lableLarge - prominent
    fun labelLarge() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 14.sp,
        lineHeight = 20.sp
    )

    fun labelLargeProminent() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontSize = 14.sp,
        lineHeight = 20.sp
    )

    fun labelMedium() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 12.sp,
        lineHeight = 16.sp
    )

    fun labelSmall() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 11.sp,
        lineHeight = 16.sp
    )
    fun labelStatus() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 13.sp,
        lineHeight = 0.sp
    )

    fun bodyLarge() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    fun bodyMedium() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
// fontSize = 12; LineHeight = 18
    fun bodySmall() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontSize = 12.sp,
        lineHeight = 18.sp
    )

    fun wallet() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    fun registrationStepTitle() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    fun notification() = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontSize = 12.sp,
        lineHeight = 0.sp
    )
}